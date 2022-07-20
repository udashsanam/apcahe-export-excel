package com.cvrs.backend.entity;

import com.cvrs.backend.entity.baseEntity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "vaccine_distribution_center")
public class VaccineDistributionCenterEntity extends BaseEntity {

    @ManyToOne
    private LocationEntity locationEntity;

}
