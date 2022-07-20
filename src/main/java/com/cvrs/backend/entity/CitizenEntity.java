package com.cvrs.backend.entity;

import com.cvrs.backend.entity.baseEntity.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "citizen")
public class CitizenEntity extends BaseEntity {

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    private String gender;

    @NotNull
    private Date dob;

    @NotNull
    @Column(unique = true)
    private String citizenship;

    @NotNull
    private String email;

    @NotNull
    @Column(name = "phone_num", unique = true)
    private String phoneNum;

    @Column(name = "vaccinated_status")
    private String vaccinatedStatus;

    private Boolean priority;

    @Column(unique = true)
    private String regNum;

    @OneToOne
    private VaccineEntity vaccineEntity;

    @ManyToOne
    @NotNull
    private MedicalConditionEntity medicalConditionEntity;

    @ManyToOne
    @NotNull
    private LocationEntity locationEntity;

    @ManyToOne
    @NotNull
    private OccupationEntity occupationEntity;

    @ManyToOne
    @NotNull
    private AgeCategoryEntity ageCategoryEntity;

}
