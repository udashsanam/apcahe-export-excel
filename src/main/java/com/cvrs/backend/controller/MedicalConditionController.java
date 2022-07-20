package com.cvrs.backend.controller;

import com.cvrs.backend.controller.base.BaseController;
import com.cvrs.backend.dto.CustomDto.ResponseDto;
import com.cvrs.backend.dto.MedicalConditionDto;
import com.cvrs.backend.entity.MedicalConditionEntity;
import com.cvrs.backend.exception.NotFoundException;
import com.cvrs.backend.exception.NotSavedException;
import com.cvrs.backend.mapper.MedicalConditionMapper;
import com.cvrs.backend.service.IMedicalConditionService;
import com.cvrs.backend.util.APIConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIConstant.MEDICAL_CONDITION)
public class MedicalConditionController extends BaseController {
    private MedicalConditionMapper medicalConditionMapper;
    private IMedicalConditionService medicalConditionService;

    @Autowired
    public MedicalConditionController(MedicalConditionMapper medicalConditionMapper, IMedicalConditionService medicalConditionService) {
        this.medicalConditionMapper = medicalConditionMapper;
        this.medicalConditionService = medicalConditionService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody MedicalConditionDto medicalConditionDto){
        try {
            medicalConditionService.save(medicalConditionMapper.mapToEntity(medicalConditionDto));
        }catch (Exception exception){
            throw new NotSavedException("Not Saved", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully Saved", medicalConditionDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody MedicalConditionDto medicalConditionDto){
        try {
            medicalConditionService.save(medicalConditionMapper.mapToEntity(medicalConditionDto));
        }catch (Exception exception){
            throw new NotSavedException("Not Saved", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully Saved", medicalConditionDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> delete(@RequestBody MedicalConditionDto medicalConditionDto){
        try {
            medicalConditionService.delete(medicalConditionMapper.mapToEntity(medicalConditionDto));
        }catch (Exception exception){
            throw new NotFoundException("Not Found", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @DeleteMapping(APIConstant.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id){
        try {
            medicalConditionService.deleteById(id);
        }catch (Exception exception){
            throw new NotFoundException("Not Found Id: " + id);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_ALL)
    public ResponseEntity<ResponseDto> findAll(){
        List<MedicalConditionEntity> medicalConditionEntityList = medicalConditionService.findAll();
        if(medicalConditionEntityList == null) throw new NotFoundException("Empty List");
        return new ResponseEntity<>(new ResponseDto("Successfully fetched", medicalConditionMapper.mapToDto(medicalConditionEntityList)), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id){
        MedicalConditionEntity medicalConditionEntity = medicalConditionService.findById(id);
        if(medicalConditionEntity == null) throw new NotFoundException("Empty List");
        return new ResponseEntity<>(new ResponseDto("Successfully fetched", medicalConditionMapper.mapToDto(medicalConditionEntity)), HttpStatus.OK);
    }


}
