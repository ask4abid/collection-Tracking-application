package com.finca.ccwapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.finca.ccwapp.CcwApp;
import com.finca.ccwapp.domain.Proosals;
import com.finca.ccwapp.repository.ProosalsRepository;
import com.finca.ccwapp.service.ProosalsService;
import com.finca.ccwapp.service.dto.ProosalsDTO;
import com.finca.ccwapp.service.mapper.ProosalsMapper;
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
 * Integration tests for the {@link ProosalsResource} REST controller.
 */
@SpringBootTest(classes = CcwApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProosalsResourceIT {
    private static final String DEFAULT_UNITID = "AAAAAAAAAA";
    private static final String UPDATED_UNITID = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESSPROPOSAL = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSPROPOSAL = "BBBBBBBBBB";

    private static final String DEFAULT_SUBPROPOSAL = "AAAAAAAAAA";
    private static final String UPDATED_SUBPROPOSAL = "BBBBBBBBBB";

    private static final String DEFAULT_RELATIONID = "AAAAAAAAAA";
    private static final String UPDATED_RELATIONID = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MOBILENUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNTNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNTNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNTTITLE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNTTITLE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROFVISITS = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROFVISITS = "BBBBBBBBBB";

    private static final String DEFAULT_OUTSTANDINGAMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_OUTSTANDINGAMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_OUTSTANDINGPROFIT = "AAAAAAAAAA";
    private static final String UPDATED_OUTSTANDINGPROFIT = "BBBBBBBBBB";

    private static final String DEFAULT_ODDAYS = "AAAAAAAAAA";
    private static final String UPDATED_ODDAYS = "BBBBBBBBBB";

    private static final String DEFAULT_LOANOFFICER = "AAAAAAAAAA";
    private static final String UPDATED_LOANOFFICER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PROMISETOPAY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PROMISETOPAY = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_DELEQUENCYREASON = "AAAAAAAAAA";
    private static final String UPDATED_DELEQUENCYREASON = "BBBBBBBBBB";

    @Autowired
    private ProosalsRepository proosalsRepository;

    @Autowired
    private ProosalsMapper proosalsMapper;

    @Autowired
    private ProosalsService proosalsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProosalsMockMvc;

    private Proosals proosals;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Proosals createEntity(EntityManager em) {
        Proosals proosals = new Proosals()
            .unitid(DEFAULT_UNITID)
            .businessproposal(DEFAULT_BUSINESSPROPOSAL)
            .subproposal(DEFAULT_SUBPROPOSAL)
            .relationid(DEFAULT_RELATIONID)
            .mobilenumber(DEFAULT_MOBILENUMBER)
            .accountnumber(DEFAULT_ACCOUNTNUMBER)
            .accounttitle(DEFAULT_ACCOUNTTITLE)
            .numerofvisits(DEFAULT_NUMEROFVISITS)
            .outstandingamount(DEFAULT_OUTSTANDINGAMOUNT)
            .outstandingprofit(DEFAULT_OUTSTANDINGPROFIT)
            .oddays(DEFAULT_ODDAYS)
            .loanofficer(DEFAULT_LOANOFFICER)
            .promisetopay(DEFAULT_PROMISETOPAY)
            .remarks(DEFAULT_REMARKS)
            .delequencyreason(DEFAULT_DELEQUENCYREASON);
        return proosals;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Proosals createUpdatedEntity(EntityManager em) {
        Proosals proosals = new Proosals()
            .unitid(UPDATED_UNITID)
            .businessproposal(UPDATED_BUSINESSPROPOSAL)
            .subproposal(UPDATED_SUBPROPOSAL)
            .relationid(UPDATED_RELATIONID)
            .mobilenumber(UPDATED_MOBILENUMBER)
            .accountnumber(UPDATED_ACCOUNTNUMBER)
            .accounttitle(UPDATED_ACCOUNTTITLE)
            .numerofvisits(UPDATED_NUMEROFVISITS)
            .outstandingamount(UPDATED_OUTSTANDINGAMOUNT)
            .outstandingprofit(UPDATED_OUTSTANDINGPROFIT)
            .oddays(UPDATED_ODDAYS)
            .loanofficer(UPDATED_LOANOFFICER)
            .promisetopay(UPDATED_PROMISETOPAY)
            .remarks(UPDATED_REMARKS)
            .delequencyreason(UPDATED_DELEQUENCYREASON);
        return proosals;
    }

    @BeforeEach
    public void initTest() {
        proosals = createEntity(em);
    }

    @Test
    @Transactional
    public void createProosals() throws Exception {
        int databaseSizeBeforeCreate = proosalsRepository.findAll().size();
        // Create the Proosals
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(proosals);
        restProosalsMockMvc
            .perform(post("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isCreated());

        // Validate the Proosals in the database
        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeCreate + 1);
        Proosals testProosals = proosalsList.get(proosalsList.size() - 1);
        assertThat(testProosals.getUnitid()).isEqualTo(DEFAULT_UNITID);
        assertThat(testProosals.getBusinessproposal()).isEqualTo(DEFAULT_BUSINESSPROPOSAL);
        assertThat(testProosals.getSubproposal()).isEqualTo(DEFAULT_SUBPROPOSAL);
        assertThat(testProosals.getRelationid()).isEqualTo(DEFAULT_RELATIONID);
        assertThat(testProosals.getMobilenumber()).isEqualTo(DEFAULT_MOBILENUMBER);
        assertThat(testProosals.getAccountnumber()).isEqualTo(DEFAULT_ACCOUNTNUMBER);
        assertThat(testProosals.getAccounttitle()).isEqualTo(DEFAULT_ACCOUNTTITLE);
        assertThat(testProosals.getNumerofvisits()).isEqualTo(DEFAULT_NUMEROFVISITS);
        assertThat(testProosals.getOutstandingamount()).isEqualTo(DEFAULT_OUTSTANDINGAMOUNT);
        assertThat(testProosals.getOutstandingprofit()).isEqualTo(DEFAULT_OUTSTANDINGPROFIT);
        assertThat(testProosals.getOddays()).isEqualTo(DEFAULT_ODDAYS);
        assertThat(testProosals.getLoanofficer()).isEqualTo(DEFAULT_LOANOFFICER);
        assertThat(testProosals.getPromisetopay()).isEqualTo(DEFAULT_PROMISETOPAY);
        assertThat(testProosals.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testProosals.getDelequencyreason()).isEqualTo(DEFAULT_DELEQUENCYREASON);
    }

    @Test
    @Transactional
    public void createProosalsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proosalsRepository.findAll().size();

        // Create the Proosals with an existing ID
        proosals.setId(1L);
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(proosals);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProosalsMockMvc
            .perform(post("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Proosals in the database
        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUnitidIsRequired() throws Exception {
        int databaseSizeBeforeTest = proosalsRepository.findAll().size();
        // set the field null
        proosals.setUnitid(null);

        // Create the Proosals, which fails.
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(proosals);

        restProosalsMockMvc
            .perform(post("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isBadRequest());

        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBusinessproposalIsRequired() throws Exception {
        int databaseSizeBeforeTest = proosalsRepository.findAll().size();
        // set the field null
        proosals.setBusinessproposal(null);

        // Create the Proosals, which fails.
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(proosals);

        restProosalsMockMvc
            .perform(post("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isBadRequest());

        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSubproposalIsRequired() throws Exception {
        int databaseSizeBeforeTest = proosalsRepository.findAll().size();
        // set the field null
        proosals.setSubproposal(null);

        // Create the Proosals, which fails.
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(proosals);

        restProosalsMockMvc
            .perform(post("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isBadRequest());

        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRelationidIsRequired() throws Exception {
        int databaseSizeBeforeTest = proosalsRepository.findAll().size();
        // set the field null
        proosals.setRelationid(null);

        // Create the Proosals, which fails.
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(proosals);

        restProosalsMockMvc
            .perform(post("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isBadRequest());

        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMobilenumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = proosalsRepository.findAll().size();
        // set the field null
        proosals.setMobilenumber(null);

        // Create the Proosals, which fails.
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(proosals);

        restProosalsMockMvc
            .perform(post("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isBadRequest());

        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAccountnumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = proosalsRepository.findAll().size();
        // set the field null
        proosals.setAccountnumber(null);

        // Create the Proosals, which fails.
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(proosals);

        restProosalsMockMvc
            .perform(post("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isBadRequest());

        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAccounttitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = proosalsRepository.findAll().size();
        // set the field null
        proosals.setAccounttitle(null);

        // Create the Proosals, which fails.
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(proosals);

        restProosalsMockMvc
            .perform(post("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isBadRequest());

        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumerofvisitsIsRequired() throws Exception {
        int databaseSizeBeforeTest = proosalsRepository.findAll().size();
        // set the field null
        proosals.setNumerofvisits(null);

        // Create the Proosals, which fails.
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(proosals);

        restProosalsMockMvc
            .perform(post("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isBadRequest());

        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOutstandingamountIsRequired() throws Exception {
        int databaseSizeBeforeTest = proosalsRepository.findAll().size();
        // set the field null
        proosals.setOutstandingamount(null);

        // Create the Proosals, which fails.
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(proosals);

        restProosalsMockMvc
            .perform(post("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isBadRequest());

        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOutstandingprofitIsRequired() throws Exception {
        int databaseSizeBeforeTest = proosalsRepository.findAll().size();
        // set the field null
        proosals.setOutstandingprofit(null);

        // Create the Proosals, which fails.
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(proosals);

        restProosalsMockMvc
            .perform(post("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isBadRequest());

        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOddaysIsRequired() throws Exception {
        int databaseSizeBeforeTest = proosalsRepository.findAll().size();
        // set the field null
        proosals.setOddays(null);

        // Create the Proosals, which fails.
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(proosals);

        restProosalsMockMvc
            .perform(post("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isBadRequest());

        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLoanofficerIsRequired() throws Exception {
        int databaseSizeBeforeTest = proosalsRepository.findAll().size();
        // set the field null
        proosals.setLoanofficer(null);

        // Create the Proosals, which fails.
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(proosals);

        restProosalsMockMvc
            .perform(post("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isBadRequest());

        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRemarksIsRequired() throws Exception {
        int databaseSizeBeforeTest = proosalsRepository.findAll().size();
        // set the field null
        proosals.setRemarks(null);

        // Create the Proosals, which fails.
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(proosals);

        restProosalsMockMvc
            .perform(post("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isBadRequest());

        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDelequencyreasonIsRequired() throws Exception {
        int databaseSizeBeforeTest = proosalsRepository.findAll().size();
        // set the field null
        proosals.setDelequencyreason(null);

        // Create the Proosals, which fails.
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(proosals);

        restProosalsMockMvc
            .perform(post("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isBadRequest());

        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProosals() throws Exception {
        // Initialize the database
        proosalsRepository.saveAndFlush(proosals);

        // Get all the proosalsList
        restProosalsMockMvc
            .perform(get("/api/proosals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proosals.getId().intValue())))
            .andExpect(jsonPath("$.[*].unitid").value(hasItem(DEFAULT_UNITID)))
            .andExpect(jsonPath("$.[*].businessproposal").value(hasItem(DEFAULT_BUSINESSPROPOSAL)))
            .andExpect(jsonPath("$.[*].subproposal").value(hasItem(DEFAULT_SUBPROPOSAL)))
            .andExpect(jsonPath("$.[*].relationid").value(hasItem(DEFAULT_RELATIONID)))
            .andExpect(jsonPath("$.[*].mobilenumber").value(hasItem(DEFAULT_MOBILENUMBER)))
            .andExpect(jsonPath("$.[*].accountnumber").value(hasItem(DEFAULT_ACCOUNTNUMBER)))
            .andExpect(jsonPath("$.[*].accounttitle").value(hasItem(DEFAULT_ACCOUNTTITLE)))
            .andExpect(jsonPath("$.[*].numerofvisits").value(hasItem(DEFAULT_NUMEROFVISITS)))
            .andExpect(jsonPath("$.[*].outstandingamount").value(hasItem(DEFAULT_OUTSTANDINGAMOUNT)))
            .andExpect(jsonPath("$.[*].outstandingprofit").value(hasItem(DEFAULT_OUTSTANDINGPROFIT)))
            .andExpect(jsonPath("$.[*].oddays").value(hasItem(DEFAULT_ODDAYS)))
            .andExpect(jsonPath("$.[*].loanofficer").value(hasItem(DEFAULT_LOANOFFICER)))
            .andExpect(jsonPath("$.[*].promisetopay").value(hasItem(DEFAULT_PROMISETOPAY.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].delequencyreason").value(hasItem(DEFAULT_DELEQUENCYREASON)));
    }

    @Test
    @Transactional
    public void getProosals() throws Exception {
        // Initialize the database
        proosalsRepository.saveAndFlush(proosals);

        // Get the proosals
        restProosalsMockMvc
            .perform(get("/api/proosals/{id}", proosals.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(proosals.getId().intValue()))
            .andExpect(jsonPath("$.unitid").value(DEFAULT_UNITID))
            .andExpect(jsonPath("$.businessproposal").value(DEFAULT_BUSINESSPROPOSAL))
            .andExpect(jsonPath("$.subproposal").value(DEFAULT_SUBPROPOSAL))
            .andExpect(jsonPath("$.relationid").value(DEFAULT_RELATIONID))
            .andExpect(jsonPath("$.mobilenumber").value(DEFAULT_MOBILENUMBER))
            .andExpect(jsonPath("$.accountnumber").value(DEFAULT_ACCOUNTNUMBER))
            .andExpect(jsonPath("$.accounttitle").value(DEFAULT_ACCOUNTTITLE))
            .andExpect(jsonPath("$.numerofvisits").value(DEFAULT_NUMEROFVISITS))
            .andExpect(jsonPath("$.outstandingamount").value(DEFAULT_OUTSTANDINGAMOUNT))
            .andExpect(jsonPath("$.outstandingprofit").value(DEFAULT_OUTSTANDINGPROFIT))
            .andExpect(jsonPath("$.oddays").value(DEFAULT_ODDAYS))
            .andExpect(jsonPath("$.loanofficer").value(DEFAULT_LOANOFFICER))
            .andExpect(jsonPath("$.promisetopay").value(DEFAULT_PROMISETOPAY.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.delequencyreason").value(DEFAULT_DELEQUENCYREASON));
    }

    @Test
    @Transactional
    public void getNonExistingProosals() throws Exception {
        // Get the proosals
        restProosalsMockMvc.perform(get("/api/proosals/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProosals() throws Exception {
        // Initialize the database
        proosalsRepository.saveAndFlush(proosals);

        int databaseSizeBeforeUpdate = proosalsRepository.findAll().size();

        // Update the proosals
        Proosals updatedProosals = proosalsRepository.findById(proosals.getId()).get();
        // Disconnect from session so that the updates on updatedProosals are not directly saved in db
        em.detach(updatedProosals);
        updatedProosals
            .unitid(UPDATED_UNITID)
            .businessproposal(UPDATED_BUSINESSPROPOSAL)
            .subproposal(UPDATED_SUBPROPOSAL)
            .relationid(UPDATED_RELATIONID)
            .mobilenumber(UPDATED_MOBILENUMBER)
            .accountnumber(UPDATED_ACCOUNTNUMBER)
            .accounttitle(UPDATED_ACCOUNTTITLE)
            .numerofvisits(UPDATED_NUMEROFVISITS)
            .outstandingamount(UPDATED_OUTSTANDINGAMOUNT)
            .outstandingprofit(UPDATED_OUTSTANDINGPROFIT)
            .oddays(UPDATED_ODDAYS)
            .loanofficer(UPDATED_LOANOFFICER)
            .promisetopay(UPDATED_PROMISETOPAY)
            .remarks(UPDATED_REMARKS)
            .delequencyreason(UPDATED_DELEQUENCYREASON);
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(updatedProosals);

        restProosalsMockMvc
            .perform(put("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isOk());

        // Validate the Proosals in the database
        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeUpdate);
        Proosals testProosals = proosalsList.get(proosalsList.size() - 1);
        assertThat(testProosals.getUnitid()).isEqualTo(UPDATED_UNITID);
        assertThat(testProosals.getBusinessproposal()).isEqualTo(UPDATED_BUSINESSPROPOSAL);
        assertThat(testProosals.getSubproposal()).isEqualTo(UPDATED_SUBPROPOSAL);
        assertThat(testProosals.getRelationid()).isEqualTo(UPDATED_RELATIONID);
        assertThat(testProosals.getMobilenumber()).isEqualTo(UPDATED_MOBILENUMBER);
        assertThat(testProosals.getAccountnumber()).isEqualTo(UPDATED_ACCOUNTNUMBER);
        assertThat(testProosals.getAccounttitle()).isEqualTo(UPDATED_ACCOUNTTITLE);
        assertThat(testProosals.getNumerofvisits()).isEqualTo(UPDATED_NUMEROFVISITS);
        assertThat(testProosals.getOutstandingamount()).isEqualTo(UPDATED_OUTSTANDINGAMOUNT);
        assertThat(testProosals.getOutstandingprofit()).isEqualTo(UPDATED_OUTSTANDINGPROFIT);
        assertThat(testProosals.getOddays()).isEqualTo(UPDATED_ODDAYS);
        assertThat(testProosals.getLoanofficer()).isEqualTo(UPDATED_LOANOFFICER);
        assertThat(testProosals.getPromisetopay()).isEqualTo(UPDATED_PROMISETOPAY);
        assertThat(testProosals.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testProosals.getDelequencyreason()).isEqualTo(UPDATED_DELEQUENCYREASON);
    }

    @Test
    @Transactional
    public void updateNonExistingProosals() throws Exception {
        int databaseSizeBeforeUpdate = proosalsRepository.findAll().size();

        // Create the Proosals
        ProosalsDTO proosalsDTO = proosalsMapper.toDto(proosals);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProosalsMockMvc
            .perform(put("/api/proosals").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proosalsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Proosals in the database
        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProosals() throws Exception {
        // Initialize the database
        proosalsRepository.saveAndFlush(proosals);

        int databaseSizeBeforeDelete = proosalsRepository.findAll().size();

        // Delete the proosals
        restProosalsMockMvc
            .perform(delete("/api/proosals/{id}", proosals.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Proosals> proosalsList = proosalsRepository.findAll();
        assertThat(proosalsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
