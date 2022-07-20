package com.cvrs.backend.dto.CustomDto;

import com.cvrs.backend.dto.CitizenDto;
import com.cvrs.backend.dto.VaccineDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VaccineReportCustomDto {

    private CitizenDto citizenDto;

    private VaccineDto vaccineDto;

    private List<Map<String, String>> vaccineLog;

    public VaccineReportCustomDto(CitizenDto citizenDto, VaccineDto vaccineDto) {
        this.vaccineDto = vaccineDto;
        this.citizenDto = citizenDto;
    }

}
