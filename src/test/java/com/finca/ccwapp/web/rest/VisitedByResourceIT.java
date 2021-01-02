package com.finca.ccwapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.finca.ccwapp.CcwApp;
import com.finca.ccwapp.domain.Proosals;
import com.finca.ccwapp.domain.VisitedBy;
import com.finca.ccwapp.repository.VisitedByRepository;
import com.finca.ccwapp.service.VisitedByQueryService;
import com.finca.ccwapp.service.VisitedByService;
import com.finca.ccwapp.service.dto.VisitedByCriteria;
import com.finca.ccwapp.service.dto.VisitedByDTO;
import com.finca.ccwapp.service.mapper.VisitedByMapper;
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
 * Integration tests for the {@link VisitedByResource} REST controller.
 */
@SpringBootTest(classes = CcwApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VisitedByResourceIT {
    private static final String DEFAULT_UNITID = "AAAAAAAAAA";
    private static final String UPDATED_UNITID = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEEID = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEEID = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_VISTDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VISTDATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_VISTDATE = LocalDate.ofEpochDay(-1L);

    @Autowired
    private VisitedByRepository visitedByRepository;

    @Autowired
    private VisitedByMapper visitedByMapper;

    @Autowired
    private VisitedByService visitedByService;

    @Autowired
    private VisitedByQueryService visitedByQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVisitedByMockMvc;

    private VisitedBy visitedBy;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VisitedBy createEntity(EntityManager em) {
        VisitedBy visitedBy = new VisitedBy()
            .unitid(DEFAULT_UNITID)
            .firstname(DEFAULT_FIRSTNAME)
            .lastname(DEFAULT_LASTNAME)
            .status(DEFAULT_STATUS)
            .employeeid(DEFAULT_EMPLOYEEID)
            .role(DEFAULT_ROLE)
            .designation(DEFAULT_DESIGNATION)
            .vistdate(DEFAULT_VISTDATE);
        return visitedBy;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VisitedBy createUpdatedEntity(EntityManager em) {
        VisitedBy visitedBy = new VisitedBy()
            .unitid(UPDATED_UNITID)
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .status(UPDATED_STATUS)
            .employeeid(UPDATED_EMPLOYEEID)
            .role(UPDATED_ROLE)
            .designation(UPDATED_DESIGNATION)
            .vistdate(UPDATED_VISTDATE);
        return visitedBy;
    }

    @BeforeEach
    public void initTest() {
        visitedBy = createEntity(em);
    }

    @Test
    @Transactional
    public void createVisitedBy() throws Exception {
        int databaseSizeBeforeCreate = visitedByRepository.findAll().size();
        // Create the VisitedBy
        VisitedByDTO visitedByDTO = visitedByMapper.toDto(visitedBy);
        restVisitedByMockMvc
            .perform(
                post("/api/visited-bies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitedByDTO))
            )
            .andExpect(status().isCreated());

        // Validate the VisitedBy in the database
        List<VisitedBy> visitedByList = visitedByRepository.findAll();
        assertThat(visitedByList).hasSize(databaseSizeBeforeCreate + 1);
        VisitedBy testVisitedBy = visitedByList.get(visitedByList.size() - 1);
        assertThat(testVisitedBy.getUnitid()).isEqualTo(DEFAULT_UNITID);
        assertThat(testVisitedBy.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testVisitedBy.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testVisitedBy.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testVisitedBy.getEmployeeid()).isEqualTo(DEFAULT_EMPLOYEEID);
        assertThat(testVisitedBy.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testVisitedBy.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testVisitedBy.getVistdate()).isEqualTo(DEFAULT_VISTDATE);
    }

    @Test
    @Transactional
    public void createVisitedByWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = visitedByRepository.findAll().size();

        // Create the VisitedBy with an existing ID
        visitedBy.setId(1L);
        VisitedByDTO visitedByDTO = visitedByMapper.toDto(visitedBy);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVisitedByMockMvc
            .perform(
                post("/api/visited-bies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitedByDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VisitedBy in the database
        List<VisitedBy> visitedByList = visitedByRepository.findAll();
        assertThat(visitedByList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUnitidIsRequired() throws Exception {
        int databaseSizeBeforeTest = visitedByRepository.findAll().size();
        // set the field null
        visitedBy.setUnitid(null);

        // Create the VisitedBy, which fails.
        VisitedByDTO visitedByDTO = visitedByMapper.toDto(visitedBy);

        restVisitedByMockMvc
            .perform(
                post("/api/visited-bies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitedByDTO))
            )
            .andExpect(status().isBadRequest());

        List<VisitedBy> visitedByList = visitedByRepository.findAll();
        assertThat(visitedByList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = visitedByRepository.findAll().size();
        // set the field null
        visitedBy.setFirstname(null);

        // Create the VisitedBy, which fails.
        VisitedByDTO visitedByDTO = visitedByMapper.toDto(visitedBy);

        restVisitedByMockMvc
            .perform(
                post("/api/visited-bies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitedByDTO))
            )
            .andExpect(status().isBadRequest());

        List<VisitedBy> visitedByList = visitedByRepository.findAll();
        assertThat(visitedByList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = visitedByRepository.findAll().size();
        // set the field null
        visitedBy.setLastname(null);

        // Create the VisitedBy, which fails.
        VisitedByDTO visitedByDTO = visitedByMapper.toDto(visitedBy);

        restVisitedByMockMvc
            .perform(
                post("/api/visited-bies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitedByDTO))
            )
            .andExpect(status().isBadRequest());

        List<VisitedBy> visitedByList = visitedByRepository.findAll();
        assertThat(visitedByList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = visitedByRepository.findAll().size();
        // set the field null
        visitedBy.setStatus(null);

        // Create the VisitedBy, which fails.
        VisitedByDTO visitedByDTO = visitedByMapper.toDto(visitedBy);

        restVisitedByMockMvc
            .perform(
                post("/api/visited-bies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitedByDTO))
            )
            .andExpect(status().isBadRequest());

        List<VisitedBy> visitedByList = visitedByRepository.findAll();
        assertThat(visitedByList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmployeeidIsRequired() throws Exception {
        int databaseSizeBeforeTest = visitedByRepository.findAll().size();
        // set the field null
        visitedBy.setEmployeeid(null);

        // Create the VisitedBy, which fails.
        VisitedByDTO visitedByDTO = visitedByMapper.toDto(visitedBy);

        restVisitedByMockMvc
            .perform(
                post("/api/visited-bies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitedByDTO))
            )
            .andExpect(status().isBadRequest());

        List<VisitedBy> visitedByList = visitedByRepository.findAll();
        assertThat(visitedByList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRoleIsRequired() throws Exception {
        int databaseSizeBeforeTest = visitedByRepository.findAll().size();
        // set the field null
        visitedBy.setRole(null);

        // Create the VisitedBy, which fails.
        VisitedByDTO visitedByDTO = visitedByMapper.toDto(visitedBy);

        restVisitedByMockMvc
            .perform(
                post("/api/visited-bies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitedByDTO))
            )
            .andExpect(status().isBadRequest());

        List<VisitedBy> visitedByList = visitedByRepository.findAll();
        assertThat(visitedByList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDesignationIsRequired() throws Exception {
        int databaseSizeBeforeTest = visitedByRepository.findAll().size();
        // set the field null
        visitedBy.setDesignation(null);

        // Create the VisitedBy, which fails.
        VisitedByDTO visitedByDTO = visitedByMapper.toDto(visitedBy);

        restVisitedByMockMvc
            .perform(
                post("/api/visited-bies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitedByDTO))
            )
            .andExpect(status().isBadRequest());

        List<VisitedBy> visitedByList = visitedByRepository.findAll();
        assertThat(visitedByList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVisitedBies() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList
        restVisitedByMockMvc
            .perform(get("/api/visited-bies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(visitedBy.getId().intValue())))
            .andExpect(jsonPath("$.[*].unitid").value(hasItem(DEFAULT_UNITID)))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].employeeid").value(hasItem(DEFAULT_EMPLOYEEID)))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION)))
            .andExpect(jsonPath("$.[*].vistdate").value(hasItem(DEFAULT_VISTDATE.toString())));
    }

    @Test
    @Transactional
    public void getVisitedBy() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get the visitedBy
        restVisitedByMockMvc
            .perform(get("/api/visited-bies/{id}", visitedBy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(visitedBy.getId().intValue()))
            .andExpect(jsonPath("$.unitid").value(DEFAULT_UNITID))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.employeeid").value(DEFAULT_EMPLOYEEID))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION))
            .andExpect(jsonPath("$.vistdate").value(DEFAULT_VISTDATE.toString()));
    }

    @Test
    @Transactional
    public void getVisitedBiesByIdFiltering() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        Long id = visitedBy.getId();

        defaultVisitedByShouldBeFound("id.equals=" + id);
        defaultVisitedByShouldNotBeFound("id.notEquals=" + id);

        defaultVisitedByShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultVisitedByShouldNotBeFound("id.greaterThan=" + id);

        defaultVisitedByShouldBeFound("id.lessThanOrEqual=" + id);
        defaultVisitedByShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByUnitidIsEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where unitid equals to DEFAULT_UNITID
        defaultVisitedByShouldBeFound("unitid.equals=" + DEFAULT_UNITID);

        // Get all the visitedByList where unitid equals to UPDATED_UNITID
        defaultVisitedByShouldNotBeFound("unitid.equals=" + UPDATED_UNITID);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByUnitidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where unitid not equals to DEFAULT_UNITID
        defaultVisitedByShouldNotBeFound("unitid.notEquals=" + DEFAULT_UNITID);

        // Get all the visitedByList where unitid not equals to UPDATED_UNITID
        defaultVisitedByShouldBeFound("unitid.notEquals=" + UPDATED_UNITID);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByUnitidIsInShouldWork() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where unitid in DEFAULT_UNITID or UPDATED_UNITID
        defaultVisitedByShouldBeFound("unitid.in=" + DEFAULT_UNITID + "," + UPDATED_UNITID);

        // Get all the visitedByList where unitid equals to UPDATED_UNITID
        defaultVisitedByShouldNotBeFound("unitid.in=" + UPDATED_UNITID);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByUnitidIsNullOrNotNull() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where unitid is not null
        defaultVisitedByShouldBeFound("unitid.specified=true");

        // Get all the visitedByList where unitid is null
        defaultVisitedByShouldNotBeFound("unitid.specified=false");
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByUnitidContainsSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where unitid contains DEFAULT_UNITID
        defaultVisitedByShouldBeFound("unitid.contains=" + DEFAULT_UNITID);

        // Get all the visitedByList where unitid contains UPDATED_UNITID
        defaultVisitedByShouldNotBeFound("unitid.contains=" + UPDATED_UNITID);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByUnitidNotContainsSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where unitid does not contain DEFAULT_UNITID
        defaultVisitedByShouldNotBeFound("unitid.doesNotContain=" + DEFAULT_UNITID);

        // Get all the visitedByList where unitid does not contain UPDATED_UNITID
        defaultVisitedByShouldBeFound("unitid.doesNotContain=" + UPDATED_UNITID);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByFirstnameIsEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where firstname equals to DEFAULT_FIRSTNAME
        defaultVisitedByShouldBeFound("firstname.equals=" + DEFAULT_FIRSTNAME);

        // Get all the visitedByList where firstname equals to UPDATED_FIRSTNAME
        defaultVisitedByShouldNotBeFound("firstname.equals=" + UPDATED_FIRSTNAME);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByFirstnameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where firstname not equals to DEFAULT_FIRSTNAME
        defaultVisitedByShouldNotBeFound("firstname.notEquals=" + DEFAULT_FIRSTNAME);

        // Get all the visitedByList where firstname not equals to UPDATED_FIRSTNAME
        defaultVisitedByShouldBeFound("firstname.notEquals=" + UPDATED_FIRSTNAME);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByFirstnameIsInShouldWork() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where firstname in DEFAULT_FIRSTNAME or UPDATED_FIRSTNAME
        defaultVisitedByShouldBeFound("firstname.in=" + DEFAULT_FIRSTNAME + "," + UPDATED_FIRSTNAME);

        // Get all the visitedByList where firstname equals to UPDATED_FIRSTNAME
        defaultVisitedByShouldNotBeFound("firstname.in=" + UPDATED_FIRSTNAME);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByFirstnameIsNullOrNotNull() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where firstname is not null
        defaultVisitedByShouldBeFound("firstname.specified=true");

        // Get all the visitedByList where firstname is null
        defaultVisitedByShouldNotBeFound("firstname.specified=false");
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByFirstnameContainsSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where firstname contains DEFAULT_FIRSTNAME
        defaultVisitedByShouldBeFound("firstname.contains=" + DEFAULT_FIRSTNAME);

        // Get all the visitedByList where firstname contains UPDATED_FIRSTNAME
        defaultVisitedByShouldNotBeFound("firstname.contains=" + UPDATED_FIRSTNAME);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByFirstnameNotContainsSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where firstname does not contain DEFAULT_FIRSTNAME
        defaultVisitedByShouldNotBeFound("firstname.doesNotContain=" + DEFAULT_FIRSTNAME);

        // Get all the visitedByList where firstname does not contain UPDATED_FIRSTNAME
        defaultVisitedByShouldBeFound("firstname.doesNotContain=" + UPDATED_FIRSTNAME);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByLastnameIsEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where lastname equals to DEFAULT_LASTNAME
        defaultVisitedByShouldBeFound("lastname.equals=" + DEFAULT_LASTNAME);

        // Get all the visitedByList where lastname equals to UPDATED_LASTNAME
        defaultVisitedByShouldNotBeFound("lastname.equals=" + UPDATED_LASTNAME);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByLastnameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where lastname not equals to DEFAULT_LASTNAME
        defaultVisitedByShouldNotBeFound("lastname.notEquals=" + DEFAULT_LASTNAME);

        // Get all the visitedByList where lastname not equals to UPDATED_LASTNAME
        defaultVisitedByShouldBeFound("lastname.notEquals=" + UPDATED_LASTNAME);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByLastnameIsInShouldWork() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where lastname in DEFAULT_LASTNAME or UPDATED_LASTNAME
        defaultVisitedByShouldBeFound("lastname.in=" + DEFAULT_LASTNAME + "," + UPDATED_LASTNAME);

        // Get all the visitedByList where lastname equals to UPDATED_LASTNAME
        defaultVisitedByShouldNotBeFound("lastname.in=" + UPDATED_LASTNAME);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByLastnameIsNullOrNotNull() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where lastname is not null
        defaultVisitedByShouldBeFound("lastname.specified=true");

        // Get all the visitedByList where lastname is null
        defaultVisitedByShouldNotBeFound("lastname.specified=false");
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByLastnameContainsSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where lastname contains DEFAULT_LASTNAME
        defaultVisitedByShouldBeFound("lastname.contains=" + DEFAULT_LASTNAME);

        // Get all the visitedByList where lastname contains UPDATED_LASTNAME
        defaultVisitedByShouldNotBeFound("lastname.contains=" + UPDATED_LASTNAME);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByLastnameNotContainsSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where lastname does not contain DEFAULT_LASTNAME
        defaultVisitedByShouldNotBeFound("lastname.doesNotContain=" + DEFAULT_LASTNAME);

        // Get all the visitedByList where lastname does not contain UPDATED_LASTNAME
        defaultVisitedByShouldBeFound("lastname.doesNotContain=" + UPDATED_LASTNAME);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where status equals to DEFAULT_STATUS
        defaultVisitedByShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the visitedByList where status equals to UPDATED_STATUS
        defaultVisitedByShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where status not equals to DEFAULT_STATUS
        defaultVisitedByShouldNotBeFound("status.notEquals=" + DEFAULT_STATUS);

        // Get all the visitedByList where status not equals to UPDATED_STATUS
        defaultVisitedByShouldBeFound("status.notEquals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultVisitedByShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the visitedByList where status equals to UPDATED_STATUS
        defaultVisitedByShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where status is not null
        defaultVisitedByShouldBeFound("status.specified=true");

        // Get all the visitedByList where status is null
        defaultVisitedByShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByStatusContainsSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where status contains DEFAULT_STATUS
        defaultVisitedByShouldBeFound("status.contains=" + DEFAULT_STATUS);

        // Get all the visitedByList where status contains UPDATED_STATUS
        defaultVisitedByShouldNotBeFound("status.contains=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByStatusNotContainsSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where status does not contain DEFAULT_STATUS
        defaultVisitedByShouldNotBeFound("status.doesNotContain=" + DEFAULT_STATUS);

        // Get all the visitedByList where status does not contain UPDATED_STATUS
        defaultVisitedByShouldBeFound("status.doesNotContain=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByEmployeeidIsEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where employeeid equals to DEFAULT_EMPLOYEEID
        defaultVisitedByShouldBeFound("employeeid.equals=" + DEFAULT_EMPLOYEEID);

        // Get all the visitedByList where employeeid equals to UPDATED_EMPLOYEEID
        defaultVisitedByShouldNotBeFound("employeeid.equals=" + UPDATED_EMPLOYEEID);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByEmployeeidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where employeeid not equals to DEFAULT_EMPLOYEEID
        defaultVisitedByShouldNotBeFound("employeeid.notEquals=" + DEFAULT_EMPLOYEEID);

        // Get all the visitedByList where employeeid not equals to UPDATED_EMPLOYEEID
        defaultVisitedByShouldBeFound("employeeid.notEquals=" + UPDATED_EMPLOYEEID);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByEmployeeidIsInShouldWork() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where employeeid in DEFAULT_EMPLOYEEID or UPDATED_EMPLOYEEID
        defaultVisitedByShouldBeFound("employeeid.in=" + DEFAULT_EMPLOYEEID + "," + UPDATED_EMPLOYEEID);

        // Get all the visitedByList where employeeid equals to UPDATED_EMPLOYEEID
        defaultVisitedByShouldNotBeFound("employeeid.in=" + UPDATED_EMPLOYEEID);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByEmployeeidIsNullOrNotNull() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where employeeid is not null
        defaultVisitedByShouldBeFound("employeeid.specified=true");

        // Get all the visitedByList where employeeid is null
        defaultVisitedByShouldNotBeFound("employeeid.specified=false");
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByEmployeeidContainsSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where employeeid contains DEFAULT_EMPLOYEEID
        defaultVisitedByShouldBeFound("employeeid.contains=" + DEFAULT_EMPLOYEEID);

        // Get all the visitedByList where employeeid contains UPDATED_EMPLOYEEID
        defaultVisitedByShouldNotBeFound("employeeid.contains=" + UPDATED_EMPLOYEEID);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByEmployeeidNotContainsSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where employeeid does not contain DEFAULT_EMPLOYEEID
        defaultVisitedByShouldNotBeFound("employeeid.doesNotContain=" + DEFAULT_EMPLOYEEID);

        // Get all the visitedByList where employeeid does not contain UPDATED_EMPLOYEEID
        defaultVisitedByShouldBeFound("employeeid.doesNotContain=" + UPDATED_EMPLOYEEID);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByRoleIsEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where role equals to DEFAULT_ROLE
        defaultVisitedByShouldBeFound("role.equals=" + DEFAULT_ROLE);

        // Get all the visitedByList where role equals to UPDATED_ROLE
        defaultVisitedByShouldNotBeFound("role.equals=" + UPDATED_ROLE);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByRoleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where role not equals to DEFAULT_ROLE
        defaultVisitedByShouldNotBeFound("role.notEquals=" + DEFAULT_ROLE);

        // Get all the visitedByList where role not equals to UPDATED_ROLE
        defaultVisitedByShouldBeFound("role.notEquals=" + UPDATED_ROLE);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByRoleIsInShouldWork() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where role in DEFAULT_ROLE or UPDATED_ROLE
        defaultVisitedByShouldBeFound("role.in=" + DEFAULT_ROLE + "," + UPDATED_ROLE);

        // Get all the visitedByList where role equals to UPDATED_ROLE
        defaultVisitedByShouldNotBeFound("role.in=" + UPDATED_ROLE);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByRoleIsNullOrNotNull() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where role is not null
        defaultVisitedByShouldBeFound("role.specified=true");

        // Get all the visitedByList where role is null
        defaultVisitedByShouldNotBeFound("role.specified=false");
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByRoleContainsSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where role contains DEFAULT_ROLE
        defaultVisitedByShouldBeFound("role.contains=" + DEFAULT_ROLE);

        // Get all the visitedByList where role contains UPDATED_ROLE
        defaultVisitedByShouldNotBeFound("role.contains=" + UPDATED_ROLE);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByRoleNotContainsSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where role does not contain DEFAULT_ROLE
        defaultVisitedByShouldNotBeFound("role.doesNotContain=" + DEFAULT_ROLE);

        // Get all the visitedByList where role does not contain UPDATED_ROLE
        defaultVisitedByShouldBeFound("role.doesNotContain=" + UPDATED_ROLE);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByDesignationIsEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where designation equals to DEFAULT_DESIGNATION
        defaultVisitedByShouldBeFound("designation.equals=" + DEFAULT_DESIGNATION);

        // Get all the visitedByList where designation equals to UPDATED_DESIGNATION
        defaultVisitedByShouldNotBeFound("designation.equals=" + UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByDesignationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where designation not equals to DEFAULT_DESIGNATION
        defaultVisitedByShouldNotBeFound("designation.notEquals=" + DEFAULT_DESIGNATION);

        // Get all the visitedByList where designation not equals to UPDATED_DESIGNATION
        defaultVisitedByShouldBeFound("designation.notEquals=" + UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByDesignationIsInShouldWork() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where designation in DEFAULT_DESIGNATION or UPDATED_DESIGNATION
        defaultVisitedByShouldBeFound("designation.in=" + DEFAULT_DESIGNATION + "," + UPDATED_DESIGNATION);

        // Get all the visitedByList where designation equals to UPDATED_DESIGNATION
        defaultVisitedByShouldNotBeFound("designation.in=" + UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByDesignationIsNullOrNotNull() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where designation is not null
        defaultVisitedByShouldBeFound("designation.specified=true");

        // Get all the visitedByList where designation is null
        defaultVisitedByShouldNotBeFound("designation.specified=false");
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByDesignationContainsSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where designation contains DEFAULT_DESIGNATION
        defaultVisitedByShouldBeFound("designation.contains=" + DEFAULT_DESIGNATION);

        // Get all the visitedByList where designation contains UPDATED_DESIGNATION
        defaultVisitedByShouldNotBeFound("designation.contains=" + UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByDesignationNotContainsSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where designation does not contain DEFAULT_DESIGNATION
        defaultVisitedByShouldNotBeFound("designation.doesNotContain=" + DEFAULT_DESIGNATION);

        // Get all the visitedByList where designation does not contain UPDATED_DESIGNATION
        defaultVisitedByShouldBeFound("designation.doesNotContain=" + UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByVistdateIsEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where vistdate equals to DEFAULT_VISTDATE
        defaultVisitedByShouldBeFound("vistdate.equals=" + DEFAULT_VISTDATE);

        // Get all the visitedByList where vistdate equals to UPDATED_VISTDATE
        defaultVisitedByShouldNotBeFound("vistdate.equals=" + UPDATED_VISTDATE);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByVistdateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where vistdate not equals to DEFAULT_VISTDATE
        defaultVisitedByShouldNotBeFound("vistdate.notEquals=" + DEFAULT_VISTDATE);

        // Get all the visitedByList where vistdate not equals to UPDATED_VISTDATE
        defaultVisitedByShouldBeFound("vistdate.notEquals=" + UPDATED_VISTDATE);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByVistdateIsInShouldWork() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where vistdate in DEFAULT_VISTDATE or UPDATED_VISTDATE
        defaultVisitedByShouldBeFound("vistdate.in=" + DEFAULT_VISTDATE + "," + UPDATED_VISTDATE);

        // Get all the visitedByList where vistdate equals to UPDATED_VISTDATE
        defaultVisitedByShouldNotBeFound("vistdate.in=" + UPDATED_VISTDATE);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByVistdateIsNullOrNotNull() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where vistdate is not null
        defaultVisitedByShouldBeFound("vistdate.specified=true");

        // Get all the visitedByList where vistdate is null
        defaultVisitedByShouldNotBeFound("vistdate.specified=false");
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByVistdateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where vistdate is greater than or equal to DEFAULT_VISTDATE
        defaultVisitedByShouldBeFound("vistdate.greaterThanOrEqual=" + DEFAULT_VISTDATE);

        // Get all the visitedByList where vistdate is greater than or equal to UPDATED_VISTDATE
        defaultVisitedByShouldNotBeFound("vistdate.greaterThanOrEqual=" + UPDATED_VISTDATE);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByVistdateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where vistdate is less than or equal to DEFAULT_VISTDATE
        defaultVisitedByShouldBeFound("vistdate.lessThanOrEqual=" + DEFAULT_VISTDATE);

        // Get all the visitedByList where vistdate is less than or equal to SMALLER_VISTDATE
        defaultVisitedByShouldNotBeFound("vistdate.lessThanOrEqual=" + SMALLER_VISTDATE);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByVistdateIsLessThanSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where vistdate is less than DEFAULT_VISTDATE
        defaultVisitedByShouldNotBeFound("vistdate.lessThan=" + DEFAULT_VISTDATE);

        // Get all the visitedByList where vistdate is less than UPDATED_VISTDATE
        defaultVisitedByShouldBeFound("vistdate.lessThan=" + UPDATED_VISTDATE);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByVistdateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        // Get all the visitedByList where vistdate is greater than DEFAULT_VISTDATE
        defaultVisitedByShouldNotBeFound("vistdate.greaterThan=" + DEFAULT_VISTDATE);

        // Get all the visitedByList where vistdate is greater than SMALLER_VISTDATE
        defaultVisitedByShouldBeFound("vistdate.greaterThan=" + SMALLER_VISTDATE);
    }

    @Test
    @Transactional
    public void getAllVisitedBiesByProosalsIsEqualToSomething() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);
        Proosals proosals = ProosalsResourceIT.createEntity(em);
        em.persist(proosals);
        em.flush();
        visitedBy.setProosals(proosals);
        visitedByRepository.saveAndFlush(visitedBy);
        Long proosalsId = proosals.getId();

        // Get all the visitedByList where proosals equals to proosalsId
        defaultVisitedByShouldBeFound("proosalsId.equals=" + proosalsId);

        // Get all the visitedByList where proosals equals to proosalsId + 1
        defaultVisitedByShouldNotBeFound("proosalsId.equals=" + (proosalsId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultVisitedByShouldBeFound(String filter) throws Exception {
        restVisitedByMockMvc
            .perform(get("/api/visited-bies?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(visitedBy.getId().intValue())))
            .andExpect(jsonPath("$.[*].unitid").value(hasItem(DEFAULT_UNITID)))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].employeeid").value(hasItem(DEFAULT_EMPLOYEEID)))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION)))
            .andExpect(jsonPath("$.[*].vistdate").value(hasItem(DEFAULT_VISTDATE.toString())));

        // Check, that the count call also returns 1
        restVisitedByMockMvc
            .perform(get("/api/visited-bies/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultVisitedByShouldNotBeFound(String filter) throws Exception {
        restVisitedByMockMvc
            .perform(get("/api/visited-bies?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restVisitedByMockMvc
            .perform(get("/api/visited-bies/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingVisitedBy() throws Exception {
        // Get the visitedBy
        restVisitedByMockMvc.perform(get("/api/visited-bies/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVisitedBy() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        int databaseSizeBeforeUpdate = visitedByRepository.findAll().size();

        // Update the visitedBy
        VisitedBy updatedVisitedBy = visitedByRepository.findById(visitedBy.getId()).get();
        // Disconnect from session so that the updates on updatedVisitedBy are not directly saved in db
        em.detach(updatedVisitedBy);
        updatedVisitedBy
            .unitid(UPDATED_UNITID)
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .status(UPDATED_STATUS)
            .employeeid(UPDATED_EMPLOYEEID)
            .role(UPDATED_ROLE)
            .designation(UPDATED_DESIGNATION)
            .vistdate(UPDATED_VISTDATE);
        VisitedByDTO visitedByDTO = visitedByMapper.toDto(updatedVisitedBy);

        restVisitedByMockMvc
            .perform(
                put("/api/visited-bies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitedByDTO))
            )
            .andExpect(status().isOk());

        // Validate the VisitedBy in the database
        List<VisitedBy> visitedByList = visitedByRepository.findAll();
        assertThat(visitedByList).hasSize(databaseSizeBeforeUpdate);
        VisitedBy testVisitedBy = visitedByList.get(visitedByList.size() - 1);
        assertThat(testVisitedBy.getUnitid()).isEqualTo(UPDATED_UNITID);
        assertThat(testVisitedBy.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testVisitedBy.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testVisitedBy.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testVisitedBy.getEmployeeid()).isEqualTo(UPDATED_EMPLOYEEID);
        assertThat(testVisitedBy.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testVisitedBy.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testVisitedBy.getVistdate()).isEqualTo(UPDATED_VISTDATE);
    }

    @Test
    @Transactional
    public void updateNonExistingVisitedBy() throws Exception {
        int databaseSizeBeforeUpdate = visitedByRepository.findAll().size();

        // Create the VisitedBy
        VisitedByDTO visitedByDTO = visitedByMapper.toDto(visitedBy);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVisitedByMockMvc
            .perform(
                put("/api/visited-bies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(visitedByDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VisitedBy in the database
        List<VisitedBy> visitedByList = visitedByRepository.findAll();
        assertThat(visitedByList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVisitedBy() throws Exception {
        // Initialize the database
        visitedByRepository.saveAndFlush(visitedBy);

        int databaseSizeBeforeDelete = visitedByRepository.findAll().size();

        // Delete the visitedBy
        restVisitedByMockMvc
            .perform(delete("/api/visited-bies/{id}", visitedBy.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VisitedBy> visitedByList = visitedByRepository.findAll();
        assertThat(visitedByList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
