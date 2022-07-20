package com.cvrs.backend.service;

import com.cvrs.backend.dto.VaccineDto;
import com.cvrs.backend.entity.VaccineEntity;
import com.cvrs.backend.service.base.IBaseService;

import java.util.List;

public interface IVaccineService extends IBaseService<VaccineEntity, Long> {

    List<VaccineDto> findAllWithDistributionCenter(String center);


}
