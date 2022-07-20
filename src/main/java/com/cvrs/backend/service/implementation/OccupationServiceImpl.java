package com.cvrs.backend.service.implementation;

import com.cvrs.backend.entity.OccupationEntity;
import com.cvrs.backend.repository.OccupationRepository;
import com.cvrs.backend.service.IOccupationService;
import com.cvrs.backend.service.implementation.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class OccupationServiceImpl extends BaseServiceImpl<OccupationEntity, Long> implements com.cvrs.backend.service.IOccupationService {
    private OccupationRepository occupationRepository;

    @Autowired
    public OccupationServiceImpl(JpaRepository<OccupationEntity, Long> repository, OccupationRepository occupationRepository) {
        super(repository);
        this.occupationRepository = occupationRepository;
    }
}
