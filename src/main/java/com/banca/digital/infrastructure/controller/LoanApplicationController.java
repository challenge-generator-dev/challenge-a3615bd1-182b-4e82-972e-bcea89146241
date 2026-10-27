package com.banca.digital.infrastructure.controller;


import com.banca.digital.domain.model.Status;
import com.banca.digital.application.service.LoanApplicationService;
import com.banca.digital.domain.model.LoanApplication;
import com.banca.digital.infrastructure.controller.dto.CreateLoanApplicationRequest;
import com.banca.digital.infrastructure.controller.dto.LoanApplicationResponse;
import com.banca.digital.infrastructure.controller.dto.ErrorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

/**
 * Controlador REST para operaciones de solicitudes de préstamos.
 * Expone los endpoints para crear, consultar y gestionar solicitudes de crédito.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/loan-applications")
@RequiredArgsConstructor
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    @PostMapping
    public ResponseEntity<LoanApplicationResponse> createLoanApplication(
            @Valid @RequestBody CreateLoanApplicationRequest request) {
        log.info("Recibida solicitud de prestamo para cliente: {}, monto: {}",
                request.clientId(), request.amount());

        try {
            LoanApplication created = loanApplicationService.createLoanApplication(
                    request.clientId(),
                    request.amount(),
                    request.termMonths(),
                    request.purpose()
            );

            log.info("Solicitud de prestamo creada exitosamente con ID: {}", created.id());
            return ResponseEntity.status(HttpStatus.CREATED).body(LoanApplicationResponse.fromDomain(created));

        } catch (IllegalArgumentException e) {
            log.error("Error al crear solicitud de prestamo: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al crear solicitud de prestamo: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanApplicationResponse> getLoanApplication(@PathVariable UUID id) {
        log.debug("Consultando solicitud de prestamo con ID: {}", id);

        return loanApplicationService.findById(id)
                .map(application -> ResponseEntity.ok(LoanApplicationResponse.fromDomain(application)))
                .orElseThrow(() -> {
                    log.warn("Solicitud de prestamo no encontrada: {}", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitud no encontrada: " + id);
                });
    }

    @GetMapping
    public ResponseEntity<List<LoanApplicationResponse>> getLoanApplicationsByStatus(
            @RequestParam(required = false) String status) {
        log.debug("Consultando solicitudes de prestamo con filtro de estado: {}", status);

        List<LoanApplication> applications;
        if (status != null && !status.isBlank()) {
            LoanApplication.Status statusEnum = LoanApplication.Status.valueOf(status.toUpperCase());
            applications = loanApplicationService.findByStatus(statusEnum);
        } else {
            applications = loanApplicationService.findAll();
        }

        log.debug("Encontradas {} solicitudes de prestamo", applications.size());
        List<LoanApplicationResponse> responses = applications.stream()
                .map(LoanApplicationResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<LoanApplicationResponse> approveLoanApplication(@PathVariable UUID id) {
        log.info("Aprobando solicitud de prestamo con ID: {}", id);

        try {
            LoanApplication approved = loanApplicationService.approveLoanApplication(id);
            log.info("Solicitud de prestamo aprobada: {}", id);
            return ResponseEntity.ok(LoanApplicationResponse.fromDomain(approved));

        } catch (IllegalArgumentException e) {
            log.error("Error al aprobar solicitud: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al aprobar solicitud {}: {}", id, e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<LoanApplicationResponse> rejectLoanApplication(@PathVariable UUID id) {
        log.info("Rechazando solicitud de prestamo con ID: {}", id);

        try {
            LoanApplication rejected = loanApplicationService.rejectLoanApplication(id);
            log.info("Solicitud de prestamo rechazada: {}", id);
            return ResponseEntity.ok(LoanApplicationResponse.fromDomain(rejected));

        } catch (IllegalArgumentException e) {
            log.error("Error al rechazar solicitud: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al rechazar solicitud {}: {}", id, e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoanApplication(@PathVariable UUID id) {
        log.info("Eliminando solicitud de prestamo con ID: {}", id);

        try {
            loanApplicationService.deleteLoanApplication(id);
            log.info("Solicitud de prestamo eliminada: {}", id);
            return ResponseEntity.noContent().build();

        } catch (IllegalArgumentException e) {
            log.error("Error al eliminar solicitud: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al eliminar solicitud {}: {}", id, e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getStatusCode().value(),
                ex.getReason()
        );
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error("Excepcion no manejada: {}", ex.getMessage(), ex);
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno del servidor"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}