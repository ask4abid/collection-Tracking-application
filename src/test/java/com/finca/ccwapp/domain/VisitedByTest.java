package com.finca.ccwapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.finca.ccwapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class VisitedByTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VisitedBy.class);
        VisitedBy visitedBy1 = new VisitedBy();
        visitedBy1.setId(1L);
        VisitedBy visitedBy2 = new VisitedBy();
        visitedBy2.setId(visitedBy1.getId());
        assertThat(visitedBy1).isEqualTo(visitedBy2);
        visitedBy2.setId(2L);
        assertThat(visitedBy1).isNotEqualTo(visitedBy2);
        visitedBy1.setId(null);
        assertThat(visitedBy1).isNotEqualTo(visitedBy2);
    }
}
