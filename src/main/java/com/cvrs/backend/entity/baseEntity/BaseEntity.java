package com.cvrs.backend.entity.baseEntity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    private String name;

    @Column(name = "created_by", updatable = false)
    private Long createdBy;

    @Column(name = "updated_by", updatable = true)
    private Long updatedBy;

    @NotNull
    @Column(name = "created_date", updatable = false)
    private final Date createdDate = new Date();

}
