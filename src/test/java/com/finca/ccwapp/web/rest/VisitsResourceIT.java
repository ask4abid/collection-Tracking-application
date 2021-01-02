package com.finca.ccwapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.finca.ccwapp.CcwApp;
import com.finca.ccwapp.domain.Visits;
import com.finca.ccwapp.repository.VisitsRepository;
import com.finca.ccwapp.service.VisitsService;
import com.finca.ccwapp.service.dto.VisitsDTO;
import com.finca.ccwapp.service.mapper.VisitsMapper;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link VisitsResource} REST controller.
 */
@SpringBootTest(classes = CcwApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VisitsResourceIT {
    private static final String DEFAULT_UNITID = "AAAAAAAAAA";
    private static final String UPDATED_UNITID = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESSPROPOSAL = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSPROPOSAL = "BBBBBBBBBB";

    private static final String DEFAULT_SUBPROPOSAL = "AAAAAAAAAA";
    private static final String UPDATED_SUBPROPOSAL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PROMISETOPAY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PROMISETOPAY = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_VISITEDBY = "AAAAAAAAAA";
    private static final String UPDATED_VISITEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEEID = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEEID = "BBBBBBBBBB";

    @Autowired
    private VisitsRepository visitsRepository;

    @Autowired
    private VisitsMapper visitsMapper;

    @Autowired
    private VisitsService visitsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVisitsMockMvc;

    private Visits visits;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Visits createEntity(EntityManager em) {
        Visits visits = new Visits()
            .unitid(DEFAULT_UNITID)
            .businessproposal(DEFAULT_BUSINESSPROPOSAL)
            .subproposal(DEFAULT_SUBPROPOSAL)
            .promisetopay(DEFAULT_PROMISETOPAY)
            .remarks(DEFAULT_REMARKS)
            .visitedby(DEFAULT_VISITEDBY)
            .employeeid(DEFAULT_EMPLOYEEID);
        return visits;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Visits createUpdatedEntity(EntityManager em) {
        Visits visits = new Visits()
            .unitid(UPDATED_UNITID)
            .businessproposal(UPDATED_BUSINESSPROPOSAL)
            .subproposal(UPDATED_SUBPROPOSAL)
            .promisetopay(UPDATED_PROMISETOPAY)
            .remarks(UPDATED_REMARKS)
            .visitedby(UPDATED_VISITEDBY)
            .employeeid(UPDATED_EMPLOYEEID);
        return visits;
    }

    @BeforeEach
    public void initTest() {
        visits = createEntity(em);
    }

    @Test
    @Transactional
    public void createVisits() throws Exception {
        int databaseSizeBeforeCreate = visitsRepository.findAll().size();
        // Create the Visits
        VisitsDTO visitsDTO = visitsMapper.toDto(visits);
        restVisitsMockMvc
            .perform(post("/api/visits").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitsDTO)))
            .andExpect(status().isCreated());

        // Validate the Visits in the database
        List<Visits> visitsList = visitsRepository.findAll();
        assertThat(visitsList).hasSize(databaseSizeBeforeCreate + 1);
        Visits testVisits = visitsList.get(visitsList.size() - 1);
        assertThat(testVisits.getUnitid()).isEqualTo(DEFAULT_UNITID);
        assertThat(testVisits.getBusinessproposal()).isEqualTo(DEFAULT_BUSINESSPROPOSAL);
        assertThat(testVisits.getSubproposal()).isEqualTo(DEFAULT_SUBPROPOSAL);
        assertThat(testVisits.getPromisetopay()).isEqualTo(DEFAULT_PROMISETOPAY);
        assertThat(testVisits.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testVisits.getVisitedby()).isEqualTo(DEFAULT_VISITEDBY);
        assertThat(testVisits.getEmployeeid()).isEqualTo(DEFAULT_EMPLOYEEID);
    }

    @Test
    @Transactional
    public void createVisitsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = visitsRepository.findAll().size();

        // Create the Visits with an existing ID
        visits.setId(1L);
        VisitsDTO visitsDTO = visitsMapper.toDto(visits);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVisitsMockMvc
            .perform(post("/api/visits").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Visits in the database
        List<Visits> visitsList = visitsRepository.findAll();
        assertThat(visitsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUnitidIsRequired() throws Exception {
        int databaseSizeBeforeTest = visitsRepository.findAll().size();
        // set the field null
        visits.setUnitid(null);

        // Create the Visits, which fails.
        VisitsDTO visitsDTO = visitsMapper.toDto(visits);

        restVisitsMockMvc
            .perform(post("/api/visits").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitsDTO)))
            .andExpect(status().isBadRequest());

        List<Visits> visitsList = visitsRepository.findAll();
        assertThat(visitsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBusinessproposalIsRequired() throws Exception {
        int databaseSizeBeforeTest = visitsRepository.findAll().size();
        // set the field null
        visits.setBusinessproposal(null);

        // Create the Visits, which fails.
        VisitsDTO visitsDTO = visitsMapper.toDto(visits);

        restVisitsMockMvc
            .perform(post("/api/visits").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitsDTO)))
            .andExpect(status().isBadRequest());

        List<Visits> visitsList = visitsRepository.findAll();
        assertThat(visitsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSubproposalIsRequired() throws Exception {
        int databaseSizeBeforeTest = visitsRepository.findAll().size();
        // set the field null
        visits.setSubproposal(null);

        // Create the Visits, which fails.
        VisitsDTO visitsDTO = visitsMapper.toDto(visits);

        restVisitsMockMvc
            .perform(post("/api/visits").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitsDTO)))
            .andExpect(status().isBadRequest());

        List<Visits> visitsList = visitsRepository.findAll();
        assertThat(visitsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRemarksIsRequired() throws Exception {
        int databaseSizeBeforeTest = visitsRepository.findAll().size();
        // set the field null
        visits.setRemarks(null);

        // Create the Visits, which fails.
        VisitsDTO visitsDTO = visitsMapper.toDto(visits);

        restVisitsMockMvc
            .perform(post("/api/visits").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitsDTO)))
            .andExpect(status().isBadRequest());

        List<Visits> visitsList = visitsRepository.findAll();
        assertThat(visitsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVisitedbyIsRequired() throws Exception {
        int databaseSizeBeforeTest = visitsRepository.findAll().size();
        // set the field null
        visits.setVisitedby(null);

        // Create the Visits, which fails.
        VisitsDTO visitsDTO = visitsMapper.toDto(visits);

        restVisitsMockMvc
            .perform(post("/api/visits").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitsDTO)))
            .andExpect(status().isBadRequest());

        List<Visits> visitsList = visitsRepository.findAll();
        assertThat(visitsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmployeeidIsRequired() throws Exception {
        int databaseSizeBeforeTest = visitsRepository.findAll().size();
        // set the field null
        visits.setEmployeeid(null);

        // Create the Visits, which fails.
        VisitsDTO visitsDTO = visitsMapper.toDto(visits);

        restVisitsMockMvc
            .perform(post("/api/visits").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitsDTO)))
            .andExpect(status().isBadRequest());

        List<Visits> visitsList = visitsRepository.findAll();
        assertThat(visitsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVisits() throws Exception {
        // Initialize the database
        visitsRepository.saveAndFlush(visits);

        // Get all the visitsList
        restVisitsMockMvc
            .perform(get("/api/visits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(visits.getId().intValue())))
            .andExpect(jsonPath("$.[*].unitid").value(hasItem(DEFAULT_UNITID)))
            .andExpect(jsonPath("$.[*].businessproposal").value(hasItem(DEFAULT_BUSINESSPROPOSAL)))
            .andExpect(jsonPath("$.[*].subproposal").value(hasItem(DEFAULT_SUBPROPOSAL)))
            .andExpect(jsonPath("$.[*].promisetopay").value(hasItem(DEFAULT_PROMISETOPAY.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].visitedby").value(hasItem(DEFAULT_VISITEDBY)))
            .andExpect(jsonPath("$.[*].employeeid").value(hasItem(DEFAULT_EMPLOYEEID)));
    }

    @Test
    @Transactional
    public void getVisits() throws Exception {
        // Initialize the database
        visitsRepository.saveAndFlush(visits);

        // Get the visits
        restVisitsMockMvc
            .perform(get("/api/visits/{id}", visits.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(visits.getId().intValue()))
            .andExpect(jsonPath("$.unitid").value(DEFAULT_UNITID))
            .andExpect(jsonPath("$.businessproposal").value(DEFAULT_BUSINESSPROPOSAL))
            .andExpect(jsonPath("$.subproposal").value(DEFAULT_SUBPROPOSAL))
            .andExpect(jsonPath("$.promisetopay").value(DEFAULT_PROMISETOPAY.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.visitedby").value(DEFAULT_VISITEDBY))
            .andExpect(jsonPath("$.employeeid").value(DEFAULT_EMPLOYEEID));
    }

    @Test
    @Transactional
    public void getNonExistingVisits() throws Exception {
        // Get the visits
        restVisitsMockMvc.perform(get("/api/visits/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVisits() throws Exception {
        // Initialize the database
        visitsRepository.saveAndFlush(visits);

        int databaseSizeBeforeUpdate = visitsRepository.findAll().size();

        // Update the visits
        Visits updatedVisits = visitsRepository.findById(visits.getId()).get();
        // Disconnect from session so that the updates on updatedVisits are not directly saved in db
        em.detach(updatedVisits);
        updatedVisits
            .unitid(UPDATED_UNITID)
            .businessproposal(UPDATED_BUSINESSPROPOSAL)
            .subproposal(UPDATED_SUBPROPOSAL)
            .promisetopay(UPDATED_PROMISETOPAY)
            .remarks(UPDATED_REMARKS)
            .visitedby(UPDATED_VISITEDBY)
            .employeeid(UPDATED_EMPLOYEEID);
        VisitsDTO visitsDTO = visitsMapper.toDto(updatedVisits);

        restVisitsMockMvc
            .perform(put("/api/visits").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitsDTO)))
            .andExpect(status().isOk());

        // Validate the Visits in the database
        List<Visits> visitsList = visitsRepository.findAll();
        assertThat(visitsList).hasSize(databaseSizeBeforeUpdate);
        Visits testVisits = visitsList.get(visitsList.size() - 1);
        assertThat(testVisits.getUnitid()).isEqualTo(UPDATED_UNITID);
        assertThat(testVisits.getBusinessproposal()).isEqualTo(UPDATED_BUSINESSPROPOSAL);
        assertThat(testVisits.getSubproposal()).isEqualTo(UPDATED_SUBPROPOSAL);
        assertThat(testVisits.getPromisetopay()).isEqualTo(UPDATED_PROMISETOPAY);
        assertThat(testVisits.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testVisits.getVisitedby()).isEqualTo(UPDATED_VISITEDBY);
        assertThat(testVisits.getEmployeeid()).isEqualTo(UPDATED_EMPLOYEEID);
    }

    @Test
    @Transactional
    public void updateNonExistingVisits() throws Exception {
        int databaseSizeBeforeUpdate = visitsRepository.findAll().size();

        // Create the Visits
        VisitsDTO visitsDTO = visitsMapper.toDto(visits);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVisitsMockMvc
            .perform(put("/api/visits").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Visits in the database
        List<Visits> visitsList = visitsRepository.findAll();
        assertThat(visitsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVisits() throws Exception {
        // Initialize the database
        visitsRepository.saveAndFlush(visits);

        int databaseSizeBeforeDelete = visitsRepository.findAll().size();

        // Delete the visits
        restVisitsMockMvc
            .perform(delete("/api/visits/{id}", visits.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Visits> visitsList = visitsRepository.findAll();
        assertThat(visitsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
