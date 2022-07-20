package com.cvrs.backend.repository;

import com.cvrs.backend.entity.VaccineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<VaccineEntity, Long> {
    VaccineEntity findByBatchNum(String batchNum);

    VaccineEntity findByName(String center);
}
