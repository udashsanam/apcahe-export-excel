package com.cvrs.backend.dto.CustomDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeDto {

    private String registrationNumber;

    private String vaccinatedStatus;

    private Long vaccineId;

    private String username;



}
