package com.cvrs.backend.controller;

import com.cvrs.backend.controller.base.BaseController;
import com.cvrs.backend.dto.AgeCategoryDto;
import com.cvrs.backend.dto.CustomDto.ResponseDto;
import com.cvrs.backend.exception.NotSavedException;
import com.cvrs.backend.mapper.AgeCategoryMapper;
import com.cvrs.backend.service.IAgeCategoryService;
import com.cvrs.backend.util.APIConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIConstant.AGE_CATEGORY)
public class AgeCategoryController extends BaseController {
    private IAgeCategoryService ageCategoryService;
    private AgeCategoryMapper ageCategoryMapper;

    @Autowired
    public AgeCategoryController(IAgeCategoryService ageCategoryService, AgeCategoryMapper ageCategoryMapper) {
        this.ageCategoryService = ageCategoryService;
        this.ageCategoryMapper = ageCategoryMapper;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save (@RequestBody AgeCategoryDto ageCategoryDto){
        try {
            ageCategoryService.save(ageCategoryMapper.mapToEntity(ageCategoryDto));
        } catch (Exception ex) {
            throw new NotSavedException("Error saving AgeCategory!", ex);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully saved", ageCategoryDto), HttpStatus.OK);
    }

}
