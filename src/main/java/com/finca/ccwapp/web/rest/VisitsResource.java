package com.finca.ccwapp.web.rest;

import com.finca.ccwapp.service.VisitsService;
import com.finca.ccwapp.service.dto.VisitsDTO;
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
 * REST controller for managing {@link com.finca.ccwapp.domain.Visits}.
 */
@RestController
@RequestMapping("/api")
public class VisitsResource {
    private final Logger log = LoggerFactory.getLogger(VisitsResource.class);

    private static final String ENTITY_NAME = "visits";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VisitsService visitsService;

    public VisitsResource(VisitsService visitsService) {
        this.visitsService = visitsService;
    }

    /**
     * {@code POST  /visits} : Create a new visits.
     *
     * @param visitsDTO the visitsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new visitsDTO, or with status {@code 400 (Bad Request)} if the visits has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/visits")
    public ResponseEntity<VisitsDTO> createVisits(@Valid @RequestBody VisitsDTO visitsDTO) throws URISyntaxException {
        log.debug("REST request to save Visits : {}", visitsDTO);
        if (visitsDTO.getId() != null) {
            throw new BadRequestAlertException("A new visits cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VisitsDTO result = visitsService.save(visitsDTO);
        return ResponseEntity
            .created(new URI("/api/visits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /visits} : Updates an existing visits.
     *
     * @param visitsDTO the visitsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated visitsDTO,
     * or with status {@code 400 (Bad Request)} if the visitsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the visitsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/visits")
    public ResponseEntity<VisitsDTO> updateVisits(@Valid @RequestBody VisitsDTO visitsDTO) throws URISyntaxException {
        log.debug("REST request to update Visits : {}", visitsDTO);
        if (visitsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VisitsDTO result = visitsService.save(visitsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, visitsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /visits} : get all the visits.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of visits in body.
     */
    @GetMapping("/visits")
    public ResponseEntity<List<VisitsDTO>> getAllVisits(Pageable pageable) {
        log.debug("REST request to get a page of Visits");
        Page<VisitsDTO> page = visitsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /visits/:id} : get the "id" visits.
     *
     * @param id the id of the visitsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the visitsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/visits/{id}")
    public ResponseEntity<VisitsDTO> getVisits(@PathVariable Long id) {
        log.debug("REST request to get Visits : {}", id);
        Optional<VisitsDTO> visitsDTO = visitsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(visitsDTO);
    }

    /**
     * {@code DELETE  /visits/:id} : delete the "id" visits.
     *
     * @param id the id of the visitsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/visits/{id}")
    public ResponseEntity<Void> deleteVisits(@PathVariable Long id) {
        log.debug("REST request to delete Visits : {}", id);
        visitsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
