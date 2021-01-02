package com.finca.ccwapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VisitsMapperTest {
    private VisitsMapper visitsMapper;

    @BeforeEach
    public void setUp() {
        visitsMapper = new VisitsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(visitsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(visitsMapper.fromId(null)).isNull();
    }
}
