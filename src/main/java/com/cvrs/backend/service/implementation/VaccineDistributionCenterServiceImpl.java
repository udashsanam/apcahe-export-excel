package com.cvrs.backend.service.implementation;

import com.cvrs.backend.entity.VaccineDistributionCenterEntity;
import com.cvrs.backend.repository.VaccineDistributionCenterRepository;
import com.cvrs.backend.service.IVaccineDistributionCenterService;
import com.cvrs.backend.service.implementation.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class VaccineDistributionCenterServiceImpl extends BaseServiceImpl<VaccineDistributionCenterEntity, Long> implements IVaccineDistributionCenterService {
    private VaccineDistributionCenterRepository vaccineDistributionCenterRepository;

    @Autowired
    public VaccineDistributionCenterServiceImpl(JpaRepository<VaccineDistributionCenterEntity, Long> repository, VaccineDistributionCenterRepository vaccineDistributionCenterRepository) {
        super(repository);
        this.vaccineDistributionCenterRepository = vaccineDistributionCenterRepository;
    }
}
