package com.finca.ccwapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VisitedByMapperTest {
    private VisitedByMapper visitedByMapper;

    @BeforeEach
    public void setUp() {
        visitedByMapper = new VisitedByMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(visitedByMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(visitedByMapper.fromId(null)).isNull();
    }
}
