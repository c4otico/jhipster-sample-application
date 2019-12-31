package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Formuladtl;
import com.mycompany.myapp.repository.FormuladtlRepository;
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
 * Integration tests for the {@link FormuladtlResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class FormuladtlResourceIT {

    private static final Integer DEFAULT_DTLID = 1;
    private static final Integer UPDATED_DTLID = 2;

    private static final Integer DEFAULT_DTLSEQ = 1;
    private static final Integer UPDATED_DTLSEQ = 2;

    private static final String DEFAULT_ITEMCODE = "AAAAAAAAAA";
    private static final String UPDATED_ITEMCODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_QTY = 1;
    private static final Integer UPDATED_QTY = 2;

    private static final String DEFAULT_OLD_ITEMCODE = "AAAAAAAAAA";
    private static final String UPDATED_OLD_ITEMCODE = "BBBBBBBBBB";

    private static final String DEFAULT_REFXI_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REFXI_NUMBER = "BBBBBBBBBB";

    @Autowired
    private FormuladtlRepository formuladtlRepository;

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

    private MockMvc restFormuladtlMockMvc;

    private Formuladtl formuladtl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FormuladtlResource formuladtlResource = new FormuladtlResource(formuladtlRepository);
        this.restFormuladtlMockMvc = MockMvcBuilders.standaloneSetup(formuladtlResource)
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
    public static Formuladtl createEntity(EntityManager em) {
        Formuladtl formuladtl = new Formuladtl()
            .dtlid(DEFAULT_DTLID)
            .dtlseq(DEFAULT_DTLSEQ)
            .itemcode(DEFAULT_ITEMCODE)
            .qty(DEFAULT_QTY)
            .oldItemcode(DEFAULT_OLD_ITEMCODE)
            .refxiNumber(DEFAULT_REFXI_NUMBER);
        return formuladtl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Formuladtl createUpdatedEntity(EntityManager em) {
        Formuladtl formuladtl = new Formuladtl()
            .dtlid(UPDATED_DTLID)
            .dtlseq(UPDATED_DTLSEQ)
            .itemcode(UPDATED_ITEMCODE)
            .qty(UPDATED_QTY)
            .oldItemcode(UPDATED_OLD_ITEMCODE)
            .refxiNumber(UPDATED_REFXI_NUMBER);
        return formuladtl;
    }

    @BeforeEach
    public void initTest() {
        formuladtl = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormuladtl() throws Exception {
        int databaseSizeBeforeCreate = formuladtlRepository.findAll().size();

        // Create the Formuladtl
        restFormuladtlMockMvc.perform(post("/api/formuladtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formuladtl)))
            .andExpect(status().isCreated());

        // Validate the Formuladtl in the database
        List<Formuladtl> formuladtlList = formuladtlRepository.findAll();
        assertThat(formuladtlList).hasSize(databaseSizeBeforeCreate + 1);
        Formuladtl testFormuladtl = formuladtlList.get(formuladtlList.size() - 1);
        assertThat(testFormuladtl.getDtlid()).isEqualTo(DEFAULT_DTLID);
        assertThat(testFormuladtl.getDtlseq()).isEqualTo(DEFAULT_DTLSEQ);
        assertThat(testFormuladtl.getItemcode()).isEqualTo(DEFAULT_ITEMCODE);
        assertThat(testFormuladtl.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testFormuladtl.getOldItemcode()).isEqualTo(DEFAULT_OLD_ITEMCODE);
        assertThat(testFormuladtl.getRefxiNumber()).isEqualTo(DEFAULT_REFXI_NUMBER);
    }

    @Test
    @Transactional
    public void createFormuladtlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formuladtlRepository.findAll().size();

        // Create the Formuladtl with an existing ID
        formuladtl.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormuladtlMockMvc.perform(post("/api/formuladtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formuladtl)))
            .andExpect(status().isBadRequest());

        // Validate the Formuladtl in the database
        List<Formuladtl> formuladtlList = formuladtlRepository.findAll();
        assertThat(formuladtlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFormuladtls() throws Exception {
        // Initialize the database
        formuladtlRepository.saveAndFlush(formuladtl);

        // Get all the formuladtlList
        restFormuladtlMockMvc.perform(get("/api/formuladtls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formuladtl.getId().intValue())))
            .andExpect(jsonPath("$.[*].dtlid").value(hasItem(DEFAULT_DTLID)))
            .andExpect(jsonPath("$.[*].dtlseq").value(hasItem(DEFAULT_DTLSEQ)))
            .andExpect(jsonPath("$.[*].itemcode").value(hasItem(DEFAULT_ITEMCODE)))
            .andExpect(jsonPath("$.[*].qty").value(hasItem(DEFAULT_QTY)))
            .andExpect(jsonPath("$.[*].oldItemcode").value(hasItem(DEFAULT_OLD_ITEMCODE)))
            .andExpect(jsonPath("$.[*].refxiNumber").value(hasItem(DEFAULT_REFXI_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getFormuladtl() throws Exception {
        // Initialize the database
        formuladtlRepository.saveAndFlush(formuladtl);

        // Get the formuladtl
        restFormuladtlMockMvc.perform(get("/api/formuladtls/{id}", formuladtl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(formuladtl.getId().intValue()))
            .andExpect(jsonPath("$.dtlid").value(DEFAULT_DTLID))
            .andExpect(jsonPath("$.dtlseq").value(DEFAULT_DTLSEQ))
            .andExpect(jsonPath("$.itemcode").value(DEFAULT_ITEMCODE))
            .andExpect(jsonPath("$.qty").value(DEFAULT_QTY))
            .andExpect(jsonPath("$.oldItemcode").value(DEFAULT_OLD_ITEMCODE))
            .andExpect(jsonPath("$.refxiNumber").value(DEFAULT_REFXI_NUMBER));
    }

    @Test
    @Transactional
    public void getNonExistingFormuladtl() throws Exception {
        // Get the formuladtl
        restFormuladtlMockMvc.perform(get("/api/formuladtls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormuladtl() throws Exception {
        // Initialize the database
        formuladtlRepository.saveAndFlush(formuladtl);

        int databaseSizeBeforeUpdate = formuladtlRepository.findAll().size();

        // Update the formuladtl
        Formuladtl updatedFormuladtl = formuladtlRepository.findById(formuladtl.getId()).get();
        // Disconnect from session so that the updates on updatedFormuladtl are not directly saved in db
        em.detach(updatedFormuladtl);
        updatedFormuladtl
            .dtlid(UPDATED_DTLID)
            .dtlseq(UPDATED_DTLSEQ)
            .itemcode(UPDATED_ITEMCODE)
            .qty(UPDATED_QTY)
            .oldItemcode(UPDATED_OLD_ITEMCODE)
            .refxiNumber(UPDATED_REFXI_NUMBER);

        restFormuladtlMockMvc.perform(put("/api/formuladtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFormuladtl)))
            .andExpect(status().isOk());

        // Validate the Formuladtl in the database
        List<Formuladtl> formuladtlList = formuladtlRepository.findAll();
        assertThat(formuladtlList).hasSize(databaseSizeBeforeUpdate);
        Formuladtl testFormuladtl = formuladtlList.get(formuladtlList.size() - 1);
        assertThat(testFormuladtl.getDtlid()).isEqualTo(UPDATED_DTLID);
        assertThat(testFormuladtl.getDtlseq()).isEqualTo(UPDATED_DTLSEQ);
        assertThat(testFormuladtl.getItemcode()).isEqualTo(UPDATED_ITEMCODE);
        assertThat(testFormuladtl.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testFormuladtl.getOldItemcode()).isEqualTo(UPDATED_OLD_ITEMCODE);
        assertThat(testFormuladtl.getRefxiNumber()).isEqualTo(UPDATED_REFXI_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingFormuladtl() throws Exception {
        int databaseSizeBeforeUpdate = formuladtlRepository.findAll().size();

        // Create the Formuladtl

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormuladtlMockMvc.perform(put("/api/formuladtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formuladtl)))
            .andExpect(status().isBadRequest());

        // Validate the Formuladtl in the database
        List<Formuladtl> formuladtlList = formuladtlRepository.findAll();
        assertThat(formuladtlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFormuladtl() throws Exception {
        // Initialize the database
        formuladtlRepository.saveAndFlush(formuladtl);

        int databaseSizeBeforeDelete = formuladtlRepository.findAll().size();

        // Delete the formuladtl
        restFormuladtlMockMvc.perform(delete("/api/formuladtls/{id}", formuladtl.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Formuladtl> formuladtlList = formuladtlRepository.findAll();
        assertThat(formuladtlList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
