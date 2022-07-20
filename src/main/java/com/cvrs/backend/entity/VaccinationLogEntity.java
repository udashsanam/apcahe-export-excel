package com.cvrs.backend.entity;

import com.cvrs.backend.entity.baseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vaccination_log")
public class VaccinationLogEntity extends BaseEntity {

    @Column(name = "admin_id",nullable = false)
    private Long adminId;

    @Column(name = "registration_number",nullable = false)
    private String registrationNumber;
}
