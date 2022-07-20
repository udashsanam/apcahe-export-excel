package com.cvrs.backend.controller;

import com.cvrs.backend.controller.base.BaseController;
import com.cvrs.backend.dto.CustomDto.ResponseDto;
import com.cvrs.backend.dto.CustomDto.VaccineRegisterDto;
import com.cvrs.backend.dto.VaccineDto;
import com.cvrs.backend.entity.LocationEntity;
import com.cvrs.backend.entity.ManufacturingCompanyEntity;
import com.cvrs.backend.entity.VaccineEntity;
import com.cvrs.backend.exception.NotFoundException;
import com.cvrs.backend.exception.NotSavedException;
import com.cvrs.backend.mapper.LocationMapper;
import com.cvrs.backend.mapper.ManufacturingCompanyMapper;
import com.cvrs.backend.mapper.VaccineMapper;
import com.cvrs.backend.service.ILocationService;
import com.cvrs.backend.service.IManufacturingCompanyService;
import com.cvrs.backend.service.IVaccineService;
import com.cvrs.backend.util.APIConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(APIConstant.VACCINE)
public class VaccineController extends BaseController {
    private IVaccineService vaccineService;
    private VaccineMapper vaccineMapper;
    private LocationMapper locationMapper;
    private ILocationService locationService;
    private ManufacturingCompanyMapper manufacturingCompanyMapper;
    private IManufacturingCompanyService manufacturingCompanyService;

    @Autowired
    public VaccineController(IVaccineService vaccineService, VaccineMapper vaccineMapper) {
        this.vaccineService = vaccineService;
        this.vaccineMapper = vaccineMapper;
    }

//    Todo: save its manufacturing company details
    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody VaccineRegisterDto vaccineDto){
        try {
            LocationEntity manufacturingCompanyLocationEntity = locationService.save(locationMapper.mapToEntity(vaccineDto.getManufacturingCompanyLocationDto()));
            vaccineDto.getManufacturingCompanyDto().setLocationEntityId(manufacturingCompanyLocationEntity.getId());
            ManufacturingCompanyEntity manufacturingCompanyEntity = manufacturingCompanyService.save(manufacturingCompanyMapper.mapToEntity(vaccineDto.getManufacturingCompanyDto()));
            vaccineDto.getVaccineDto().setManufacturingCompanyEntityId(manufacturingCompanyEntity.getId());
            vaccineService.save(vaccineMapper.mapToEntity(vaccineDto.getVaccineDto()));
        }catch (Exception exception){
            throw new NotSavedException("Not Saved", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully saved", vaccineDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody VaccineDto vaccineDto){
        try {
            vaccineService.save(vaccineMapper.mapToEntity(vaccineDto));
        }catch (Exception exception){
            throw new NotSavedException("Not Saved", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully saved", vaccineDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> delete(@RequestBody VaccineDto vaccineDto){
        try {
            vaccineService.delete(vaccineMapper.mapToEntity(vaccineDto));
        }catch (Exception exception){
            throw new NotFoundException("Not Found", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @DeleteMapping(APIConstant.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id){
        try {
            vaccineService.findById(id);
        }catch (Exception exception){
            throw new NotFoundException("Not Found Vaccine Id: " + id);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_ALL)
    public ResponseEntity<ResponseDto> findAll(){
        List<VaccineEntity> vaccineEntityList = vaccineService.findAll();
        if(vaccineEntityList == null) throw new NotFoundException("Empty List");
        return new ResponseEntity<>(new ResponseDto("Successfully fetched", vaccineMapper.mapToDto(vaccineEntityList)), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id){
        VaccineEntity vaccineEntity = vaccineService.findById(id);
        if(vaccineEntity == null) throw new NotFoundException("Not Found Vaccine Id: " + id);
        return new ResponseEntity<>(new ResponseDto("Successfully fetched", vaccineMapper.mapToDto(vaccineEntity)), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_ALL_WITH_DISTRIBUTION_CENTER)
    public ResponseEntity<ResponseDto> findAllWithVaccineDistributionCenter(@PathVariable("center") String center){
        List<VaccineDto> vaccineDtos = new ArrayList<>();
        vaccineDtos = vaccineService.findAllWithDistributionCenter(center);

        if (vaccineDtos == null){
            return new ResponseEntity<>(new ResponseDto("No vaccine detail found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDto("Successfully fetched", vaccineDtos), HttpStatus.OK);
    }

}
