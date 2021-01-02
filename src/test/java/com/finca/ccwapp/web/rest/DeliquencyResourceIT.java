package com.finca.ccwapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.finca.ccwapp.CcwApp;
import com.finca.ccwapp.domain.Deliquency;
import com.finca.ccwapp.repository.DeliquencyRepository;
import com.finca.ccwapp.service.DeliquencyService;
import com.finca.ccwapp.service.dto.DeliquencyDTO;
import com.finca.ccwapp.service.mapper.DeliquencyMapper;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DeliquencyResource} REST controller.
 */
@SpringBootTest(classes = CcwApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DeliquencyResourceIT {
    private static final String DEFAULT_OBSERVATOINID = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATOINID = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVATION = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATION = "BBBBBBBBBB";

    @Autowired
    private DeliquencyRepository deliquencyRepository;

    @Autowired
    private DeliquencyMapper deliquencyMapper;

    @Autowired
    private DeliquencyService deliquencyService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeliquencyMockMvc;

    private Deliquency deliquency;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Deliquency createEntity(EntityManager em) {
        Deliquency deliquency = new Deliquency().observatoinid(DEFAULT_OBSERVATOINID).observation(DEFAULT_OBSERVATION);
        return deliquency;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Deliquency createUpdatedEntity(EntityManager em) {
        Deliquency deliquency = new Deliquency().observatoinid(UPDATED_OBSERVATOINID).observation(UPDATED_OBSERVATION);
        return deliquency;
    }

    @BeforeEach
    public void initTest() {
        deliquency = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeliquency() throws Exception {
        int databaseSizeBeforeCreate = deliquencyRepository.findAll().size();
        // Create the Deliquency
        DeliquencyDTO deliquencyDTO = deliquencyMapper.toDto(deliquency);
        restDeliquencyMockMvc
            .perform(
                post("/api/deliquencies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deliquencyDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Deliquency in the database
        List<Deliquency> deliquencyList = deliquencyRepository.findAll();
        assertThat(deliquencyList).hasSize(databaseSizeBeforeCreate + 1);
        Deliquency testDeliquency = deliquencyList.get(deliquencyList.size() - 1);
        assertThat(testDeliquency.getObservatoinid()).isEqualTo(DEFAULT_OBSERVATOINID);
        assertThat(testDeliquency.getObservation()).isEqualTo(DEFAULT_OBSERVATION);
    }

    @Test
    @Transactional
    public void createDeliquencyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deliquencyRepository.findAll().size();

        // Create the Deliquency with an existing ID
        deliquency.setId(1L);
        DeliquencyDTO deliquencyDTO = deliquencyMapper.toDto(deliquency);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliquencyMockMvc
            .perform(
                post("/api/deliquencies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deliquencyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Deliquency in the database
        List<Deliquency> deliquencyList = deliquencyRepository.findAll();
        assertThat(deliquencyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkObservatoinidIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliquencyRepository.findAll().size();
        // set the field null
        deliquency.setObservatoinid(null);

        // Create the Deliquency, which fails.
        DeliquencyDTO deliquencyDTO = deliquencyMapper.toDto(deliquency);

        restDeliquencyMockMvc
            .perform(
                post("/api/deliquencies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deliquencyDTO))
            )
            .andExpect(status().isBadRequest());

        List<Deliquency> deliquencyList = deliquencyRepository.findAll();
        assertThat(deliquencyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservationIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliquencyRepository.findAll().size();
        // set the field null
        deliquency.setObservation(null);

        // Create the Deliquency, which fails.
        DeliquencyDTO deliquencyDTO = deliquencyMapper.toDto(deliquency);

        restDeliquencyMockMvc
            .perform(
                post("/api/deliquencies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deliquencyDTO))
            )
            .andExpect(status().isBadRequest());

        List<Deliquency> deliquencyList = deliquencyRepository.findAll();
        assertThat(deliquencyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDeliquencies() throws Exception {
        // Initialize the database
        deliquencyRepository.saveAndFlush(deliquency);

        // Get all the deliquencyList
        restDeliquencyMockMvc
            .perform(get("/api/deliquencies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deliquency.getId().intValue())))
            .andExpect(jsonPath("$.[*].observatoinid").value(hasItem(DEFAULT_OBSERVATOINID)))
            .andExpect(jsonPath("$.[*].observation").value(hasItem(DEFAULT_OBSERVATION)));
    }

    @Test
    @Transactional
    public void getDeliquency() throws Exception {
        // Initialize the database
        deliquencyRepository.saveAndFlush(deliquency);

        // Get the deliquency
        restDeliquencyMockMvc
            .perform(get("/api/deliquencies/{id}", deliquency.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(deliquency.getId().intValue()))
            .andExpect(jsonPath("$.observatoinid").value(DEFAULT_OBSERVATOINID))
            .andExpect(jsonPath("$.observation").value(DEFAULT_OBSERVATION));
    }

    @Test
    @Transactional
    public void getNonExistingDeliquency() throws Exception {
        // Get the deliquency
        restDeliquencyMockMvc.perform(get("/api/deliquencies/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeliquency() throws Exception {
        // Initialize the database
        deliquencyRepository.saveAndFlush(deliquency);

        int databaseSizeBeforeUpdate = deliquencyRepository.findAll().size();

        // Update the deliquency
        Deliquency updatedDeliquency = deliquencyRepository.findById(deliquency.getId()).get();
        // Disconnect from session so that the updates on updatedDeliquency are not directly saved in db
        em.detach(updatedDeliquency);
        updatedDeliquency.observatoinid(UPDATED_OBSERVATOINID).observation(UPDATED_OBSERVATION);
        DeliquencyDTO deliquencyDTO = deliquencyMapper.toDto(updatedDeliquency);

        restDeliquencyMockMvc
            .perform(
                put("/api/deliquencies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deliquencyDTO))
            )
            .andExpect(status().isOk());

        // Validate the Deliquency in the database
        List<Deliquency> deliquencyList = deliquencyRepository.findAll();
        assertThat(deliquencyList).hasSize(databaseSizeBeforeUpdate);
        Deliquency testDeliquency = deliquencyList.get(deliquencyList.size() - 1);
        assertThat(testDeliquency.getObservatoinid()).isEqualTo(UPDATED_OBSERVATOINID);
        assertThat(testDeliquency.getObservation()).isEqualTo(UPDATED_OBSERVATION);
    }

    @Test
    @Transactional
    public void updateNonExistingDeliquency() throws Exception {
        int databaseSizeBeforeUpdate = deliquencyRepository.findAll().size();

        // Create the Deliquency
        DeliquencyDTO deliquencyDTO = deliquencyMapper.toDto(deliquency);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliquencyMockMvc
            .perform(
                put("/api/deliquencies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deliquencyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Deliquency in the database
        List<Deliquency> deliquencyList = deliquencyRepository.findAll();
        assertThat(deliquencyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDeliquency() throws Exception {
        // Initialize the database
        deliquencyRepository.saveAndFlush(deliquency);

        int databaseSizeBeforeDelete = deliquencyRepository.findAll().size();

        // Delete the deliquency
        restDeliquencyMockMvc
            .perform(delete("/api/deliquencies/{id}", deliquency.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Deliquency> deliquencyList = deliquencyRepository.findAll();
        assertThat(deliquencyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
