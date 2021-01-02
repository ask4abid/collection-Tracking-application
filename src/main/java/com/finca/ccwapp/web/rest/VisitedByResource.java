package com.finca.ccwapp.web.rest;

import com.finca.ccwapp.service.VisitedByQueryService;
import com.finca.ccwapp.service.VisitedByService;
import com.finca.ccwapp.service.dto.VisitedByCriteria;
import com.finca.ccwapp.service.dto.VisitedByDTO;
import com.finca.ccwapp.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link com.finca.ccwapp.domain.VisitedBy}.
 */
@RestController
@RequestMapping("/api")
public class VisitedByResource {
    private final Logger log = LoggerFactory.getLogger(VisitedByResource.class);

    private static final String ENTITY_NAME = "visitedBy";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VisitedByService visitedByService;

    private final VisitedByQueryService visitedByQueryService;

    public VisitedByResource(VisitedByService visitedByService, VisitedByQueryService visitedByQueryService) {
        this.visitedByService = visitedByService;
        this.visitedByQueryService = visitedByQueryService;
    }

    /**
     * {@code POST  /visited-bies} : Create a new visitedBy.
     *
     * @param visitedByDTO the visitedByDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new visitedByDTO, or with status {@code 400 (Bad Request)} if the visitedBy has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/visited-bies")
    public ResponseEntity<VisitedByDTO> createVisitedBy(@Valid @RequestBody VisitedByDTO visitedByDTO) throws URISyntaxException {
        log.debug("REST request to save VisitedBy : {}", visitedByDTO);
        if (visitedByDTO.getId() != null) {
            throw new BadRequestAlertException("A new visitedBy cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VisitedByDTO result = visitedByService.save(visitedByDTO);
        return ResponseEntity
            .created(new URI("/api/visited-bies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /visited-bies} : Updates an existing visitedBy.
     *
     * @param visitedByDTO the visitedByDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated visitedByDTO,
     * or with status {@code 400 (Bad Request)} if the visitedByDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the visitedByDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/visited-bies")
    public ResponseEntity<VisitedByDTO> updateVisitedBy(@Valid @RequestBody VisitedByDTO visitedByDTO) throws URISyntaxException {
        log.debug("REST request to update VisitedBy : {}", visitedByDTO);
        if (visitedByDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VisitedByDTO result = visitedByService.save(visitedByDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, visitedByDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /visited-bies} : get all the visitedBies.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of visitedBies in body.
     */
    @GetMapping("/visited-bies")
    public ResponseEntity<List<VisitedByDTO>> getAllVisitedBies(VisitedByCriteria criteria, Pageable pageable) {
        log.debug("REST request to get VisitedBies by criteria: {}", criteria);
        Page<VisitedByDTO> page = visitedByQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /visited-bies/count} : count all the visitedBies.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/visited-bies/count")
    public ResponseEntity<Long> countVisitedBies(VisitedByCriteria criteria) {
        log.debug("REST request to count VisitedBies by criteria: {}", criteria);
        return ResponseEntity.ok().body(visitedByQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /visited-bies/:id} : get the "id" visitedBy.
     *
     * @param id the id of the visitedByDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the visitedByDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/visited-bies/{id}")
    public ResponseEntity<VisitedByDTO> getVisitedBy(@PathVariable Long id) {
        log.debug("REST request to get VisitedBy : {}", id);
        Optional<VisitedByDTO> visitedByDTO = visitedByService.findOne(id);
        return ResponseUtil.wrapOrNotFound(visitedByDTO);
    }

    /**
     * {@code DELETE  /visited-bies/:id} : delete the "id" visitedBy.
     *
     * @param id the id of the visitedByDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/visited-bies/{id}")
    public ResponseEntity<Void> deleteVisitedBy(@PathVariable Long id) {
        log.debug("REST request to delete VisitedBy : {}", id);
        visitedByService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
