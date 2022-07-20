package com.cvrs.backend.repository;

import com.cvrs.backend.entity.OccupationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccupationRepository extends JpaRepository<OccupationEntity, Long> {
    OccupationEntity findByName(String occupationName);
}
