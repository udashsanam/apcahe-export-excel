package com.cvrs.backend.controller;

import com.cvrs.backend.controller.base.BaseController;
import com.cvrs.backend.dto.CitizenDto;
import com.cvrs.backend.dto.CustomDto.ChangeDto;
import com.cvrs.backend.dto.CustomDto.DashboardCustomDto;
import com.cvrs.backend.dto.CustomDto.FormDto;
import com.cvrs.backend.dto.CustomDto.ResponseDto;
import com.cvrs.backend.dto.VaccineDto;
import com.cvrs.backend.entity.CitizenEntity;
import com.cvrs.backend.entity.VaccinationLogEntity;
import com.cvrs.backend.entity.VaccineEntity;
import com.cvrs.backend.exception.NotFoundException;
import com.cvrs.backend.exception.NotSavedException;
import com.cvrs.backend.mapper.CitizenMapper;
import com.cvrs.backend.service.IAdminService;
import com.cvrs.backend.service.ICitizenService;
import com.cvrs.backend.service.IVaccinationLogService;
import com.cvrs.backend.service.IVaccineService;
import com.cvrs.backend.util.APIConstant;
import com.cvrs.backend.util.CvrsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(APIConstant.CITIZEN)
public class CitizenController extends BaseController {
    private CitizenMapper citizenMapper;
    private ICitizenService citizenService;
    private IVaccineService vaccineService;
    private IVaccinationLogService vaccinationLogService;
    private IAdminService adminService;

    @Autowired
    public CitizenController(CitizenMapper citizenMapper, ICitizenService citizenService , IVaccineService vaccineService, IVaccinationLogService vaccinationLogService, IAdminService adminService) {
        this.citizenMapper = citizenMapper;
        this.citizenService = citizenService;
        this.vaccineService = vaccineService;
        this.vaccinationLogService = vaccinationLogService;
        this.adminService = adminService;

    }

