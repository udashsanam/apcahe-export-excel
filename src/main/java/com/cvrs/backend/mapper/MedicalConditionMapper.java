package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.MedicalConditionDto;
import com.cvrs.backend.entity.MedicalConditionEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicalConditionMapper extends GenericBaseMapperImpl<MedicalConditionEntity, MedicalConditionDto> {

    private ModelMapper modelMapper;

    public MedicalConditionMapper(ModelMapper modelMapper) {
        super(modelMapper, MedicalConditionEntity.class, MedicalConditionDto.class);
        this.modelMapper = modelMapper;
    }
}
