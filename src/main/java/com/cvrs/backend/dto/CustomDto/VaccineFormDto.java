package com.cvrs.backend.dto.CustomDto;

import com.cvrs.backend.dto.LocationDto;
import com.cvrs.backend.dto.ManufacturingCompanyDto;
import com.cvrs.backend.dto.VaccineDistributionCenterDto;
import com.cvrs.backend.dto.VaccineDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaccineFormDto {
    private VaccineDto vaccineDto;
    private ManufacturingCompanyDto manufacturingCompanyDto;
    private LocationDto companyLocationDto;
    private VaccineDistributionCenterDto vaccineDistributionCenterDto;
    private LocationDto centerLocationDto;
    private Long ageCategory;
}
