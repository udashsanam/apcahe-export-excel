package com.cvrs.backend.repository;

import com.cvrs.backend.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    LocationEntity findByWardNoAndMunicipality(Integer wardNo, String municipality);
}
