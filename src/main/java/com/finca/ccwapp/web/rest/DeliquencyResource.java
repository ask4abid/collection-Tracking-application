package com.finca.ccwapp.web.rest;

import com.finca.ccwapp.service.DeliquencyService;
import com.finca.ccwapp.service.dto.DeliquencyDTO;
import com.finca.ccwapp.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link com.finca.ccwapp.domain.Deliquency}.
 */
@RestController
@RequestMapping("/api")
public class DeliquencyResource {
    private final Logger log = LoggerFactory.getLogger(DeliquencyResource.class);

    private static final String ENTITY_NAME = "deliquency";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeliquencyService deliquencyService;

    public DeliquencyResource(DeliquencyService deliquencyService) {
        this.deliquencyService = deliquencyService;
    }

    /**
     * {@code POST  /deliquencies} : Create a new deliquency.
     *
     * @param deliquencyDTO the deliquencyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deliquencyDTO, or with status {@code 400 (Bad Request)} if the deliquency has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deliquencies")
    public ResponseEntity<DeliquencyDTO> createDeliquency(@Valid @RequestBody DeliquencyDTO deliquencyDTO) throws URISyntaxException {
        log.debug("REST request to save Deliquency : {}", deliquencyDTO);
        if (deliquencyDTO.getId() != null) {
            throw new BadRequestAlertException("A new deliquency cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliquencyDTO result = deliquencyService.save(deliquencyDTO);
        return ResponseEntity
            .created(new URI("/api/deliquencies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /deliquencies} : Updates an existing deliquency.
     *
     * @param deliquencyDTO the deliquencyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliquencyDTO,
     * or with status {@code 400 (Bad Request)} if the deliquencyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deliquencyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/deliquencies")
    public ResponseEntity<DeliquencyDTO> updateDeliquency(@Valid @RequestBody DeliquencyDTO deliquencyDTO) throws URISyntaxException {
        log.debug("REST request to update Deliquency : {}", deliquencyDTO);
        if (deliquencyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeliquencyDTO result = deliquencyService.save(deliquencyDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deliquencyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /deliquencies} : get all the deliquencies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliquencies in body.
     */
    @GetMapping("/deliquencies")
    public List<DeliquencyDTO> getAllDeliquencies() {
        log.debug("REST request to get all Deliquencies");
        return deliquencyService.findAll();
    }

    /**
     * {@code GET  /deliquencies/:id} : get the "id" deliquency.
     *
     * @param id the id of the deliquencyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliquencyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deliquencies/{id}")
    public ResponseEntity<DeliquencyDTO> getDeliquency(@PathVariable Long id) {
        log.debug("REST request to get Deliquency : {}", id);
        Optional<DeliquencyDTO> deliquencyDTO = deliquencyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliquencyDTO);
    }

    /**
     * {@code DELETE  /deliquencies/:id} : delete the "id" deliquency.
     *
     * @param id the id of the deliquencyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/deliquencies/{id}")
    public ResponseEntity<Void> deleteDeliquency(@PathVariable Long id) {
        log.debug("REST request to delete Deliquency : {}", id);
        deliquencyService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
