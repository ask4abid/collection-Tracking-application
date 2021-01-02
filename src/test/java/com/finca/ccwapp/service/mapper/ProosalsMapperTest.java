package com.finca.ccwapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProosalsMapperTest {
    private ProosalsMapper proosalsMapper;

    @BeforeEach
    public void setUp() {
        proosalsMapper = new ProosalsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(proosalsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proosalsMapper.fromId(null)).isNull();
    }
}
