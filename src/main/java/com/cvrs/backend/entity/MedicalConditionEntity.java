package com.cvrs.backend.entity;


import com.cvrs.backend.entity.baseEntity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "medical_condition")
public class MedicalConditionEntity extends BaseEntity {

    private boolean serious;

}
