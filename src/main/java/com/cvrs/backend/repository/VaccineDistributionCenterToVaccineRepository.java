package com.cvrs.backend.repository;

import com.cvrs.backend.entity.VaccineDistributionCenterToVaccineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineDistributionCenterToVaccineRepository extends JpaRepository<VaccineDistributionCenterToVaccineEntity, Long> {
}
