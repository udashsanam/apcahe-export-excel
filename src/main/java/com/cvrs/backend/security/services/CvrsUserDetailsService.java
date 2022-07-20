package com.cvrs.backend.security.services;

import com.cvrs.backend.entity.AdminEntity;
import com.cvrs.backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CvrsUserDetailsService implements UserDetailsService {

    private AdminRepository adminRepository;

    @Autowired
    public CvrsUserDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        AdminEntity admin = adminRepository.findByUserName(userName);
        if(admin != null){
        return new User(admin.getUserName(), admin.getPassword(), new ArrayList<>());

//        return new UserDetailsImpl(admin.get().getUserName(), admin.get().getPassword());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + userName);
        }
    }
}
