package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class FormuladtlTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Formuladtl.class);
        Formuladtl formuladtl1 = new Formuladtl();
        formuladtl1.setId(1L);
        Formuladtl formuladtl2 = new Formuladtl();
        formuladtl2.setId(formuladtl1.getId());
        assertThat(formuladtl1).isEqualTo(formuladtl2);
        formuladtl2.setId(2L);
        assertThat(formuladtl1).isNotEqualTo(formuladtl2);
        formuladtl1.setId(null);
        assertThat(formuladtl1).isNotEqualTo(formuladtl2);
    }
}
