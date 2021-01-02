package com.finca.ccwapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.finca.ccwapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class DeliquencyDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliquencyDTO.class);
        DeliquencyDTO deliquencyDTO1 = new DeliquencyDTO();
        deliquencyDTO1.setId(1L);
        DeliquencyDTO deliquencyDTO2 = new DeliquencyDTO();
        assertThat(deliquencyDTO1).isNotEqualTo(deliquencyDTO2);
        deliquencyDTO2.setId(deliquencyDTO1.getId());
        assertThat(deliquencyDTO1).isEqualTo(deliquencyDTO2);
        deliquencyDTO2.setId(2L);
        assertThat(deliquencyDTO1).isNotEqualTo(deliquencyDTO2);
        deliquencyDTO1.setId(null);
        assertThat(deliquencyDTO1).isNotEqualTo(deliquencyDTO2);
    }
}
