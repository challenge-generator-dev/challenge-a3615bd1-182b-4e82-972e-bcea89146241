package com.banca.digital.domain.port;


import com.banca.digital.domain.model.Status;
import com.banca.digital.domain.model.LoanApplication;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto para operaciones de persistencia de solicitudes de préstamo.
 * Define el contrato que la capa de infraestructura debe implementar
 * para persistir y recuperar solicitudes de préstamo.
 */
public interface LoanApplicationRepositoryPort {

    /**
     * Guarda una solicitud de préstamo en el repositorio.
     *
     * @param loanApplication la solicitud a guardar
     * @return la solicitud guardada con su ID generado
     * @throws LoanApplicationPersistenceException si ocurre un error al persistir
     */
    LoanApplication save(LoanApplication loanApplication);

    /**
     * Busca una solicitud de préstamo por su ID.
     *
     * @param id el ID de la solicitud
     * @return Optional con la solicitud si existe
     * @throws LoanApplicationPersistenceException si ocurre un error durante la búsqueda
     */
    Optional<LoanApplication> findById(UUID id);

    /**
     * Actualiza el estado de una solicitud de préstamo.
     *
     * @param id el ID de la solicitud
     * @param status el nuevo estado
     * @return la solicitud actualizada
     * @throws LoanApplicationPersistenceException si ocurre un error durante la actualización
     */
    LoanApplication updateStatus(UUID id, LoanApplication.Status status);

    /**
     * Busca todas las solicitudes de préstamo con un estado específico.
     *
     * @param status el estado a filtrar
     * @return lista de solicitudes con el estado especificado
     * @throws LoanApplicationPersistenceException si ocurre un error durante la búsqueda
     */
    List<LoanApplication> findByStatus(LoanApplication.Status status);

    /**
     * Elimina una solicitud de préstamo por su ID.
     *
     * @param id el ID de la solicitud
     * @throws LoanApplicationPersistenceException si ocurre un error durante la eliminación
     */
    void deleteById(UUID id);
}
"