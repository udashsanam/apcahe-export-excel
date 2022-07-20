package com.cvrs.backend.repository;

import com.cvrs.backend.entity.VaccineDistributionCenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineDistributionCenterRepository extends JpaRepository<VaccineDistributionCenterEntity, Long> {
    VaccineDistributionCenterEntity findByName(String distributionName);
}
