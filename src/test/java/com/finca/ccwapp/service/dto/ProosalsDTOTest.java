package com.finca.ccwapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.finca.ccwapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ProosalsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProosalsDTO.class);
        ProosalsDTO proosalsDTO1 = new ProosalsDTO();
        proosalsDTO1.setId(1L);
        ProosalsDTO proosalsDTO2 = new ProosalsDTO();
        assertThat(proosalsDTO1).isNotEqualTo(proosalsDTO2);
        proosalsDTO2.setId(proosalsDTO1.getId());
        assertThat(proosalsDTO1).isEqualTo(proosalsDTO2);
        proosalsDTO2.setId(2L);
        assertThat(proosalsDTO1).isNotEqualTo(proosalsDTO2);
        proosalsDTO1.setId(null);
        assertThat(proosalsDTO1).isNotEqualTo(proosalsDTO2);
    }
}
