package com.finca.ccwapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.finca.ccwapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class VisitedByDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VisitedByDTO.class);
        VisitedByDTO visitedByDTO1 = new VisitedByDTO();
        visitedByDTO1.setId(1L);
        VisitedByDTO visitedByDTO2 = new VisitedByDTO();
        assertThat(visitedByDTO1).isNotEqualTo(visitedByDTO2);
        visitedByDTO2.setId(visitedByDTO1.getId());
        assertThat(visitedByDTO1).isEqualTo(visitedByDTO2);
        visitedByDTO2.setId(2L);
        assertThat(visitedByDTO1).isNotEqualTo(visitedByDTO2);
        visitedByDTO1.setId(null);
        assertThat(visitedByDTO1).isNotEqualTo(visitedByDTO2);
    }
}
