package com.cvrs.backend.controller;

import com.cvrs.backend.controller.base.BaseController;
import com.cvrs.backend.dto.CustomDto.ResponseDto;
import com.cvrs.backend.dto.VaccineDistributionCenterDto;
import com.cvrs.backend.entity.VaccineDistributionCenterEntity;
import com.cvrs.backend.exception.NotFoundException;
import com.cvrs.backend.exception.NotSavedException;
import com.cvrs.backend.mapper.VaccineDistributionCenterMapper;
import com.cvrs.backend.service.IVaccineDistributionCenterService;
import com.cvrs.backend.util.APIConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIConstant.VACCINE_DISTRIBUTION_CENTER)
public class VaccineDistributionCenterController extends BaseController {

    private IVaccineDistributionCenterService vaccineDistributionCenterService;
    private VaccineDistributionCenterMapper vaccineDistributionCenterMapper;

    @Autowired
    public VaccineDistributionCenterController(IVaccineDistributionCenterService vaccineDistributionCenterService, VaccineDistributionCenterMapper vaccineDistributionCenterMapper) {
        this.vaccineDistributionCenterService = vaccineDistributionCenterService;
        this.vaccineDistributionCenterMapper = vaccineDistributionCenterMapper;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody VaccineDistributionCenterDto vaccineDistributionCenterDto){
        try {
            vaccineDistributionCenterService.save(vaccineDistributionCenterMapper.mapToEntity(vaccineDistributionCenterDto));
        }catch (Exception exception){
            throw new NotSavedException("Not Saved", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully saved", vaccineDistributionCenterDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody VaccineDistributionCenterDto vaccineDistributionCenterDto){
        try {
            vaccineDistributionCenterService.save(vaccineDistributionCenterMapper.mapToEntity(vaccineDistributionCenterDto));
        }catch (Exception exception){
            throw new NotSavedException("Not Saved", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully saved", vaccineDistributionCenterDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> delete(@RequestBody VaccineDistributionCenterDto vaccineDistributionCenterDto){
        try {
            vaccineDistributionCenterService.delete(vaccineDistributionCenterMapper.mapToEntity(vaccineDistributionCenterDto));
        }catch (Exception exception){
            throw new NotFoundException("Not Found", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @DeleteMapping(APIConstant.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id){
        try {
            vaccineDistributionCenterService.deleteById(id);
        }catch (Exception exception){
            throw new NotFoundException("Not Found Center Id: " + id);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_ALL)
    public ResponseEntity<ResponseDto> findAll(){
        List<VaccineDistributionCenterEntity> vaccineDistributionCenterEntityList = vaccineDistributionCenterService.findAll();
        if(vaccineDistributionCenterEntityList == null) throw new NotFoundException("Empty List");
        return new ResponseEntity<>(new ResponseDto("Successfully fetched", vaccineDistributionCenterMapper.mapToDto(vaccineDistributionCenterEntityList)), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id){
        VaccineDistributionCenterEntity vaccineDistributionCenterEntity = vaccineDistributionCenterService.findById(id);
        if(vaccineDistributionCenterEntity == null) throw new NotFoundException("Not Found Center Id: " + id);
        return new ResponseEntity<>(new ResponseDto("Successfully fetched", vaccineDistributionCenterMapper.mapToDto(vaccineDistributionCenterEntity)), HttpStatus.OK);
    }
}
