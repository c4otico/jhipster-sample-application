package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class FormulaHdrTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormulaHdr.class);
        FormulaHdr formulaHdr1 = new FormulaHdr();
        formulaHdr1.setId(1L);
        FormulaHdr formulaHdr2 = new FormulaHdr();
        formulaHdr2.setId(formulaHdr1.getId());
        assertThat(formulaHdr1).isEqualTo(formulaHdr2);
        formulaHdr2.setId(2L);
        assertThat(formulaHdr1).isNotEqualTo(formulaHdr2);
        formulaHdr1.setId(null);
        assertThat(formulaHdr1).isNotEqualTo(formulaHdr2);
    }
}
