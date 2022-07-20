package com.cvrs.backend.dto.CustomDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardCustomDto {
    // citizen
    private String firstName;

    private String middleName;

    private String lastName;

    private String gender;

    private String dob;

    private String citizenship;

    private String email;

    private String phoneNum;

    private String vaccinatedStatus;

    private String regNum;

// location
    private Integer wardNo;

    private String municipality;

    private String district;

    private String zone;

    private String state;


    // occupation, age category and medical condition;

    private String occupation;

    private String medicalCondition;

    private String ageCategory;

    // vaccine details
    private Long vaccineId;

    private String vaccineName;

}
