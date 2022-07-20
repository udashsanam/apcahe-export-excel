package com.cvrs.backend.entity;

import com.cvrs.backend.entity.baseEntity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "location")
public class LocationEntity extends BaseEntity {

    @Column(name = "ward_no")
    private Integer wardNo;

    private String municipality;

    private String district;

    private String zone;

    private String state;

}
