package com.finca.ccwapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.finca.ccwapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class DeliquencyTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Deliquency.class);
        Deliquency deliquency1 = new Deliquency();
        deliquency1.setId(1L);
        Deliquency deliquency2 = new Deliquency();
        deliquency2.setId(deliquency1.getId());
        assertThat(deliquency1).isEqualTo(deliquency2);
        deliquency2.setId(2L);
        assertThat(deliquency1).isNotEqualTo(deliquency2);
        deliquency1.setId(null);
        assertThat(deliquency1).isNotEqualTo(deliquency2);
    }
}
