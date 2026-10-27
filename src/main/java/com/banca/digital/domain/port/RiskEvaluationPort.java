package com.banca.digital.domain.port;

import com.banca.digital.domain.model.LoanApplication;
import java.util.concurrent.CompletableFuture;

/**
 * Puerto para interactuar con el motor de evaluación de riesgos.
 * Define el contrato que la capa de infraestructura debe implementar
 * para evaluar el riesgo de una solicitud de préstamo.
 */
public interface RiskEvaluationPort {

    /**
     * Evalúa el riesgo asociado a una solicitud de préstamo.
     *
     * @param loanApplication la solicitud de préstamo a evaluar
     * @return CompletableFuture con el resultado de la evaluación de riesgo
     * @throws RiskEvaluationException si ocurre un error durante la evaluación
     */
    CompletableFuture<RiskEvaluationResult> evaluateRisk(LoanApplication loanApplication);

    /**
     * Resultado de la evaluación de riesgo.
     *
     * @param approved indica si la solicitud fue aprobada
     * @param riskScore puntuación de riesgo calculada
     * @param rejectionReason motivo de rechazo (null si aprobado)
     */
    record RiskEvaluationResult(
        boolean approved,
        double riskScore,
        String rejectionReason
    ) {}
}
"