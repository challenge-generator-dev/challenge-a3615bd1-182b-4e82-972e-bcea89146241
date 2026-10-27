package com.banca.digital.application.service;


import com.banca.digital.domain.model.Status;
import com.banca.digital.domain.model.LoanApplication;
import com.banca.digital.domain.port.LoanApplicationRepositoryPort;
import com.banca.digital.domain.port.RiskEvaluationPort;
import com.banca.digital.domain.port.RiskEvaluationPort.RiskEvaluationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class LoanApplicationService {

    private static final Logger log = LoggerFactory.getLogger(LoanApplicationService.class);
    private static final String MAX_AMOUNT_KEY = "MAX_LOAN_AMOUNT";
    private static final BigDecimal MAX_AMOUNT = new BigDecimal(System.getProperty(MAX_AMOUNT_KEY, "50000"));

    private final LoanApplicationRepositoryPort repositoryPort;
    private final RiskEvaluationPort riskEvaluationPort;
    private final ExecutorService executorService;

    public LoanApplicationService(LoanApplicationRepositoryPort repositoryPort,
                                   RiskEvaluationPort riskEvaluationPort) {
        this.repositoryPort = repositoryPort;
        this.riskEvaluationPort = riskEvaluationPort;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    public LoanApplication createApplication(BigDecimal amount, Integer termMonths, String customerId,
                                              String customerName, String customerEmail, String purpose) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        MDC.put("operation", "createApplication");

        try {
            validateAmount(amount);
            validateTerm(termMonths);
            validateCustomer(customerId, customerName, customerEmail);

            log.info("Iniciando creación de solicitud de préstamo para cliente: {}, monto: {}, plazo: {} meses",
                    customerId, amount, termMonths);

            LoanApplication application = LoanApplication.create(amount, termMonths, customerId,
                    customerName, customerEmail, purpose);

            LoanApplication savedApplication = repositoryPort.save(application);

            log.info("Solicitud de préstamo creada exitosamente con ID: {}", savedApplication.getId());

            return savedApplication;

        } catch (IllegalArgumentException e) {
            log.error("Error de validación al crear solicitud de préstamo: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al crear solicitud de préstamo para cliente {}: {}", customerId, e.getMessage(), e);
            throw new RuntimeException("Error al crear la solicitud de préstamo", e);
        } finally {
            MDC.clear();
        }
    }

    public CompletableFuture<LoanApplication> submitForRiskEvaluation(UUID applicationId) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        MDC.put("operation", "submitForRiskEvaluation");

        try {
            log.info("Iniciando evaluación de riesgo para solicitud: {}", applicationId);

            Optional<LoanApplication> optApplication = repositoryPort.findById(applicationId);
            if (optApplication.isEmpty()) {
                log.error("Solicitud de préstamo no encontrada: {}", applicationId);
                throw new IllegalArgumentException("Solicitud de préstamo no encontrada: " + applicationId);
            }

            LoanApplication application = optApplication.get();
            if (application.getStatus() != LoanApplication.Status.PENDING) {
                log.warn("La solicitud {} no está en estado PENDING, estado actual: {}", 
                        applicationId, application.getStatus());
                throw new IllegalStateException("La solicitud no está en estado válido para evaluación");
            }

            LoanApplication inEvaluation = application.withStatus(LoanApplication.Status.RISK_EVALUATION);
            repositoryPort.updateStatus(applicationId, LoanApplication.Status.RISK_EVALUATION);

            return CompletableFuture.supplyAsync(() -> {
                try {
                    RiskEvaluationResult result = riskEvaluationPort.evaluateRisk(inEvaluation).get();

                    LoanApplication.Status newStatus = result.approved() 
                            ? LoanApplication.Status.APPROVED 
                            : LoanApplication.Status.REJECTED;

                    BigDecimal approvedAmount = result.approved() 
                            ? result.approvedAmount() != null ? result.approvedAmount() : application.getAmount()
                            : null;

                    LoanApplication updatedApplication = application.withRiskEvaluation(
                            result.evaluationId(), 
                            result.approved(), 
                            approvedAmount,
                            result.rejectionReason()
                    ).withStatus(newStatus);

                    repositoryPort.updateStatus(applicationId, newStatus);

                    log.info("Evaluación de riesgo completada para solicitud {}: aprobado={}, monto aprobado={}",
                            applicationId, result.approved(), approvedAmount);

                    return updatedApplication;

                } catch (Exception e) {
                    log.error("Error en evaluación de riesgo para solicitud {}: {}", applicationId, e.getMessage(), e);
                    repositoryPort.updateStatus(applicationId, LoanApplication.Status.REJECTED);
                    throw new RuntimeException("Error en la evaluación de riesgo", e);
                }
            }, executorService);

        } finally {
            MDC.clear();
        }
    }

    public Optional<LoanApplication> getApplication(UUID applicationId) {
        MDC.put("correlationId", UUID.randomUUID().toString());
        MDC.put("operation", "getApplication");

        try {
            log.debug("Consultando solicitud de préstamo: {}", applicationId);
            return repositoryPort.findById(applicationId);
        } finally {
            MDC.clear();
        }
    }

    public List<LoanApplication> getApplicationsByStatus(LoanApplication.Status status) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        MDC.put("operation", "getApplicationsByStatus");

        try {
            log.debug("Consultando solicitudes de préstamo con estado: {}", status);
            return repositoryPort.findByStatus(status);
        } finally {
            MDC.clear();
        }
    }

    public LoanApplication cancelApplication(UUID applicationId) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        MDC.put("operation", "cancelApplication");

        try {
            log.info("Cancelando solicitud de préstamo: {}", applicationId);

            Optional<LoanApplication> optApplication = repositoryPort.findById(applicationId);
            if (optApplication.isEmpty()) {
                throw new IllegalArgumentException("Solicitud de préstamo no encontrada: " + applicationId);
            }

            LoanApplication application = optApplication.get();
            if (application.getStatus() == LoanApplication.Status.DISBURSED) {
                throw new IllegalStateException("No se puede cancelar una solicitud ya desembolsada");
            }

            LoanApplication cancelled = application.withStatus(LoanApplication.Status.CANCELLED);
            return repositoryPort.updateStatus(applicationId, LoanApplication.Status.CANCELLED);

        } finally {
            MDC.clear();
        }
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("El monto del préstamo es obligatorio");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }
        if (amount.compareTo(MAX_AMOUNT) > 0) {
            throw new IllegalArgumentException("El monto excede el límite máximo de " + MAX_AMOUNT);
        }
    }

    private void validateTerm(Integer termMonths) {
        if (termMonths == null) {
            throw new IllegalArgumentException("El plazo del préstamo es obligatorio");
        }
        if (termMonths < 1 || termMonths > 360) {
            throw new IllegalArgumentException("El plazo debe estar entre 1 y 360 meses");
        }
    }

    private void validateCustomer(String customerId, String customerName, String customerEmail) {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("El identificador del cliente es obligatorio");
        }
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio");
        }
        if (customerEmail == null || customerEmail.isBlank()) {
            throw new IllegalArgumentException("El correo electrónico es obligatorio");
        }
        if (!customerEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("El formato del correo electrónico no es válido");
        }
    }
}