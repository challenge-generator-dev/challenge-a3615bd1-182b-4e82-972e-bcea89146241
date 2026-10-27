package com.banca.digital.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class LoanApplication {

    private final UUID id;
    
    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser positivo")
    private final BigDecimal amount;
    
    @NotNull(message = "El plazo es obligatorio")
    @Positive(message = "El plazo debe ser positivo")
    private final Integer termMonths;
    
    @NotNull(message = "El identificador del cliente es obligatorio")
    private final String customerId;
    
    @NotNull(message = "El nombre del cliente es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private final String customerName;
    
    @NotNull(message = "El correo electrónico es obligatorio")
    private final String customerEmail;
    
    @NotNull(message = "El propósito del préstamo es obligatorio")
    private final String purpose;
    
    private final Status status;
    
    private final LocalDateTime createdAt;
    
    private final LocalDateTime updatedAt;
    
    private final String riskEvaluationId;
    
    private final Boolean riskApproved;
    
    private final BigDecimal approvedAmount;
    
    private final String rejectionReason;

    public LoanApplication(UUID id, BigDecimal amount, Integer termMonths, String customerId, 
                           String customerName, String customerEmail, String purpose, Status status,
                           LocalDateTime createdAt, LocalDateTime updatedAt, String riskEvaluationId,
                           Boolean riskApproved, BigDecimal approvedAmount, String rejectionReason) {
        this.id = id;
        this.amount = amount;
        this.termMonths = termMonths;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.purpose = purpose;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.riskEvaluationId = riskEvaluationId;
        this.riskApproved = riskApproved;
        this.approvedAmount = approvedAmount;
        this.rejectionReason = rejectionReason;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getPurpose() {
        return purpose;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getRiskEvaluationId() {
        return riskEvaluationId;
    }

    public Boolean getRiskApproved() {
        return riskApproved;
    }

    public BigDecimal getApprovedAmount() {
        return approvedAmount;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public LoanApplication withStatus(Status newStatus) {
        return new LoanApplication(
            this.id, this.amount, this.termMonths, this.customerId,
            this.customerName, this.customerEmail, this.purpose, newStatus,
            this.createdAt, LocalDateTime.now(), this.riskEvaluationId,
            this.riskApproved, this.approvedAmount, this.rejectionReason
        );
    }

    public LoanApplication withRiskEvaluation(String evalId, Boolean approved, BigDecimal approvedAmt, String reason) {
        return new LoanApplication(
            this.id, this.amount, this.termMonths, this.customerId,
            this.customerName, this.customerEmail, this.purpose, this.status,
            this.createdAt, LocalDateTime.now(), evalId, approved, approvedAmt, reason
        );
    }

    public static LoanApplication create(BigDecimal amount, Integer termMonths, String customerId,
                                          String customerName, String customerEmail, String purpose) {
        LocalDateTime now = LocalDateTime.now();
        return new LoanApplication(
            UUID.randomUUID(), amount, termMonths, customerId,
            customerName, customerEmail, purpose, Status.PENDING,
            now, now, null, null, null, null
        );
    }

    public enum Status {
        PENDING("Pendiente de evaluación"),
        RISK_EVALUATION("En evaluación de riesgo"),
        APPROVED("Aprobado"),
        REJECTED("Rechazado"),
        DISBURSED("Desembolsado"),
        CANCELLED("Cancelado");

        private final String description;

        Status(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}