package com.finca.ccwapp.service;

import com.finca.ccwapp.service.dto.VisitsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.finca.ccwapp.domain.Visits}.
 */
public interface VisitsService {
    /**
     * Save a visits.
     *
     * @param visitsDTO the entity to save.
     * @return the persisted entity.
     */
    VisitsDTO save(VisitsDTO visitsDTO);

    /**
     * Get all the visits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VisitsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" visits.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VisitsDTO> findOne(Long id);

    /**
     * Delete the "id" visits.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
