package com.cvrs.backend.repository;

import com.cvrs.backend.entity.VaccineToAgeCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineToAgeCategoryRepository extends JpaRepository<VaccineToAgeCategoryEntity, Long> {
}
