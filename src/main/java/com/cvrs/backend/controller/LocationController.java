package com.cvrs.backend.controller;

import com.cvrs.backend.controller.base.BaseController;
import com.cvrs.backend.dto.CustomDto.ResponseDto;
import com.cvrs.backend.dto.LocationDto;
import com.cvrs.backend.entity.LocationEntity;
import com.cvrs.backend.exception.NotFoundException;
import com.cvrs.backend.exception.NotSavedException;
import com.cvrs.backend.mapper.LocationMapper;
import com.cvrs.backend.service.ILocationService;
import com.cvrs.backend.util.APIConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIConstant.LOCATION)
public class LocationController extends BaseController {
    private ILocationService locationService;
    private LocationMapper locationMapper;

    @Autowired
    public LocationController(ILocationService locationService, LocationMapper locationMapper) {
        this.locationService = locationService;
        this.locationMapper = locationMapper;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody LocationDto locationDto){
        try {
            locationService.save(locationMapper.mapToEntity(locationDto));
        }catch (Exception exception){
            throw new NotSavedException("Error saving location!", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully Saved!", locationDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody LocationDto locationDto){
        try {
            locationService.save(locationMapper.mapToEntity(locationDto));
        }catch (Exception exception){
            throw new NotFoundException("Not Found!", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully Updated!", locationDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> delete(@RequestBody LocationDto locationDto){
        try{
            locationService.delete(locationMapper.mapToEntity(locationDto));
        }catch (Exception exception){
            throw new NotFoundException("Not Found!", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully Deleted!"), HttpStatus.OK);
    }

    @DeleteMapping(APIConstant.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id){
        try {
            locationService.deleteById(id);
        }catch (Exception exception){
            throw new NotFoundException("Not Found! Id: " + id );
        }
        return new ResponseEntity<>(new ResponseDto("Successfully Deleted!"), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_ALL)
    public ResponseEntity<ResponseDto> findAll(){
        List<LocationEntity> locationEntityList = locationService.findAll();
        if(locationEntityList == null) throw new NotFoundException("Empty List!");
        return new ResponseEntity<>(new ResponseDto("Successfully Fetched!", locationMapper.mapToDto(locationEntityList)), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id){
        LocationEntity locationEntity = locationService.findById(id);
        if(locationEntity == null) throw new NotFoundException("Not Found! Id: " + id );
        return new ResponseEntity<>(new ResponseDto("Successfully Fetched!", locationMapper.mapToDto(locationEntity)), HttpStatus.OK);
    }
}
