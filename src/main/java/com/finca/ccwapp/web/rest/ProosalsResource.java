package com.finca.ccwapp.web.rest;

import com.finca.ccwapp.service.ProosalsService;
import com.finca.ccwapp.service.dto.ProosalsDTO;
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
 * REST controller for managing {@link com.finca.ccwapp.domain.Proosals}.
 */
@RestController
@RequestMapping("/api")
public class ProosalsResource {
    private final Logger log = LoggerFactory.getLogger(ProosalsResource.class);

    private static final String ENTITY_NAME = "proosals";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProosalsService proosalsService;

    public ProosalsResource(ProosalsService proosalsService) {
        this.proosalsService = proosalsService;
    }

    /**
     * {@code POST  /proosals} : Create a new proosals.
     *
     * @param proosalsDTO the proosalsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proosalsDTO, or with status {@code 400 (Bad Request)} if the proosals has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/proosals")
    public ResponseEntity<ProosalsDTO> createProosals(@Valid @RequestBody ProosalsDTO proosalsDTO) throws URISyntaxException {
        log.debug("REST request to save Proosals : {}", proosalsDTO);
        if (proosalsDTO.getId() != null) {
            throw new BadRequestAlertException("A new proosals cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProosalsDTO result = proosalsService.save(proosalsDTO);
        return ResponseEntity
            .created(new URI("/api/proosals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /proosals} : Updates an existing proosals.
     *
     * @param proosalsDTO the proosalsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proosalsDTO,
     * or with status {@code 400 (Bad Request)} if the proosalsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proosalsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/proosals")
    public ResponseEntity<ProosalsDTO> updateProosals(@Valid @RequestBody ProosalsDTO proosalsDTO) throws URISyntaxException {
        log.debug("REST request to update Proosals : {}", proosalsDTO);
        if (proosalsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProosalsDTO result = proosalsService.save(proosalsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proosalsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /proosals} : get all the proosals.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proosals in body.
     */
    @GetMapping("/proosals")
    public ResponseEntity<List<ProosalsDTO>> getAllProosals(Pageable pageable) {
        log.debug("REST request to get a page of Proosals");
        Page<ProosalsDTO> page = proosalsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /proosals/:id} : get the "id" proosals.
     *
     * @param id the id of the proosalsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proosalsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/proosals/{id}")
    public ResponseEntity<ProosalsDTO> getProosals(@PathVariable Long id) {
        log.debug("REST request to get Proosals : {}", id);
        Optional<ProosalsDTO> proosalsDTO = proosalsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proosalsDTO);
    }

    /**
     * {@code DELETE  /proosals/:id} : delete the "id" proosals.
     *
     * @param id the id of the proosalsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/proosals/{id}")
    public ResponseEntity<Void> deleteProosals(@PathVariable Long id) {
        log.debug("REST request to delete Proosals : {}", id);
        proosalsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
