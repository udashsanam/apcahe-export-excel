package com.cvrs.backend.entity;

import com.cvrs.backend.entity.baseEntity.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "manufacturing_company")
public class ManufacturingCompanyEntity extends BaseEntity {

    @NotNull
    @Column(name = "phone_num", unique = true)
    private String phoneNum;

    @OneToOne
    private LocationEntity locationEntity;

}
