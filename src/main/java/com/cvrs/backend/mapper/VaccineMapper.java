package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.VaccineDto;
import com.cvrs.backend.entity.VaccineEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VaccineMapper extends GenericBaseMapperImpl<VaccineEntity, VaccineDto> {

    private ModelMapper modelMapper;

    public VaccineMapper(ModelMapper modelMapper) {
        super(modelMapper, VaccineEntity.class, VaccineDto.class);
        this.modelMapper = modelMapper;
    }
}
