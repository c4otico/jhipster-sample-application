package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Formuladtl;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Formuladtl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormuladtlRepository extends JpaRepository<Formuladtl, Long> {

}
