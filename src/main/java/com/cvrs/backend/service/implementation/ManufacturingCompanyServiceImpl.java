package com.cvrs.backend.service.implementation;

import com.cvrs.backend.entity.ManufacturingCompanyEntity;
import com.cvrs.backend.repository.ManufacturingCompanyRepository;
import com.cvrs.backend.service.IManufacturingCompanyService;
import com.cvrs.backend.service.implementation.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class ManufacturingCompanyServiceImpl extends BaseServiceImpl<ManufacturingCompanyEntity, Long> implements com.cvrs.backend.service.IManufacturingCompanyService {
    private ManufacturingCompanyRepository manufacturingCompanyRepository;

    @Autowired
    public ManufacturingCompanyServiceImpl(JpaRepository<ManufacturingCompanyEntity, Long> repository, ManufacturingCompanyRepository manufacturingCompanyRepository) {
        super(repository);
        this.manufacturingCompanyRepository = manufacturingCompanyRepository;
    }
}
