package com.cvrs.backend.dto;

import com.cvrs.backend.dto.baseDto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDto extends BaseDto {

    private Integer wardNo;

    private String municipality;

    private String district;

    private String zone;

    private String state;
}
