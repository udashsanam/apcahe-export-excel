package com.cvrs.backend.dto;

import com.cvrs.backend.dto.baseDto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalConditionDto extends BaseDto {

    private Boolean serious;

}
