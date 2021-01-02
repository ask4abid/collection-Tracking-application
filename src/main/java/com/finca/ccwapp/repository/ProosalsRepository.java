package com.finca.ccwapp.repository;

import com.finca.ccwapp.domain.Proosals;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Proosals entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProosalsRepository extends JpaRepository<Proosals, Long> {}
