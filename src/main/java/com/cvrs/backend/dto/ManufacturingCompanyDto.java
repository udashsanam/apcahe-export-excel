package com.cvrs.backend.dto;

import com.cvrs.backend.dto.baseDto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManufacturingCompanyDto extends BaseDto {
    private String phoneNum;

    private Long LocationEntityId;
}
