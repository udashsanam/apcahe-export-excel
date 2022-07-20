package com.cvrs.backend.service.implementation;

import com.cvrs.backend.entity.AdminEntity;
import com.cvrs.backend.repository.AdminRepository;
import com.cvrs.backend.service.IAdminService;
import com.cvrs.backend.service.implementation.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class AdminServiceImpl extends BaseServiceImpl<AdminEntity, Long> implements IAdminService {
    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(JpaRepository<AdminEntity, Long> repository, AdminRepository adminRepository) {
        super(repository);
        this.adminRepository = adminRepository;
    }


    @Override
    public AdminEntity findByUsername(String name) {
        return adminRepository.findByUserName(name);
    }
}
