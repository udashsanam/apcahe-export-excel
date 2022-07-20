package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.ManufacturingCompanyDto;
import com.cvrs.backend.entity.ManufacturingCompanyEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManufacturingCompanyMapper extends GenericBaseMapperImpl<ManufacturingCompanyEntity, ManufacturingCompanyDto> {
    private ModelMapper modelMapper;

    public ManufacturingCompanyMapper(ModelMapper modelMapper) {
        super(modelMapper, ManufacturingCompanyEntity.class, ManufacturingCompanyDto.class);
        this.modelMapper = modelMapper;
    }
}
