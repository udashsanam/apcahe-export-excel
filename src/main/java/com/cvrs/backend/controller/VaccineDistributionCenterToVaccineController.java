package com.cvrs.backend.controller;

import com.cvrs.backend.controller.base.BaseController;
import com.cvrs.backend.dto.CustomDto.ResponseDto;
import com.cvrs.backend.dto.CustomDto.VaccineFormDto;
import com.cvrs.backend.dto.VaccineDistributionCenterToVaccineDto;
import com.cvrs.backend.entity.CitizenEntity;
import com.cvrs.backend.exception.NotSavedException;
import com.cvrs.backend.mapper.VaccineDistributionCenterToVaccineMapper;
import com.cvrs.backend.repository.CitizenRepository;
import com.cvrs.backend.service.IVaccineDistributionCenterToVaccineService;
import com.cvrs.backend.service.implementation.VaccineDistributionCenterToVaccineServiceImpl;
import com.cvrs.backend.util.APIConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping(APIConstant.VACCINE_DISTRIBUTION_CENTER_TO_VACCINE)
public class VaccineDistributionCenterToVaccineController extends BaseController {
    IVaccineDistributionCenterToVaccineService vaccineDistributionCenterToVaccineService;
    VaccineDistributionCenterToVaccineMapper vaccineDistributionCenterToVaccineMapper;

    @Autowired
    public VaccineDistributionCenterToVaccineController(IVaccineDistributionCenterToVaccineService vaccineDistributionCenterToVaccineService,
                                                        VaccineDistributionCenterToVaccineMapper vaccineDistributionCenterToVaccineMapper) {
        this.vaccineDistributionCenterToVaccineService = vaccineDistributionCenterToVaccineService;
        this.vaccineDistributionCenterToVaccineMapper = vaccineDistributionCenterToVaccineMapper;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> saveVaccineDetails(@RequestBody VaccineFormDto vaccineFormDto) throws MessagingException {
        VaccineDistributionCenterToVaccineDto vaccineDistributionCenterToVaccineDto = vaccineDistributionCenterToVaccineService.saveVaccineDetails(vaccineFormDto);
        try {
            vaccineDistributionCenterToVaccineService.save(vaccineDistributionCenterToVaccineMapper.mapToEntity(vaccineDistributionCenterToVaccineDto));
//            vaccineDistributionCenterToVaccineService.distributeVaccineNotification(vaccineFormDto);
        }catch (Exception exception){
            throw new NotSavedException("Not saved", exception);
        }
        vaccineDistributionCenterToVaccineService.distributeVaccineNotification(vaccineFormDto);
        return new ResponseEntity<>(new ResponseDto("Successfully saved", vaccineDistributionCenterToVaccineDto), HttpStatus.OK);
    }


}
