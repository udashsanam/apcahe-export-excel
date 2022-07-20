package com.cvrs.backend.controller;

import com.cvrs.backend.controller.base.BaseController;
import com.cvrs.backend.dto.CustomDto.ResponseDto;
import com.cvrs.backend.dto.OccupationDto;
import com.cvrs.backend.entity.OccupationEntity;
import com.cvrs.backend.exception.NotFoundException;
import com.cvrs.backend.exception.NotSavedException;
import com.cvrs.backend.mapper.OccupationMapper;
import com.cvrs.backend.service.IOccupationService;
import com.cvrs.backend.util.APIConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIConstant.OCCUPATION)
public class OccupationController extends BaseController {
    private OccupationMapper occupationMapper;
    private IOccupationService occupationService;

    @Autowired
    public OccupationController(OccupationMapper occupationMapper, IOccupationService occupationService) {
        this.occupationMapper = occupationMapper;
        this.occupationService = occupationService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody OccupationDto occupationDto){
        try {
            occupationService.save(occupationMapper.mapToEntity(occupationDto));
        }catch (Exception exception){
            throw new NotSavedException("Not Saved", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully Saved", occupationDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody OccupationDto occupationDto){
        try {
            occupationService.save(occupationMapper.mapToEntity(occupationDto));
        }catch (Exception exception){
            throw new NotSavedException("Not Saved", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully Saved", occupationDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> delete(@RequestBody OccupationDto occupationDto){
        try {
            occupationService.delete(occupationMapper.mapToEntity(occupationDto));
        }catch (Exception exception){
            throw new NotFoundException("Not Found", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @DeleteMapping(APIConstant.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id){
        try {
            occupationService.findById(id);
        }catch (Exception exception){
            throw new NotFoundException("Not Found Occupation Id: " + id);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_ALL)
    public ResponseEntity<ResponseDto> findAll(){
        List<OccupationEntity> occupationEntityList = occupationService.findAll();
        if(occupationEntityList == null) throw new NotFoundException("Empty List");
        return new ResponseEntity<>(new ResponseDto("Successfully fetched", occupationMapper.mapToDto(occupationEntityList)), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id){
        OccupationEntity occupationEntity = occupationService.findById(id);
        if(occupationEntity == null) throw new NotFoundException("Not Found Occupation Id: " + id);
        return new ResponseEntity<>(new ResponseDto("Successfully fetched", occupationMapper.mapToDto(occupationEntity)), HttpStatus.OK);
    }
}
