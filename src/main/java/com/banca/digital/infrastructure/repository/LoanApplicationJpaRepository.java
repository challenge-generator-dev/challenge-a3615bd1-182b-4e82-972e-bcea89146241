package com.banca.digital.infrastructure.repository;

import com.banca.digital.infrastructure.repository.entity.LoanApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio JPA para operaciones CRUD sobre la entidad de solicitud de préstamo.
 * Implementa el contrato definido por Spring Data JPA para interactuar con la base de datos.
 */
@Repository
public interface LoanApplicationJpaRepository extends JpaRepository<LoanApplicationEntity, UUID> {

    /**
     * Busca todas las solicitudes de préstamo con un estado específico.
     *
     * @param status el estado a filtrar
     * @return lista de entidades con el estado especificado
     */
    List<LoanApplicationEntity> findByStatus(String status);

    /**
     * Cuenta el número de solicitudes de préstamo con un estado específico.
     *
     * @param status el estado a contar
     * @return cantidad de solicitudes con el estado especificado
     */
    long countByStatus(String status);
}