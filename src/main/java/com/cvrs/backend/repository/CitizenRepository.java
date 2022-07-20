package com.cvrs.backend.repository;

import com.cvrs.backend.entity.CitizenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitizenRepository extends JpaRepository<CitizenEntity, Long> {
    CitizenEntity findByCitizenshipEquals(String citizenship);

    List<CitizenEntity> findAllByVaccinatedStatus(String statusCode);

    List<CitizenEntity> findAllByAgeCategoryEntityId(Long id);

    @Query("select c from CitizenEntity c where c.locationEntity.municipality = ?1 and c.vaccinatedStatus = ?2 and c.ageCategoryEntity.id = ?3")
    List<CitizenEntity> findAllByLocationEntityMunicipalityAndVaccinatedStatusAndAgeCategoryEntityId(String municipality, String status, Long ageCategoryId);

    @Query("select c from CitizenEntity c where c.locationEntity.municipality = ?1 and c.locationEntity.wardNo <> ?2 and c.vaccinatedStatus = ?3 and c.ageCategoryEntity.id = ?4")
    List<CitizenEntity> findAllByLocationEntityMunicipalityAndNotLocationEntityWardNoAndVaccinatedStatusAndAgeCategoryEntityId(String municipality, Integer wardNo, String status, Long ageCategoryId);

    @Query("select c from CitizenEntity c where c.locationEntity.municipality = ?1 and c.locationEntity.wardNo = ?2 and c.vaccinatedStatus = ?3 and c.ageCategoryEntity.id = ?4")
    List<CitizenEntity> findAllByLocationEntityMunicipalityAndLocationEntityWardNoAndVaccinatedStatusAndAgeCategoryEntityId(String municipality, Integer wardNo, String status, Long ageCategoryId);

    @Query("select c from CitizenEntity c where c.locationEntity.municipality = ?1 and c.locationEntity.wardNo = ?2 and c.priority = ?3 and c.vaccinatedStatus = ?4 and c.ageCategoryEntity.id = ?5")
    List<CitizenEntity> findAllByLocationEntityMunicipalityAndLocationEntityWardNoAndPriorityAndVaccinatedStatusAndAgeCategoryEntityId(String municipality, Integer wardNo, Boolean priority, String status, Long ageCategoryId);

    List<CitizenEntity> findAllByRegNum(String registrationNumber);

    CitizenEntity findByRegNum(String registrationNumber);

}
