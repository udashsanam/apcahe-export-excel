package com.cvrs.backend.service;

import com.cvrs.backend.dto.CustomDto.VaccineFormDto;
import com.cvrs.backend.dto.VaccineDistributionCenterToVaccineDto;
import com.cvrs.backend.entity.VaccineDistributionCenterToVaccineEntity;
import com.cvrs.backend.service.base.IBaseService;

import javax.mail.MessagingException;

public interface IVaccineDistributionCenterToVaccineService extends IBaseService<VaccineDistributionCenterToVaccineEntity, Long> {
    VaccineDistributionCenterToVaccineDto saveVaccineDetails(VaccineFormDto vaccineFormDto);

    boolean distributeVaccineNotification(VaccineFormDto vaccineFormDto) throws MessagingException;
}
