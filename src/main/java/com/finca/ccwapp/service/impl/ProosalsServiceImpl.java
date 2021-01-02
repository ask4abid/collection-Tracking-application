package com.finca.ccwapp.service.impl;

import com.finca.ccwapp.domain.Proosals;
import com.finca.ccwapp.repository.ProosalsRepository;
import com.finca.ccwapp.service.ProosalsService;
import com.finca.ccwapp.service.dto.ProosalsDTO;
import com.finca.ccwapp.service.mapper.ProosalsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Proosals}.
 */
@Service
@Transactional
public class ProosalsServiceImpl implements ProosalsService {
    private final Logger log = LoggerFactory.getLogger(ProosalsServiceImpl.class);

    private final ProosalsRepository proosalsRepository;

    private final ProosalsMapper proosalsMapper;

    public ProosalsServiceImpl(ProosalsRepository proosalsRepository, ProosalsMapper proosalsMapper) {
        this.proosalsRepository = proosalsRepository;
        this.proosalsMapper = proosalsMapper;
    }

    @Override
    public ProosalsDTO save(ProosalsDTO proosalsDTO) {
        log.debug("Request to save Proosals : {}", proosalsDTO);
        Proosals proosals = proosalsMapper.toEntity(proosalsDTO);
        proosals = proosalsRepository.save(proosals);
        return proosalsMapper.toDto(proosals);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProosalsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Proosals");
        return proosalsRepository.findAll(pageable).map(proosalsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProosalsDTO> findOne(Long id) {
        log.debug("Request to get Proosals : {}", id);
        return proosalsRepository.findById(id).map(proosalsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Proosals : {}", id);
        proosalsRepository.deleteById(id);
    }
}
