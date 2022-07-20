package com.cvrs.backend.dto;

import com.cvrs.backend.dto.baseDto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VaccineDto extends BaseDto {
    private String batchNum;

    private Long units;

    private Date scheduleFor;

    private Long manufacturingCompanyEntityId;

}
