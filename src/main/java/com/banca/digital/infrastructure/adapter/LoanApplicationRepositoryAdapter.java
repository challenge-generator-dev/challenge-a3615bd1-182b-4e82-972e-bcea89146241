package com.banca.digital.infrastructure.adapter;


import com.banca.digital.domain.model.Status;
import com.banca.digital.domain.model.LoanApplication;
import com.banca.digital.domain.port.LoanApplicationRepositoryPort;
import com.banca.digital.infrastructure.repository.LoanApplicationJpaRepository;
import com.banca.digital.infrastructure.repository.entity.LoanApplicationEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Adaptador que implementa el puerto del dominio para operaciones de persistencia.
 * Convierte entre entidades del dominio y entidades JPA, delegando en el repositorio Spring Data.
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class LoanApplicationRepositoryAdapter implements LoanApplicationRepositoryPort {

    private final LoanApplicationJpaRepository jpaRepository;

    @Override
    public LoanApplication save(LoanApplication loanApplication) {
        log.debug("Persistiendo solicitud de prestamo con ID: {}", loanApplication.id());
        LoanApplicationEntity entity = toEntity(loanApplication);
        LoanApplicationEntity savedEntity = jpaRepository.save(entity);
        log.info("Solicitud de prestamo persistida exitosamente con ID: {}", savedEntity.getId());
        return toDomain(savedEntity);
    }

    @Override
    public Optional<LoanApplication> findById(UUID id) {
        log.debug("Buscando solicitud de prestamo con ID: {}", id);
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public LoanApplication updateStatus(UUID id, LoanApplication.Status status) {
        log.debug("Actualizando estado de solicitud {} a {}", id, status);
        LoanApplicationEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada: " + id));
        entity.setStatus(status.name());
        LoanApplicationEntity updated = jpaRepository.save(entity);
        log.info("Estado de solicitud {} actualizado a {}", id, status);
        return toDomain(updated);
    }

    @Override
    public List<LoanApplication> findByStatus(LoanApplication.Status status) {
        log.debug("Buscando solicitudes con estado: {}", status);
        List<LoanApplicationEntity> entities = jpaRepository.findByStatus(status.name());
        log.debug("Encontradas {} solicitudes con estado {}", entities.size(), status);
        return entities.stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        log.debug("Eliminando solicitud de prestamo con ID: {}", id);
        jpaRepository.deleteById(id);
        log.info("Solicitud de prestamo eliminada con ID: {}", id);
    }

    private LoanApplicationEntity toEntity(LoanApplication domain) {
        LoanApplicationEntity entity = new LoanApplicationEntity();
        entity.setId(domain.id());
        entity.setClientId(domain.clientId());
        entity.setAmount(domain.amount());
        entity.setTermMonths(domain.termMonths());
        entity.setPurpose(domain.purpose());
        entity.setStatus(domain.status().name());
        entity.setInterestRate(domain.interestRate());
        entity.setMonthlyPayment(domain.monthlyPayment());
        entity.setRiskScore(domain.riskScore());
        entity.setRequestedAt(domain.requestedAt());
        entity.setApprovedAt(domain.approvedAt());
        return entity;
    }

    private LoanApplication toDomain(LoanApplicationEntity entity) {
        return new LoanApplication(
                entity.getId(),
                entity.getClientId(),
                entity.getAmount(),
                entity.getTermMonths(),
                entity.getPurpose(),
                LoanApplication.Status.valueOf(entity.getStatus()),
                entity.getInterestRate(),
                entity.getMonthlyPayment(),
                entity.getRiskScore(),
                entity.getRequestedAt(),
                entity.getApprovedAt()
        );
    }
}