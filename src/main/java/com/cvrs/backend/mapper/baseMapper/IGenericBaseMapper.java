package com.cvrs.backend.mapper.baseMapper;

import java.util.List;

public interface IGenericBaseMapper<T, DTO> {
    DTO mapToDto(T entity);
    List<DTO> mapToDto(List<T> entities);

    T mapToEntity(DTO dto);
    List<T> mapToEntity(List<DTO> dtos);
}
