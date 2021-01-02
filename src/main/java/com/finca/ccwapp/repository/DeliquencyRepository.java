package com.finca.ccwapp.repository;

import com.finca.ccwapp.domain.Deliquency;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Deliquency entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliquencyRepository extends JpaRepository<Deliquency, Long> {}
