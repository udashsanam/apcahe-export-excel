package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.CitizenDto;
import com.cvrs.backend.entity.CitizenEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CitizenMapper extends GenericBaseMapperImpl<CitizenEntity, CitizenDto> {
    private  ModelMapper modelMapper;

    public CitizenMapper(ModelMapper modelMapper) {
        super(modelMapper, CitizenEntity.class, CitizenDto.class);
        this.modelMapper = modelMapper;
    }
}
