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
@Table(name = "age_category")
public class AgeCategoryEntity extends BaseEntity {

    @Column(name = "range_str")
    private String rangeStr;

}
