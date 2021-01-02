package com.finca.ccwapp.service;

import com.finca.ccwapp.domain.*; // for static metamodels
import com.finca.ccwapp.domain.VisitedBy;
import com.finca.ccwapp.repository.VisitedByRepository;
import com.finca.ccwapp.service.dto.VisitedByCriteria;
import com.finca.ccwapp.service.dto.VisitedByDTO;
import com.finca.ccwapp.service.mapper.VisitedByMapper;
import io.github.jhipster.service.QueryService;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for executing complex queries for {@link VisitedBy} entities in the database.
 * The main input is a {@link VisitedByCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VisitedByDTO} or a {@link Page} of {@link VisitedByDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VisitedByQueryService extends QueryService<VisitedBy> {
    private final Logger log = LoggerFactory.getLogger(VisitedByQueryService.class);

    private final VisitedByRepository visitedByRepository;

    private final VisitedByMapper visitedByMapper;

    public VisitedByQueryService(VisitedByRepository visitedByRepository, VisitedByMapper visitedByMapper) {
        this.visitedByRepository = visitedByRepository;
        this.visitedByMapper = visitedByMapper;
    }

    /**
     * Return a {@link List} of {@link VisitedByDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VisitedByDTO> findByCriteria(VisitedByCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<VisitedBy> specification = createSpecification(criteria);
        return visitedByMapper.toDto(visitedByRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link VisitedByDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VisitedByDTO> findByCriteria(VisitedByCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<VisitedBy> specification = createSpecification(criteria);
        return visitedByRepository.findAll(specification, page).map(visitedByMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VisitedByCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<VisitedBy> specification = createSpecification(criteria);
        return visitedByRepository.count(specification);
    }

    /**
     * Function to convert {@link VisitedByCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<VisitedBy> createSpecification(VisitedByCriteria criteria) {
        Specification<VisitedBy> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), VisitedBy_.id));
            }
            if (criteria.getUnitid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUnitid(), VisitedBy_.unitid));
            }
            if (criteria.getFirstname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstname(), VisitedBy_.firstname));
            }
            if (criteria.getLastname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastname(), VisitedBy_.lastname));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), VisitedBy_.status));
            }
            if (criteria.getEmployeeid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployeeid(), VisitedBy_.employeeid));
            }
            if (criteria.getRole() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRole(), VisitedBy_.role));
            }
            if (criteria.getDesignation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDesignation(), VisitedBy_.designation));
            }
            if (criteria.getVistdate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVistdate(), VisitedBy_.vistdate));
            }
            if (criteria.getProosalsId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getProosalsId(),
                            root -> root.join(VisitedBy_.proosals, JoinType.LEFT).get(Proosals_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
