package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.AgeCategoryDto;
import com.cvrs.backend.entity.AgeCategoryEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgeCategoryMapper extends GenericBaseMapperImpl<AgeCategoryEntity, AgeCategoryDto> {
    private ModelMapper modelMapper;

    public AgeCategoryMapper(ModelMapper modelMapper) {
        super(modelMapper, AgeCategoryEntity.class, AgeCategoryDto.class);
        this.modelMapper = modelMapper;
    }
}

