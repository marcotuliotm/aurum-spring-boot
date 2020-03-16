package com.aurum.process.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.aurum.process.domain.Case;

@Repository
public interface CaseRepository extends AbstractRepository<Case> {
	Page<Case> findBySearchEquals(String search, Pageable pageable);
}
