package com.cvrs.backend.repository;

import com.cvrs.backend.entity.ManufacturingCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturingCompanyRepository extends JpaRepository<ManufacturingCompanyEntity, Long> {
    ManufacturingCompanyEntity findByName(String manufacturingCompanyName);
}
