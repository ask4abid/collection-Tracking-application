package com.finca.ccwapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeliquencyMapperTest {
    private DeliquencyMapper deliquencyMapper;

    @BeforeEach
    public void setUp() {
        deliquencyMapper = new DeliquencyMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(deliquencyMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(deliquencyMapper.fromId(null)).isNull();
    }
}
