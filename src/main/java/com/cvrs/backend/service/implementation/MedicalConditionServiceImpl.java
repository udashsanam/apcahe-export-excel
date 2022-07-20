package com.cvrs.backend.service.implementation;

import com.cvrs.backend.entity.MedicalConditionEntity;
import com.cvrs.backend.repository.MedicalConditionRepository;
import com.cvrs.backend.service.IMedicalConditionService;
import com.cvrs.backend.service.implementation.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class MedicalConditionServiceImpl extends BaseServiceImpl<MedicalConditionEntity, Long> implements com.cvrs.backend.service.IMedicalConditionService {
    private MedicalConditionRepository medicalConditionRepository;

    @Autowired
    public MedicalConditionServiceImpl(JpaRepository<MedicalConditionEntity, Long> repository, MedicalConditionRepository medicalConditionRepository) {
        super(repository);
        this.medicalConditionRepository = medicalConditionRepository;
    }
}
