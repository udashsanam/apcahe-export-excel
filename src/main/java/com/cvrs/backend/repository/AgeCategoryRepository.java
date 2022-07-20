package com.cvrs.backend.repository;

import com.cvrs.backend.entity.AgeCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgeCategoryRepository extends JpaRepository<AgeCategoryEntity, Long> {
}
