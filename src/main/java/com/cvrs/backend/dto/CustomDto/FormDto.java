package com.cvrs.backend.dto.CustomDto;

import com.cvrs.backend.dto.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormDto {
    private CitizenDto citizenDto;
    private LocationDto locationDto;
    private OccupationDto occupationDto;
    private MedicalConditionDto medicalConditionDto;
//    private VaccineDto vaccineDto;
    private AgeCategoryDto ageCategoryDto;
}
