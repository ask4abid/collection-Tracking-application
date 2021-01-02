package com.finca.ccwapp.service.impl;

import com.finca.ccwapp.domain.Deliquency;
import com.finca.ccwapp.repository.DeliquencyRepository;
import com.finca.ccwapp.service.DeliquencyService;
import com.finca.ccwapp.service.dto.DeliquencyDTO;
import com.finca.ccwapp.service.mapper.DeliquencyMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Deliquency}.
 */
@Service
@Transactional
public class DeliquencyServiceImpl implements DeliquencyService {
    private final Logger log = LoggerFactory.getLogger(DeliquencyServiceImpl.class);

    private final DeliquencyRepository deliquencyRepository;

    private final DeliquencyMapper deliquencyMapper;

    public DeliquencyServiceImpl(DeliquencyRepository deliquencyRepository, DeliquencyMapper deliquencyMapper) {
        this.deliquencyRepository = deliquencyRepository;
        this.deliquencyMapper = deliquencyMapper;
    }

    @Override
    public DeliquencyDTO save(DeliquencyDTO deliquencyDTO) {
        log.debug("Request to save Deliquency : {}", deliquencyDTO);
        Deliquency deliquency = deliquencyMapper.toEntity(deliquencyDTO);
        deliquency = deliquencyRepository.save(deliquency);
        return deliquencyMapper.toDto(deliquency);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeliquencyDTO> findAll() {
        log.debug("Request to get all Deliquencies");
        return deliquencyRepository.findAll().stream().map(deliquencyMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DeliquencyDTO> findOne(Long id) {
        log.debug("Request to get Deliquency : {}", id);
        return deliquencyRepository.findById(id).map(deliquencyMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Deliquency : {}", id);
        deliquencyRepository.deleteById(id);
    }
}
