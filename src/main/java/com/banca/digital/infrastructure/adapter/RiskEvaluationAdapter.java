package com.banca.digital.infrastructure.adapter;

import com.banca.digital.domain.model.LoanApplication;
import com.banca.digital.domain.port.RiskEvaluationPort;
import com.banca.digital.domain.port.RiskEvaluationPort.RiskEvaluationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class RiskEvaluationAdapter implements RiskEvaluationPort {

    private static final Logger log = LoggerFactory.getLogger(RiskEvaluationAdapter.class);
    private static final BigDecimal MIN_SCORE_FOR_APPROVAL = new BigDecimal("600");
    private static final BigDecimal MAX_DEBT_RATIO = new BigDecimal("0.4");

    @Override
    public CompletableFuture<RiskEvaluationResult> evaluateRisk(LoanApplication loanApplication) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        MDC.put("operation", "evaluateRisk");

        try {
            log.info("Iniciando evaluación de riesgo para solicitud: {}, cliente: {}, monto: {}",
                    loanApplication.getId(), loanApplication.getCustomerId(), loanApplication.getAmount());

            return CompletableFuture.supplyAsync(() -> {
                long startTime = System.currentTimeMillis();
                String evaluationId = UUID.randomUUID().toString();

                try {
                    BigDecimal riskScore = calculateRiskScore(loanApplication);
                    boolean approved = isApproved(riskScore, loanApplication);
                    BigDecimal approvedAmount = calculateApprovedAmount(loanApplication, riskScore);
                    String rejectionReason = approved ? null : calculateRejectionReason(riskScore, loanApplication);

                    long processingTime = System.currentTimeMillis() - startTime;
                    log.info("Evaluación de riesgo completada en {}ms - ID: {}, Score: {}, Aprobado: {}",
                            processingTime, evaluationId, riskScore, approved);

                    return new RiskEvaluationResult(
                            evaluationId,
                            approved,
                            riskScore,
                            approvedAmount,
                            rejectionReason,
                            processingTime
                    );

                } catch (Exception e) {
                    log.error("Error durante la evaluación de riesgo para solicitud {}: {}",
                            loanApplication.getId(), e.getMessage(), e);
                    throw new RuntimeException("Error en el motor de evaluación de riesgos", e);
                }
            });

        } finally {
            MDC.clear();
        }
    }

    private BigDecimal calculateRiskScore(LoanApplication loanApplication) {
        BigDecimal baseScore = new BigDecimal("650");
        BigDecimal amountFactor = loanApplication.getAmount()
                .divide(new BigDecimal("10000"), 4, RoundingMode.HALF_UP);
        BigDecimal termFactor = new BigDecimal(loanApplication.getTermMonths())
                .divide(new BigDecimal("120"), 4, RoundingMode.HALF_UP);

        BigDecimal scoreVariation = amountFactor.multiply(termFactor).multiply(new BigDecimal("50"));
        BigDecimal finalScore = baseScore.subtract(scoreVariation);

        int hash = loanApplication.getCustomerId().hashCode();
        BigDecimal customerAdjustment = new BigDecimal(hash % 100);
        finalScore = finalScore.add(customerAdjustment);

        return finalScore.max(new BigDecimal("300")).min(new BigDecimal("850"));
    }

    private boolean isApproved(BigDecimal riskScore, LoanApplication loanApplication) {
        boolean scoreApproved = riskScore.compareTo(MIN_SCORE_FOR_APPROVAL) >= 0;
        boolean amountApproved = isAmountWithinLimits(loanApplication);
        boolean termApproved = isTermWithinLimits(loanApplication);

        return scoreApproved && amountApproved && termApproved;
    }

    private boolean isAmountWithinLimits(LoanApplication loanApplication) {
        BigDecimal maxAllowed = new BigDecimal(System.getProperty("RISK_MAX_AMOUNT", "50000"));
        BigDecimal minAllowed = new BigDecimal("1000");
        return loanApplication.getAmount().compareTo(maxAllowed) <= 0
                && loanApplication.getAmount().compareTo(minAllowed) >= 0;
    }

    private boolean isTermWithinLimits(LoanApplication loanApplication) {
        int maxTerm = Integer.parseInt(System.getProperty("RISK_MAX_TERM_MONTHS", "120"));
        int minTerm = Integer.parseInt(System.getProperty("RISK_MIN_TERM_MONTHS", "3"));
        return loanApplication.getTermMonths() <= maxTerm && loanApplication.getTermMonths() >= minTerm;
    }

    private BigDecimal calculateApprovedAmount(LoanApplication loanApplication, BigDecimal riskScore) {
        if (!isApproved(riskScore, loanApplication)) {
            return BigDecimal.ZERO;
        }

        BigDecimal percentage = riskScore.subtract(new BigDecimal("500"))
                .divide(new BigDecimal("350"), 4, RoundingMode.HALF_UP);
        percentage = percentage.max(new BigDecimal("0.5")).min(new BigDecimal("1.0"));

        BigDecimal approvedAmount = loanApplication.getAmount().multiply(percentage);
        return approvedAmount.setScale(2, RoundingMode.HALF_UP);
    }

    private String calculateRejectionReason(BigDecimal riskScore, LoanApplication loanApplication) {
        if (riskScore.compareTo(MIN_SCORE_FOR_APPROVAL) < 0) {
            return "Score de riesgo insuficiente: " + riskScore.intValue() + ". Se requiere mínimo " + MIN_SCORE_FOR_APPROVAL.intValue();
        }
        if (!isAmountWithinLimits(loanApplication)) {
            return "Monto fuera de los límites permitidos por la política de riesgos";
        }
        if (!isTermWithinLimits(loanApplication)) {
            return "Plazo fuera de los límites permitidos por la política de riesgos";
        }
        return "No cumple con los criterios de evaluación de riesgo";
    }
}