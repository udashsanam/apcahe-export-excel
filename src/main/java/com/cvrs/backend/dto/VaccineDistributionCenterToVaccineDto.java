package com.cvrs.backend.dto;

import com.cvrs.backend.dto.baseDto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaccineDistributionCenterToVaccineDto extends BaseDto {
    private Long vaccineDistributionCenterEntityId;

    private Long vaccineEntityId;
}