    // Todo: saving all details of citizen in different entity while saving citizen
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> saveAllDetails(@RequestBody FormDto formDto){
        if(formDto.getCitizenDto() == null || formDto.getLocationDto() == null) {
//            throw new NotFoundException("Data cannot be empty!");
            return new ResponseEntity<>(new ResponseDto("Data empty"),HttpStatus.OK);
        }
        CitizenDto citizenDto = citizenService.saveAllDetails(formDto);
        try {
            CitizenEntity citizenEntity = citizenMapper.mapToEntity(citizenDto);
            System.out.println(citizenEntity);
            citizenService.save(citizenEntity);
        }catch (Exception exception){
            throw new NotSavedException("Not saved", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully saved", citizenDto), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<ResponseDto> save(@RequestBody CitizenDto citizenDto){
//        try {
//            citizenService.save(citizenMapper.mapToEntity(citizenDto));
//        }catch (Exception exception){
//            throw new NotSavedException("Not saved", exception);
//        }
//        return new ResponseEntity<>(new ResponseDto("Successfully saved", citizenDto), HttpStatus.OK);
//    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@RequestBody CitizenDto citizenDto){
        try {
            citizenService.save(citizenMapper.mapToEntity(citizenDto));
        }catch (Exception exception){
            throw new NotSavedException("Not saved", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully saved", citizenDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> delete(@RequestBody CitizenDto citizenDto){
        try {
            citizenService.delete(citizenMapper.mapToEntity(citizenDto));
        }catch (Exception exception){
            throw new NotFoundException("Not Found", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @DeleteMapping(APIConstant.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id ){
        try {
            citizenService.deleteById(id);
        }catch (Exception exception){
            throw new NotFoundException("Not Found Citizen Id: " + id);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_ALL)
    public ResponseEntity<ResponseDto> findAll(){
        List<CitizenEntity> citizenEntityList = citizenService.findAll();
        if(citizenEntityList == null) throw new NotFoundException("Empty List");
        return new ResponseEntity<>(new ResponseDto("Successfully Fetched", citizenMapper.mapToDto(citizenEntityList)), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id){
        CitizenEntity citizenEntity = citizenService.findById(id);
        if(citizenEntity == null) throw new NotFoundException("Not Found Citizen Id: " + id);
        return new ResponseEntity<>(new ResponseDto("Successfully Fetched", citizenMapper.mapToDto(citizenEntity)), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_BY_STATUS_CODE)
    public ResponseEntity<ResponseDto> findByStatusCode(@RequestParam("statusCode") String statusCode){

        List<CitizenDto> citizenDtos = new ArrayList<>();
        citizenDtos = citizenService.findByStatusCode(statusCode);

        if (citizenDtos == null){
            return new ResponseEntity<>(new ResponseDto("Citizen with status code " + statusCode + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDto("Successfully fetched! ", citizenDtos), HttpStatus.OK);

    }

    @GetMapping(APIConstant.FIND_BY_AGE_CATEGORIES)
    public ResponseEntity<ResponseDto> findByAgeCategory(@RequestParam("id") Long ageCategory){

        List<CitizenDto> citizenDtos = new ArrayList<>();
        citizenDtos = citizenService.findByAgeCategory(ageCategory);

        if(citizenDtos == null){
            return  new ResponseEntity<>(new ResponseDto("Citizen data not found!"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDto("Successfully fetched!", citizenDtos), HttpStatus.NOT_FOUND);

    }

//    @GetMapping("/municipality/{municipality}") for pathVariable
    @GetMapping("/municipality")
    public ResponseEntity<ResponseDto> getCitizen(@RequestParam("municipality") String municipality, @RequestParam("wardNo") Integer wardNO) {
        List<CitizenDto> citizenDtos = new ArrayList<>();
//        citizenDtos = citizenService.findAllByMunicipality(municipality);
        citizenDtos = citizenService.findAllByMunicipality(municipality, wardNO);
        return new ResponseEntity<>(new ResponseDto("Successfully fetched!", citizenDtos), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<ResponseDto> getDashboardDetails() {
        List<DashboardCustomDto> dashboardCustomDtos = citizenService.findAllCitizenDetailsForDashboard();
        if(dashboardCustomDtos == null) {
            return new ResponseEntity<>(new ResponseDto("No citizen record found!"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully fetched!", dashboardCustomDtos), HttpStatus.OK);
    }
    @PostMapping("/changeVaccinatedStatus")
    public ResponseEntity<ResponseDto> changeVaccineStatus(@RequestBody ChangeDto dto) {

        VaccineEntity checkVaccine = vaccineService.findById(dto.getVaccineId());

        if(checkVaccine == null || checkVaccine.getUnits() == 0) {
            throw new NotFoundException("Vaccine quantity is not available or vaccine does not exits !!");
        }

        CitizenEntity citizenEntity = citizenService.findByRegistrationNUmberStr(dto.getRegistrationNumber());
        if(citizenEntity == null) {
            return new ResponseEntity<>(new ResponseDto("Citizen not found"), HttpStatus.NO_CONTENT);
        }
        // setting vaccination record
        VaccinationLogEntity vaccinationLogEntity = new VaccinationLogEntity();
        vaccinationLogEntity.setAdminId(adminService.findByUsername(dto.getUsername()).getId());
        vaccinationLogEntity.setRegistrationNumber(citizenEntity.getRegNum());
        try {
            vaccinationLogService.save(vaccinationLogEntity);
        } catch (Exception ex){
            throw new NotSavedException("vaccine record cannot be saved successfully!");
        }
        citizenEntity.setVaccinatedStatus(dto.getVaccinatedStatus());
        if(dto.getVaccineId() != null) {
            VaccineEntity vaccineEntity = vaccineService.findById(dto.getVaccineId());
            citizenEntity.setVaccineEntity(vaccineEntity);
            vaccineEntity.setUnits(vaccineEntity.getUnits()-1L);
            vaccineService.save(vaccineEntity);

        }
        citizenService.save(citizenEntity);
        return new ResponseEntity<>(new ResponseDto("Vaccinated status successfully changed"), HttpStatus.OK);
    }

    @GetMapping(APIConstant.GET_VACCINE_REPORT)
    public ResponseEntity<ResponseDto> getVaccineReport(@PathVariable("registrationNumber") Long registrationNumber) {

        System.out.println(registrationNumber);
        List<CitizenEntity> citizenEntity = citizenService.findByRegistrationNumber(registrationNumber);

        if (citizenEntity == null) {
            return new ResponseEntity<>(new ResponseDto("Registration number not found"), HttpStatus.NOT_FOUND);
        }
        List<CitizenDto> citizenDtos = citizenMapper.mapToDto(citizenEntity);

        Map<String , Object> objectMap = citizenService.generateRegistrationReport(citizenDtos.get(0));
        if(objectMap.get("-1") != null) {
            return new ResponseEntity<>(new ResponseDto("Citizen did not get any vaccine", objectMap.get("-1")), HttpStatus.OK);
        } else if (objectMap.get("-2") != null){
            return new ResponseEntity<>(new ResponseDto("Successfully fetched", objectMap.get("-2")), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDto("Successfully fetched!", objectMap.get("-3")), HttpStatus.OK);
    }


}
