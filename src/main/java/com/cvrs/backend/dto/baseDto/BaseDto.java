package com.cvrs.backend.dto.baseDto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BaseDto implements Serializable {

    private Long id;

    private String name;

    private Long createdBy;

    private Long updatedBy;

    private Date createdDate;

}
