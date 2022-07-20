package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.OccupationDto;
import com.cvrs.backend.entity.OccupationEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OccupationMapper extends GenericBaseMapperImpl<OccupationEntity, OccupationDto> {

    private ModelMapper modelMapper;

    public OccupationMapper(ModelMapper modelMapper) {
        super(modelMapper, OccupationEntity.class, OccupationDto.class);
        this.modelMapper = modelMapper;
    }
}
