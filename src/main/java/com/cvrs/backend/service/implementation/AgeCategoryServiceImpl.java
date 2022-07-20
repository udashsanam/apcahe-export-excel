package com.cvrs.backend.service.implementation;

import com.cvrs.backend.entity.AgeCategoryEntity;
import com.cvrs.backend.repository.AgeCategoryRepository;
import com.cvrs.backend.service.IAgeCategoryService;
import com.cvrs.backend.service.implementation.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class AgeCategoryServiceImpl extends BaseServiceImpl<AgeCategoryEntity, Long> implements IAgeCategoryService {
    private AgeCategoryRepository ageCategoryRepository;

    @Autowired
    public AgeCategoryServiceImpl(JpaRepository<AgeCategoryEntity, Long> repository, AgeCategoryRepository ageCategoryRepository) {
        super(repository);
        this.ageCategoryRepository = ageCategoryRepository;
    }
}
