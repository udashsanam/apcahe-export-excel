package com.cvrs.backend.repository;

import com.cvrs.backend.entity.VaccinationLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationLogRepository extends JpaRepository<VaccinationLogEntity, Long> {

    List<VaccinationLogEntity> findAllByRegistrationNumberOrderByCreatedDateDesc(String registrationNumber);
}
