package com.cvrs.backend.service;

import com.cvrs.backend.entity.AdminEntity;
import com.cvrs.backend.service.base.IBaseService;

public interface IAdminService extends IBaseService<AdminEntity, Long> {

    AdminEntity findByUsername(String name);
}
