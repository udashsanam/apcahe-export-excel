package com.cvrs.backend.controller;

import com.cvrs.backend.controller.base.BaseController;
import com.cvrs.backend.dto.*;
import com.cvrs.backend.dto.CustomDto.ContactDto;
import com.cvrs.backend.dto.CustomDto.ResponseDto;
import com.cvrs.backend.dto.CustomDto.VaccineFormDto;
import com.cvrs.backend.dto.CustomDto.VaccineRegisterDto;
import com.cvrs.backend.entity.AdminEntity;
import com.cvrs.backend.exception.NotFoundException;
import com.cvrs.backend.exception.NotSavedException;
import com.cvrs.backend.mapper.AdminMapper;
import com.cvrs.backend.service.IAdminService;
import com.cvrs.backend.service.implementation.CvrsMailService;
import com.cvrs.backend.util.APIConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping(APIConstant.ADMIN)
public class AdminController extends BaseController {

    private IAdminService adminService;
    private AdminMapper adminMapper;
    private PasswordEncoder passwordEncoder;
    private CvrsMailService cvrsMailService;


    @Autowired
    public AdminController(IAdminService adminService, AdminMapper adminMapper, PasswordEncoder passwordEncoder,
                           CvrsMailService cvrsMailService) {
        this.adminService = adminService;
        this.adminMapper = adminMapper;
        this.passwordEncoder = passwordEncoder;
        this.cvrsMailService = cvrsMailService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody AdminDto adminDto){
        adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        try {
            adminService.save(adminMapper.mapToEntity(adminDto));
        }catch (Exception exception){
            throw new NotSavedException("Error saving Admin", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully saved", adminDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody AdminDto adminDto){
        adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));

        try {
            adminService.save(adminMapper.mapToEntity(adminDto));
        }catch (Exception exception){
            throw new NotSavedException("Error updating Admin", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully saved", adminDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> delete(@RequestBody AdminDto adminDto){
        try {
            adminService.delete(adminMapper.mapToEntity(adminDto));
        }catch (Exception exception){
            throw new NotFoundException("Not Found", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @DeleteMapping(APIConstant.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id){
        try {
            adminService.deleteById(id);
        }catch (Exception exception){
            throw new NotFoundException("Not Found Admin Id: " + id);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id){
        AdminEntity adminEntity = adminService.findById(id);
        if (adminEntity == null) throw new NotFoundException("Not Found Admin Id: " + id);
        return new ResponseEntity<>(new ResponseDto("Successfully fetched", adminMapper.mapToDto(adminEntity)), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_ALL)
    public ResponseEntity<ResponseDto> findAll(){
        List<AdminEntity> adminEntityList = adminService.findAll();
        if (adminEntityList == null) throw new NotFoundException("Empty List");
        return new ResponseEntity<>(new ResponseDto("Successfully fetched", adminMapper.mapToDto(adminEntityList)), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<ResponseDto> testAPI() {
        VaccineFormDto formDto = new VaccineFormDto();
        formDto.setVaccineDistributionCenterDto(new VaccineDistributionCenterDto());
        formDto.setVaccineDto(new VaccineDto());
        formDto.setCompanyLocationDto(new LocationDto());
        formDto.setAgeCategory(1L);
        formDto.setCenterLocationDto(new LocationDto());
        formDto.setManufacturingCompanyDto(new ManufacturingCompanyDto());
//        formDto.setManufacturingCompanyDto(new ManufacturingCompanyDto());
//        formDto.setVaccineDistributionCenterDto(new VaccineDistributionCenterDto());

        return new ResponseEntity<>(new ResponseDto("test", formDto), HttpStatus.OK);
    }

    @PostMapping("/contact")
    public ResponseEntity<ResponseDto> sendContactMail(@RequestBody ContactDto contactDto) throws MessagingException {
        if(contactDto == null) {
            return new ResponseEntity<>(new ResponseDto("Field must not be null!"), HttpStatus.NO_CONTENT);
        }
        ContactDto contactDto1 = contactDto;
        contactDto1 = contactDto;
        String body = " Query send from  "+ contactDto1.getFirstName() +" "+ contactDto1.getMiddleName() +" "+ contactDto1.getLastName()+
                "  his/her email address is : " + contactDto1.getEmail() + "  description about query : " + contactDto1.getDescription();


        cvrsMailService.sendContactMail(body, body);

        return new ResponseEntity<>(new ResponseDto("Mail send Successfully"),HttpStatus.OK);

    }
}
