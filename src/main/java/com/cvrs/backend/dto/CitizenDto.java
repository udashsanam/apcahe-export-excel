package com.cvrs.backend.dto;

import com.cvrs.backend.dto.baseDto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CitizenDto extends BaseDto {
    private String firstName;

    private String middleName;

    private String lastName;

    private String gender;

    private Date dob;

    private String citizenship;

    private String email;

    private String phoneNum;

    private String vaccinatedStatus;

    private Boolean priority;

    private String regNum;

    private Long vaccineId;

    private Long medicalConditionEntityId;

    private Long locationEntityId;

    private Long occupationEntityId;

    private Long ageCategoryEntityId;
}
