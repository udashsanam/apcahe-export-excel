package com.cvrs.backend.controller;

import com.cvrs.backend.controller.base.BaseController;
import com.cvrs.backend.dto.CustomDto.ResponseDto;
import com.cvrs.backend.entity.AdminEntity;
import com.cvrs.backend.repository.AdminRepository;
import com.cvrs.backend.security.jwt.JwtToken;
import com.cvrs.backend.security.model.JwtRequest;
import com.cvrs.backend.security.model.JwtResponse;
import com.cvrs.backend.security.services.CvrsUserDetailsService;
import com.cvrs.backend.util.APIConstant;
import com.cvrs.backend.util.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIConstant.API)
public class AuthenticationController extends BaseController {

    private AuthenticationManager authenticationManager;

    private JwtToken jwtToken;

    private CvrsUserDetailsService cvrsUserDetailsService;

    private AdminRepository adminRepository;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtToken jwtToken, CvrsUserDetailsService cvrsUserDetailsService, AdminRepository adminRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtToken = jwtToken;
        this.cvrsUserDetailsService = cvrsUserDetailsService;
        this.adminRepository = adminRepository;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDto> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception{
        Boolean checkPassword = true;
        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

            AdminEntity adminEntity = adminRepository.findByUserName(authenticationRequest.getUsername());
            if(adminEntity == null) {
                return new ResponseEntity<>(new ResponseDto("Username not found!!"), HttpStatus.NOT_FOUND);
            }
            checkPassword = authenticate(adminEntity, authenticationRequest.getPassword());

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        if(checkPassword == false) {
            return new ResponseEntity<>(new ResponseDto("Password do not match!!"), HttpStatus.BAD_REQUEST);
        }

        final UserDetails userDetails = cvrsUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtToken.generateToken(userDetails);

//        return new ResponseEntity<>(new ResponseDto("Authentication Successful", new JwtResponse(token)), HttpStatus.OK);
        return new ResponseEntity<>(new ResponseDto("Successfully login",new JwtResponse(token)),HttpStatus.OK);

    }

    private boolean authenticate(final AdminEntity adminEntity, String password) {
        if (BCryptUtil.match(password, adminEntity.getPassword())) {
            return true;
        } else {
            return false;
        }
    }


}
