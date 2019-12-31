package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.FormulaHdr;
import com.mycompany.myapp.repository.FormulaHdrRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.FormulaHdr}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FormulaHdrResource {

    private final Logger log = LoggerFactory.getLogger(FormulaHdrResource.class);

    private static final String ENTITY_NAME = "formulaHdr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormulaHdrRepository formulaHdrRepository;

    public FormulaHdrResource(FormulaHdrRepository formulaHdrRepository) {
        this.formulaHdrRepository = formulaHdrRepository;
    }

    /**
     * {@code POST  /formula-hdrs} : Create a new formulaHdr.
     *
     * @param formulaHdr the formulaHdr to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formulaHdr, or with status {@code 400 (Bad Request)} if the formulaHdr has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/formula-hdrs")
    public ResponseEntity<FormulaHdr> createFormulaHdr(@Valid @RequestBody FormulaHdr formulaHdr) throws URISyntaxException {
        log.debug("REST request to save FormulaHdr : {}", formulaHdr);
        if (formulaHdr.getId() != null) {
            throw new BadRequestAlertException("A new formulaHdr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormulaHdr result = formulaHdrRepository.save(formulaHdr);
        return ResponseEntity.created(new URI("/api/formula-hdrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /formula-hdrs} : Updates an existing formulaHdr.
     *
     * @param formulaHdr the formulaHdr to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formulaHdr,
     * or with status {@code 400 (Bad Request)} if the formulaHdr is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formulaHdr couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/formula-hdrs")
    public ResponseEntity<FormulaHdr> updateFormulaHdr(@Valid @RequestBody FormulaHdr formulaHdr) throws URISyntaxException {
        log.debug("REST request to update FormulaHdr : {}", formulaHdr);
        if (formulaHdr.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FormulaHdr result = formulaHdrRepository.save(formulaHdr);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, formulaHdr.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /formula-hdrs} : get all the formulaHdrs.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formulaHdrs in body.
     */
    @GetMapping("/formula-hdrs")
    public List<FormulaHdr> getAllFormulaHdrs() {
        log.debug("REST request to get all FormulaHdrs");
        return formulaHdrRepository.findAll();
    }

    /**
     * {@code GET  /formula-hdrs/:id} : get the "id" formulaHdr.
     *
     * @param id the id of the formulaHdr to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formulaHdr, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/formula-hdrs/{id}")
    public ResponseEntity<FormulaHdr> getFormulaHdr(@PathVariable Long id) {
        log.debug("REST request to get FormulaHdr : {}", id);
        Optional<FormulaHdr> formulaHdr = formulaHdrRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(formulaHdr);
    }

    /**
     * {@code DELETE  /formula-hdrs/:id} : delete the "id" formulaHdr.
     *
     * @param id the id of the formulaHdr to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/formula-hdrs/{id}")
    public ResponseEntity<Void> deleteFormulaHdr(@PathVariable Long id) {
        log.debug("REST request to delete FormulaHdr : {}", id);
        formulaHdrRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
