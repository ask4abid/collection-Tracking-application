package com.finca.ccwapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.finca.ccwapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ProosalsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Proosals.class);
        Proosals proosals1 = new Proosals();
        proosals1.setId(1L);
        Proosals proosals2 = new Proosals();
        proosals2.setId(proosals1.getId());
        assertThat(proosals1).isEqualTo(proosals2);
        proosals2.setId(2L);
        assertThat(proosals1).isNotEqualTo(proosals2);
        proosals1.setId(null);
        assertThat(proosals1).isNotEqualTo(proosals2);
    }
}
