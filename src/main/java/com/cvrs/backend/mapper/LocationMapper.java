package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.LocationDto;
import com.cvrs.backend.entity.LocationEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocationMapper extends GenericBaseMapperImpl<LocationEntity, LocationDto> {
    private ModelMapper modelMapper;

    public LocationMapper(ModelMapper modelMapper) {
        super(modelMapper, LocationEntity.class, LocationDto.class);
        this.modelMapper = modelMapper;
    }
}
