package com.finca.ccwapp.service.impl;

import com.finca.ccwapp.domain.Visits;
import com.finca.ccwapp.repository.VisitsRepository;
import com.finca.ccwapp.service.VisitsService;
import com.finca.ccwapp.service.dto.VisitsDTO;
import com.finca.ccwapp.service.mapper.VisitsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Visits}.
 */
@Service
@Transactional
public class VisitsServiceImpl implements VisitsService {
    private final Logger log = LoggerFactory.getLogger(VisitsServiceImpl.class);

    private final VisitsRepository visitsRepository;

    private final VisitsMapper visitsMapper;

    public VisitsServiceImpl(VisitsRepository visitsRepository, VisitsMapper visitsMapper) {
        this.visitsRepository = visitsRepository;
        this.visitsMapper = visitsMapper;
    }

    @Override
    public VisitsDTO save(VisitsDTO visitsDTO) {
        log.debug("Request to save Visits : {}", visitsDTO);
        Visits visits = visitsMapper.toEntity(visitsDTO);
        visits = visitsRepository.save(visits);
        return visitsMapper.toDto(visits);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VisitsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Visits");
        return visitsRepository.findAll(pageable).map(visitsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VisitsDTO> findOne(Long id) {
        log.debug("Request to get Visits : {}", id);
        return visitsRepository.findById(id).map(visitsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Visits : {}", id);
        visitsRepository.deleteById(id);
    }
}
