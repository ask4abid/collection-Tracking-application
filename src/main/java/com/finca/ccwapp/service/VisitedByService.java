package com.finca.ccwapp.service;

import com.finca.ccwapp.service.dto.VisitedByDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.finca.ccwapp.domain.VisitedBy}.
 */
public interface VisitedByService {
    /**
     * Save a visitedBy.
     *
     * @param visitedByDTO the entity to save.
     * @return the persisted entity.
     */
    VisitedByDTO save(VisitedByDTO visitedByDTO);

    /**
     * Get all the visitedBies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VisitedByDTO> findAll(Pageable pageable);

    /**
     * Get the "id" visitedBy.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VisitedByDTO> findOne(Long id);

    /**
     * Delete the "id" visitedBy.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
