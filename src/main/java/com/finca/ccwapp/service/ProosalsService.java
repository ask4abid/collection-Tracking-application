package com.finca.ccwapp.service;

import com.finca.ccwapp.service.dto.ProosalsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.finca.ccwapp.domain.Proosals}.
 */
public interface ProosalsService {
    /**
     * Save a proosals.
     *
     * @param proosalsDTO the entity to save.
     * @return the persisted entity.
     */
    ProosalsDTO save(ProosalsDTO proosalsDTO);

    /**
     * Get all the proosals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProosalsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" proosals.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProosalsDTO> findOne(Long id);

    /**
     * Delete the "id" proosals.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
