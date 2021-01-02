package com.finca.ccwapp.service;

import com.finca.ccwapp.service.dto.DeliquencyDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.finca.ccwapp.domain.Deliquency}.
 */
public interface DeliquencyService {
    /**
     * Save a deliquency.
     *
     * @param deliquencyDTO the entity to save.
     * @return the persisted entity.
     */
    DeliquencyDTO save(DeliquencyDTO deliquencyDTO);

    /**
     * Get all the deliquencies.
     *
     * @return the list of entities.
     */
    List<DeliquencyDTO> findAll();

    /**
     * Get the "id" deliquency.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeliquencyDTO> findOne(Long id);

    /**
     * Delete the "id" deliquency.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
