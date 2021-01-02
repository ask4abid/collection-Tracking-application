package com.finca.ccwapp.repository;

import com.finca.ccwapp.domain.VisitedBy;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VisitedBy entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VisitedByRepository extends JpaRepository<VisitedBy, Long>, JpaSpecificationExecutor<VisitedBy> {}
