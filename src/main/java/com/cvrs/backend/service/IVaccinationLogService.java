package com.cvrs.backend.service;

import com.cvrs.backend.entity.VaccinationLogEntity;
import com.cvrs.backend.service.base.IBaseService;

import java.util.List;

public interface IVaccinationLogService extends IBaseService<VaccinationLogEntity, Long> {

    List<VaccinationLogEntity> findAllByRegistrationNumber(String registrationNumber);
}
