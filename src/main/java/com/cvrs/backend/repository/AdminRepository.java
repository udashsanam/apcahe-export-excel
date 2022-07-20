package com.cvrs.backend.repository;

import com.cvrs.backend.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
//    AdminEntity findAdminEntityByUserName(String username);

    AdminEntity findByUserName(String username);
}
