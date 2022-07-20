package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.VaccineDistributionCenterDto;
import com.cvrs.backend.entity.VaccineDistributionCenterEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VaccineDistributionCenterMapper extends GenericBaseMapperImpl<VaccineDistributionCenterEntity, VaccineDistributionCenterDto> {

    private ModelMapper modelMapper;

    public VaccineDistributionCenterMapper(ModelMapper modelMapper) {
        super(modelMapper, VaccineDistributionCenterEntity.class, VaccineDistributionCenterDto.class);
        this.modelMapper = modelMapper;
    }
}
