package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.VaccineDistributionCenterToVaccineDto;
import com.cvrs.backend.entity.VaccineDistributionCenterToVaccineEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VaccineDistributionCenterToVaccineMapper extends GenericBaseMapperImpl<VaccineDistributionCenterToVaccineEntity, VaccineDistributionCenterToVaccineDto> {

    private ModelMapper modelMapper;

    public VaccineDistributionCenterToVaccineMapper(ModelMapper modelMapper) {
        super(modelMapper, VaccineDistributionCenterToVaccineEntity.class, VaccineDistributionCenterToVaccineDto.class);
        this.modelMapper = modelMapper;
    }
}
