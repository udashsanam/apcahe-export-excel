package com.cvrs.backend.controller;

import com.cvrs.backend.controller.base.BaseController;
import com.cvrs.backend.dto.CustomDto.ResponseDto;
import com.cvrs.backend.dto.ManufacturingCompanyDto;
import com.cvrs.backend.entity.ManufacturingCompanyEntity;
import com.cvrs.backend.exception.NotFoundException;
import com.cvrs.backend.exception.NotSavedException;
import com.cvrs.backend.mapper.ManufacturingCompanyMapper;
import com.cvrs.backend.service.IManufacturingCompanyService;
import com.cvrs.backend.util.APIConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIConstant.MANUFACTURING_COMPANY)
public class ManufacturingCompanyController extends BaseController {
    private IManufacturingCompanyService manufacturingCompanyService;
    private ManufacturingCompanyMapper manufacturingCompanyMapper;

    @Autowired
    public ManufacturingCompanyController(IManufacturingCompanyService manufacturingCompanyService, ManufacturingCompanyMapper manufacturingCompanyMapper) {
        this.manufacturingCompanyService = manufacturingCompanyService;
        this.manufacturingCompanyMapper = manufacturingCompanyMapper;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody ManufacturingCompanyDto manufacturingCompanyDto){
//        manufacturingCompanyDto.setName("Shakti Group");
        try {
            manufacturingCompanyService.save(manufacturingCompanyMapper.mapToEntity(manufacturingCompanyDto));
        }catch (Exception exception){
            throw new NotSavedException("Not Saved", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully Saved", manufacturingCompanyMapper.mapToDto(manufacturingCompanyMapper.mapToEntity(manufacturingCompanyDto))), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody ManufacturingCompanyDto manufacturingCompanyDto){
        try {
            manufacturingCompanyService.save(manufacturingCompanyMapper.mapToEntity(manufacturingCompanyDto));
        }catch (Exception exception){
            throw new NotSavedException("Not Saved", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully Saved", manufacturingCompanyDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> delete(@RequestBody ManufacturingCompanyDto manufacturingCompanyDto){
        try {
            manufacturingCompanyService.delete(manufacturingCompanyMapper.mapToEntity(manufacturingCompanyDto));
        }catch (Exception exception){
            throw new NotFoundException("Not Found", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @DeleteMapping(APIConstant.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id){
        try {
            manufacturingCompanyService.deleteById(id);
        }catch (Exception exception){
            throw new NotFoundException("Not Found Company Id: " + id);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_ALL)
    public ResponseEntity<ResponseDto> findAll(){
        List<ManufacturingCompanyEntity> manufacturingCompanyEntityList = manufacturingCompanyService.findAll();
        if(manufacturingCompanyEntityList == null) throw new NotFoundException("Empty List");
        return new ResponseEntity<>(new ResponseDto("Successfully fetched", manufacturingCompanyMapper.mapToDto(manufacturingCompanyEntityList)), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id){
        ManufacturingCompanyEntity manufacturingCompanyEntity = manufacturingCompanyService.findById(id);
        if(manufacturingCompanyEntity == null) throw new NotFoundException("Not Found Company Id: " + id);
        return new ResponseEntity<>(new ResponseDto("Successfully fetched", manufacturingCompanyMapper.mapToDto(manufacturingCompanyEntity)), HttpStatus.OK);
    }

}
