package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.FormulaHdr;
import com.mycompany.myapp.repository.FormulaHdrRepository;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FormulaHdrResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class FormulaHdrResourceIT {

    private static final String DEFAULT_XI_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_XI_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_FRMLNAME = "AAAAAAAAAA";
    private static final String UPDATED_FRMLNAME = "BBBBBBBBBB";

    private static final String DEFAULT_FRML_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FRML_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PROFILE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PROFILE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_USER_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_BOOK_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_BOOK_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_MFG_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MFG_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_MFGLOCATION = "AAAAAAAA";
    private static final String UPDATED_MFGLOCATION = "BBBBBBBB";

    private static final String DEFAULT_CBALIASCODE = "AAAAAAAAAA";
    private static final String UPDATED_CBALIASCODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER = "BBBBBBBBBB";

    private static final String DEFAULT_BASE_APPLICATION = "AAAAAAAAAA";
    private static final String UPDATED_BASE_APPLICATION = "BBBBBBBBBB";

    @Autowired
    private FormulaHdrRepository formulaHdrRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restFormulaHdrMockMvc;

    private FormulaHdr formulaHdr;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FormulaHdrResource formulaHdrResource = new FormulaHdrResource(formulaHdrRepository);
        this.restFormulaHdrMockMvc = MockMvcBuilders.standaloneSetup(formulaHdrResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormulaHdr createEntity(EntityManager em) {
        FormulaHdr formulaHdr = new FormulaHdr()
            .xiNumber(DEFAULT_XI_NUMBER)
            .frmlname(DEFAULT_FRMLNAME)
            .frmlType(DEFAULT_FRML_TYPE)
            .profileNumber(DEFAULT_PROFILE_NUMBER)
            .userNumber(DEFAULT_USER_NUMBER)
            .bookNumber(DEFAULT_BOOK_NUMBER)
            .mfgNumber(DEFAULT_MFG_NUMBER)
            .mfglocation(DEFAULT_MFGLOCATION)
            .cbaliascode(DEFAULT_CBALIASCODE)
            .description(DEFAULT_DESCRIPTION)
            .customer(DEFAULT_CUSTOMER)
            .baseApplication(DEFAULT_BASE_APPLICATION);
        return formulaHdr;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormulaHdr createUpdatedEntity(EntityManager em) {
        FormulaHdr formulaHdr = new FormulaHdr()
            .xiNumber(UPDATED_XI_NUMBER)
            .frmlname(UPDATED_FRMLNAME)
            .frmlType(UPDATED_FRML_TYPE)
            .profileNumber(UPDATED_PROFILE_NUMBER)
            .userNumber(UPDATED_USER_NUMBER)
            .bookNumber(UPDATED_BOOK_NUMBER)
            .mfgNumber(UPDATED_MFG_NUMBER)
            .mfglocation(UPDATED_MFGLOCATION)
            .cbaliascode(UPDATED_CBALIASCODE)
            .description(UPDATED_DESCRIPTION)
            .customer(UPDATED_CUSTOMER)
            .baseApplication(UPDATED_BASE_APPLICATION);
        return formulaHdr;
    }

    @BeforeEach
    public void initTest() {
        formulaHdr = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormulaHdr() throws Exception {
        int databaseSizeBeforeCreate = formulaHdrRepository.findAll().size();

        // Create the FormulaHdr
        restFormulaHdrMockMvc.perform(post("/api/formula-hdrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaHdr)))
            .andExpect(status().isCreated());

        // Validate the FormulaHdr in the database
        List<FormulaHdr> formulaHdrList = formulaHdrRepository.findAll();
        assertThat(formulaHdrList).hasSize(databaseSizeBeforeCreate + 1);
        FormulaHdr testFormulaHdr = formulaHdrList.get(formulaHdrList.size() - 1);
        assertThat(testFormulaHdr.getXiNumber()).isEqualTo(DEFAULT_XI_NUMBER);
        assertThat(testFormulaHdr.getFrmlname()).isEqualTo(DEFAULT_FRMLNAME);
        assertThat(testFormulaHdr.getFrmlType()).isEqualTo(DEFAULT_FRML_TYPE);
        assertThat(testFormulaHdr.getProfileNumber()).isEqualTo(DEFAULT_PROFILE_NUMBER);
        assertThat(testFormulaHdr.getUserNumber()).isEqualTo(DEFAULT_USER_NUMBER);
        assertThat(testFormulaHdr.getBookNumber()).isEqualTo(DEFAULT_BOOK_NUMBER);
        assertThat(testFormulaHdr.getMfgNumber()).isEqualTo(DEFAULT_MFG_NUMBER);
        assertThat(testFormulaHdr.getMfglocation()).isEqualTo(DEFAULT_MFGLOCATION);
        assertThat(testFormulaHdr.getCbaliascode()).isEqualTo(DEFAULT_CBALIASCODE);
        assertThat(testFormulaHdr.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testFormulaHdr.getCustomer()).isEqualTo(DEFAULT_CUSTOMER);
        assertThat(testFormulaHdr.getBaseApplication()).isEqualTo(DEFAULT_BASE_APPLICATION);
    }

    @Test
    @Transactional
    public void createFormulaHdrWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formulaHdrRepository.findAll().size();

        // Create the FormulaHdr with an existing ID
        formulaHdr.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormulaHdrMockMvc.perform(post("/api/formula-hdrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaHdr)))
            .andExpect(status().isBadRequest());

        // Validate the FormulaHdr in the database
        List<FormulaHdr> formulaHdrList = formulaHdrRepository.findAll();
        assertThat(formulaHdrList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkXiNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = formulaHdrRepository.findAll().size();
        // set the field null
        formulaHdr.setXiNumber(null);

        // Create the FormulaHdr, which fails.

        restFormulaHdrMockMvc.perform(post("/api/formula-hdrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaHdr)))
            .andExpect(status().isBadRequest());

        List<FormulaHdr> formulaHdrList = formulaHdrRepository.findAll();
        assertThat(formulaHdrList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFrmlnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = formulaHdrRepository.findAll().size();
        // set the field null
        formulaHdr.setFrmlname(null);

        // Create the FormulaHdr, which fails.

        restFormulaHdrMockMvc.perform(post("/api/formula-hdrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaHdr)))
            .andExpect(status().isBadRequest());

        List<FormulaHdr> formulaHdrList = formulaHdrRepository.findAll();
        assertThat(formulaHdrList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFrmlTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = formulaHdrRepository.findAll().size();
        // set the field null
        formulaHdr.setFrmlType(null);

        // Create the FormulaHdr, which fails.

        restFormulaHdrMockMvc.perform(post("/api/formula-hdrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaHdr)))
            .andExpect(status().isBadRequest());

        List<FormulaHdr> formulaHdrList = formulaHdrRepository.findAll();
        assertThat(formulaHdrList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFormulaHdrs() throws Exception {
        // Initialize the database
        formulaHdrRepository.saveAndFlush(formulaHdr);

        // Get all the formulaHdrList
        restFormulaHdrMockMvc.perform(get("/api/formula-hdrs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formulaHdr.getId().intValue())))
            .andExpect(jsonPath("$.[*].xiNumber").value(hasItem(DEFAULT_XI_NUMBER)))
            .andExpect(jsonPath("$.[*].frmlname").value(hasItem(DEFAULT_FRMLNAME)))
            .andExpect(jsonPath("$.[*].frmlType").value(hasItem(DEFAULT_FRML_TYPE)))
            .andExpect(jsonPath("$.[*].profileNumber").value(hasItem(DEFAULT_PROFILE_NUMBER)))
            .andExpect(jsonPath("$.[*].userNumber").value(hasItem(DEFAULT_USER_NUMBER)))
            .andExpect(jsonPath("$.[*].bookNumber").value(hasItem(DEFAULT_BOOK_NUMBER)))
            .andExpect(jsonPath("$.[*].mfgNumber").value(hasItem(DEFAULT_MFG_NUMBER)))
            .andExpect(jsonPath("$.[*].mfglocation").value(hasItem(DEFAULT_MFGLOCATION)))
            .andExpect(jsonPath("$.[*].cbaliascode").value(hasItem(DEFAULT_CBALIASCODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].customer").value(hasItem(DEFAULT_CUSTOMER)))
            .andExpect(jsonPath("$.[*].baseApplication").value(hasItem(DEFAULT_BASE_APPLICATION)));
    }
    
    @Test
    @Transactional
    public void getFormulaHdr() throws Exception {
        // Initialize the database
        formulaHdrRepository.saveAndFlush(formulaHdr);

        // Get the formulaHdr
        restFormulaHdrMockMvc.perform(get("/api/formula-hdrs/{id}", formulaHdr.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(formulaHdr.getId().intValue()))
            .andExpect(jsonPath("$.xiNumber").value(DEFAULT_XI_NUMBER))
            .andExpect(jsonPath("$.frmlname").value(DEFAULT_FRMLNAME))
            .andExpect(jsonPath("$.frmlType").value(DEFAULT_FRML_TYPE))
            .andExpect(jsonPath("$.profileNumber").value(DEFAULT_PROFILE_NUMBER))
            .andExpect(jsonPath("$.userNumber").value(DEFAULT_USER_NUMBER))
            .andExpect(jsonPath("$.bookNumber").value(DEFAULT_BOOK_NUMBER))
            .andExpect(jsonPath("$.mfgNumber").value(DEFAULT_MFG_NUMBER))
            .andExpect(jsonPath("$.mfglocation").value(DEFAULT_MFGLOCATION))
            .andExpect(jsonPath("$.cbaliascode").value(DEFAULT_CBALIASCODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.customer").value(DEFAULT_CUSTOMER))
            .andExpect(jsonPath("$.baseApplication").value(DEFAULT_BASE_APPLICATION));
    }

    @Test
    @Transactional
    public void getNonExistingFormulaHdr() throws Exception {
        // Get the formulaHdr
        restFormulaHdrMockMvc.perform(get("/api/formula-hdrs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormulaHdr() throws Exception {
        // Initialize the database
        formulaHdrRepository.saveAndFlush(formulaHdr);

        int databaseSizeBeforeUpdate = formulaHdrRepository.findAll().size();

        // Update the formulaHdr
        FormulaHdr updatedFormulaHdr = formulaHdrRepository.findById(formulaHdr.getId()).get();
        // Disconnect from session so that the updates on updatedFormulaHdr are not directly saved in db
        em.detach(updatedFormulaHdr);
        updatedFormulaHdr
            .xiNumber(UPDATED_XI_NUMBER)
            .frmlname(UPDATED_FRMLNAME)
            .frmlType(UPDATED_FRML_TYPE)
            .profileNumber(UPDATED_PROFILE_NUMBER)
            .userNumber(UPDATED_USER_NUMBER)
            .bookNumber(UPDATED_BOOK_NUMBER)
            .mfgNumber(UPDATED_MFG_NUMBER)
            .mfglocation(UPDATED_MFGLOCATION)
            .cbaliascode(UPDATED_CBALIASCODE)
            .description(UPDATED_DESCRIPTION)
            .customer(UPDATED_CUSTOMER)
            .baseApplication(UPDATED_BASE_APPLICATION);

        restFormulaHdrMockMvc.perform(put("/api/formula-hdrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFormulaHdr)))
            .andExpect(status().isOk());

        // Validate the FormulaHdr in the database
        List<FormulaHdr> formulaHdrList = formulaHdrRepository.findAll();
        assertThat(formulaHdrList).hasSize(databaseSizeBeforeUpdate);
        FormulaHdr testFormulaHdr = formulaHdrList.get(formulaHdrList.size() - 1);
        assertThat(testFormulaHdr.getXiNumber()).isEqualTo(UPDATED_XI_NUMBER);
        assertThat(testFormulaHdr.getFrmlname()).isEqualTo(UPDATED_FRMLNAME);
        assertThat(testFormulaHdr.getFrmlType()).isEqualTo(UPDATED_FRML_TYPE);
        assertThat(testFormulaHdr.getProfileNumber()).isEqualTo(UPDATED_PROFILE_NUMBER);
        assertThat(testFormulaHdr.getUserNumber()).isEqualTo(UPDATED_USER_NUMBER);
        assertThat(testFormulaHdr.getBookNumber()).isEqualTo(UPDATED_BOOK_NUMBER);
        assertThat(testFormulaHdr.getMfgNumber()).isEqualTo(UPDATED_MFG_NUMBER);
        assertThat(testFormulaHdr.getMfglocation()).isEqualTo(UPDATED_MFGLOCATION);
        assertThat(testFormulaHdr.getCbaliascode()).isEqualTo(UPDATED_CBALIASCODE);
        assertThat(testFormulaHdr.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testFormulaHdr.getCustomer()).isEqualTo(UPDATED_CUSTOMER);
        assertThat(testFormulaHdr.getBaseApplication()).isEqualTo(UPDATED_BASE_APPLICATION);
    }

    @Test
    @Transactional
    public void updateNonExistingFormulaHdr() throws Exception {
        int databaseSizeBeforeUpdate = formulaHdrRepository.findAll().size();

        // Create the FormulaHdr

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormulaHdrMockMvc.perform(put("/api/formula-hdrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaHdr)))
            .andExpect(status().isBadRequest());

        // Validate the FormulaHdr in the database
        List<FormulaHdr> formulaHdrList = formulaHdrRepository.findAll();
        assertThat(formulaHdrList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFormulaHdr() throws Exception {
        // Initialize the database
        formulaHdrRepository.saveAndFlush(formulaHdr);

        int databaseSizeBeforeDelete = formulaHdrRepository.findAll().size();

        // Delete the formulaHdr
        restFormulaHdrMockMvc.perform(delete("/api/formula-hdrs/{id}", formulaHdr.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FormulaHdr> formulaHdrList = formulaHdrRepository.findAll();
        assertThat(formulaHdrList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
