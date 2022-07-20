package com.cvrs.backend.dto.CustomDto;

import com.cvrs.backend.dto.LocationDto;
import com.cvrs.backend.dto.ManufacturingCompanyDto;
import com.cvrs.backend.dto.VaccineDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VaccineRegisterDto {

    private VaccineDto vaccineDto;

    private ManufacturingCompanyDto manufacturingCompanyDto;

    private LocationDto manufacturingCompanyLocationDto;



}
