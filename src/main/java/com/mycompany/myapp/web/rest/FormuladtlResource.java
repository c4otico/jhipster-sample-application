package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Formuladtl;
import com.mycompany.myapp.repository.FormuladtlRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Formuladtl}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FormuladtlResource {

    private final Logger log = LoggerFactory.getLogger(FormuladtlResource.class);

    private static final String ENTITY_NAME = "formuladtl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormuladtlRepository formuladtlRepository;

    public FormuladtlResource(FormuladtlRepository formuladtlRepository) {
        this.formuladtlRepository = formuladtlRepository;
    }

    /**
     * {@code POST  /formuladtls} : Create a new formuladtl.
     *
     * @param formuladtl the formuladtl to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formuladtl, or with status {@code 400 (Bad Request)} if the formuladtl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/formuladtls")
    public ResponseEntity<Formuladtl> createFormuladtl(@Valid @RequestBody Formuladtl formuladtl) throws URISyntaxException {
        log.debug("REST request to save Formuladtl : {}", formuladtl);
        if (formuladtl.getId() != null) {
            throw new BadRequestAlertException("A new formuladtl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Formuladtl result = formuladtlRepository.save(formuladtl);
        return ResponseEntity.created(new URI("/api/formuladtls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /formuladtls} : Updates an existing formuladtl.
     *
     * @param formuladtl the formuladtl to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formuladtl,
     * or with status {@code 400 (Bad Request)} if the formuladtl is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formuladtl couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/formuladtls")
    public ResponseEntity<Formuladtl> updateFormuladtl(@Valid @RequestBody Formuladtl formuladtl) throws URISyntaxException {
        log.debug("REST request to update Formuladtl : {}", formuladtl);
        if (formuladtl.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Formuladtl result = formuladtlRepository.save(formuladtl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, formuladtl.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /formuladtls} : get all the formuladtls.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formuladtls in body.
     */
    @GetMapping("/formuladtls")
    public List<Formuladtl> getAllFormuladtls() {
        log.debug("REST request to get all Formuladtls");
        return formuladtlRepository.findAll();
    }

    /**
     * {@code GET  /formuladtls/:id} : get the "id" formuladtl.
     *
     * @param id the id of the formuladtl to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formuladtl, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/formuladtls/{id}")
    public ResponseEntity<Formuladtl> getFormuladtl(@PathVariable Long id) {
        log.debug("REST request to get Formuladtl : {}", id);
        Optional<Formuladtl> formuladtl = formuladtlRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(formuladtl);
    }

    /**
     * {@code DELETE  /formuladtls/:id} : delete the "id" formuladtl.
     *
     * @param id the id of the formuladtl to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/formuladtls/{id}")
    public ResponseEntity<Void> deleteFormuladtl(@PathVariable Long id) {
        log.debug("REST request to delete Formuladtl : {}", id);
        formuladtlRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
