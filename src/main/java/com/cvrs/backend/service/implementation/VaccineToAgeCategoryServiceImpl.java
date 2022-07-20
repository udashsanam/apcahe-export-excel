package com.cvrs.backend.service.implementation;

import com.cvrs.backend.entity.VaccineToAgeCategoryEntity;
import com.cvrs.backend.repository.VaccineToAgeCategoryRepository;
import com.cvrs.backend.service.IVaccineToAgeCategoryService;
import com.cvrs.backend.service.implementation.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class VaccineToAgeCategoryServiceImpl extends BaseServiceImpl<VaccineToAgeCategoryEntity, Long> implements IVaccineToAgeCategoryService {
    private VaccineToAgeCategoryRepository vaccineToAgeCategoryRepository;

    @Autowired
    public VaccineToAgeCategoryServiceImpl(JpaRepository<VaccineToAgeCategoryEntity, Long> repository, VaccineToAgeCategoryRepository vaccineToAgeCategoryRepository) {
        super(repository);
        this.vaccineToAgeCategoryRepository = vaccineToAgeCategoryRepository;
    }
}
