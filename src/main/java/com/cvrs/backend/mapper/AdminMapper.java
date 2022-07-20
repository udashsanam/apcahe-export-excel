package com.cvrs.backend.mapper;


import com.cvrs.backend.dto.AdminDto;
import com.cvrs.backend.entity.AdminEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminMapper extends GenericBaseMapperImpl<AdminEntity, AdminDto> {
    private ModelMapper modelMapper;

    public AdminMapper(ModelMapper modelMapper) {
        super(modelMapper, AdminEntity.class, AdminDto.class);
        this.modelMapper = modelMapper;
    }
}
