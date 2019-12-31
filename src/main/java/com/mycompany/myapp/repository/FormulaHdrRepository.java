package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.FormulaHdr;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FormulaHdr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormulaHdrRepository extends JpaRepository<FormulaHdr, Long> {

}
