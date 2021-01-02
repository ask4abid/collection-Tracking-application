package com.finca.ccwapp.service.impl;

import com.finca.ccwapp.domain.VisitedBy;
import com.finca.ccwapp.repository.VisitedByRepository;
import com.finca.ccwapp.service.VisitedByService;
import com.finca.ccwapp.service.dto.VisitedByDTO;
import com.finca.ccwapp.service.mapper.VisitedByMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link VisitedBy}.
 */
@Service
@Transactional
public class VisitedByServiceImpl implements VisitedByService {
    private final Logger log = LoggerFactory.getLogger(VisitedByServiceImpl.class);

    private final VisitedByRepository visitedByRepository;

    private final VisitedByMapper visitedByMapper;

    public VisitedByServiceImpl(VisitedByRepository visitedByRepository, VisitedByMapper visitedByMapper) {
        this.visitedByRepository = visitedByRepository;
        this.visitedByMapper = visitedByMapper;
    }

    @Override
    public VisitedByDTO save(VisitedByDTO visitedByDTO) {
        log.debug("Request to save VisitedBy : {}", visitedByDTO);
        VisitedBy visitedBy = visitedByMapper.toEntity(visitedByDTO);
        visitedBy = visitedByRepository.save(visitedBy);
        return visitedByMapper.toDto(visitedBy);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VisitedByDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VisitedBies");
        return visitedByRepository.findAll(pageable).map(visitedByMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VisitedByDTO> findOne(Long id) {
        log.debug("Request to get VisitedBy : {}", id);
        return visitedByRepository.findById(id).map(visitedByMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete VisitedBy : {}", id);
        visitedByRepository.deleteById(id);
    }
}
