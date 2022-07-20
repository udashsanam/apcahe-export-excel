package com.cvrs.backend.dto;


import com.cvrs.backend.dto.baseDto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto extends BaseDto {
    private String userName;

    private String phoneNum;

    private String password;
}
