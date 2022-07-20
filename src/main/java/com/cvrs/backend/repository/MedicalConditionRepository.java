package com.cvrs.backend.repository;

import com.cvrs.backend.entity.MedicalConditionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalConditionRepository extends JpaRepository<MedicalConditionEntity, Long> {
    MedicalConditionEntity findByName(String medicalConditionName);
}

