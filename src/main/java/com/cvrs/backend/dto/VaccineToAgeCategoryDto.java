package com.cvrs.backend.dto;

import com.cvrs.backend.dto.baseDto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaccineToAgeCategoryDto extends BaseDto {
    private Long ageCategoryEntityId;

    private Long vaccineEntityId;
}
