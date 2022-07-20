package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.VaccineToAgeCategoryDto;
import com.cvrs.backend.entity.VaccineToAgeCategoryEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VaccineToAgeCategoryMapper extends GenericBaseMapperImpl<VaccineToAgeCategoryEntity, VaccineToAgeCategoryDto> {

    private ModelMapper  modelMapper;


    public VaccineToAgeCategoryMapper(ModelMapper modelMapper) {
        super(modelMapper, VaccineToAgeCategoryEntity.class, VaccineToAgeCategoryDto.class);
        this.modelMapper = modelMapper;
    }
}
