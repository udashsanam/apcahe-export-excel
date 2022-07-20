package com.cvrs.backend.service;

import com.cvrs.backend.dto.CitizenDto;
import com.cvrs.backend.dto.CustomDto.DashboardCustomDto;
import com.cvrs.backend.dto.CustomDto.FormDto;
import com.cvrs.backend.dto.CustomDto.ResponseDto;
import com.cvrs.backend.entity.CitizenEntity;
import com.cvrs.backend.service.base.IBaseService;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ICitizenService extends IBaseService<CitizenEntity, Long> {

    CitizenDto saveAllDetails(FormDto formDto);

    String dateInString(Date date);

    List<CitizenDto> findByStatusCode(String statusCode);

    List<CitizenDto> findByAgeCategory(Long id);

    List<CitizenDto> findAllByMunicipality(String municipality, Integer wardNo);

    List<DashboardCustomDto> findAllCitizenDetailsForDashboard();

    CitizenEntity findByCitizenship(String citizenship);

    List<CitizenEntity> findByRegistrationNumber(Long registrationNumber);

    Map<String, Object> generateRegistrationReport(CitizenDto citizenDto);

    CitizenEntity findByRegistrationNUmberStr(String registrationNumber);

}
