package com.cvrs.backend.service.implementation;

import com.cvrs.backend.dto.*;
import com.cvrs.backend.dto.CustomDto.VaccineFormDto;
import com.cvrs.backend.entity.*;
import com.cvrs.backend.mapper.*;
import com.cvrs.backend.repository.*;
import com.cvrs.backend.service.*;
import com.cvrs.backend.service.implementation.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class VaccineDistributionCenterToVaccineServiceImpl extends BaseServiceImpl<VaccineDistributionCenterToVaccineEntity, Long> implements IVaccineDistributionCenterToVaccineService {
    private VaccineDistributionCenterToVaccineRepository vaccineDistributionCenterToVaccineRepository;
    private VaccineRepository vaccineRepository;
    private IVaccineService vaccineService;
    private VaccineMapper vaccineMapper;
    private VaccineDistributionCenterRepository vaccineDistributionCenterRepository;
    private IVaccineDistributionCenterService vaccineDistributionCenterService;
    private VaccineDistributionCenterMapper vaccineDistributionCenterMapper;
    private ManufacturingCompanyRepository manufacturingCompanyRepository;
    private IManufacturingCompanyService manufacturingCompanyService;
    private ManufacturingCompanyMapper manufacturingCompanyMapper;
    private LocationRepository locationRepository;
    private ILocationService locationService;
    private LocationMapper locationMapper;
    private CitizenRepository citizenRepository;
    private CitizenMapper citizenMapper;
    private CvrsMailService cvrsMailService;
    private IVaccineToAgeCategoryService vaccineToAgeCategoryService;
    private VaccineToAgeCategoryMapper vaccineToAgeCategoryMapper;



    @Autowired
    public VaccineDistributionCenterToVaccineServiceImpl(JpaRepository<VaccineDistributionCenterToVaccineEntity, Long> repository,
                                                         VaccineDistributionCenterToVaccineRepository vaccineDistributionCenterToVaccineRepository,
                                                         VaccineRepository vaccineRepository, IVaccineService vaccineService, VaccineMapper vaccineMapper,
                                                         VaccineDistributionCenterRepository vaccineDistributionCenterRepository,
                                                         IVaccineDistributionCenterService vaccineDistributionCenterService,
                                                         VaccineDistributionCenterMapper vaccineDistributionCenterMapper,
                                                         ManufacturingCompanyRepository manufacturingCompanyRepository,
                                                         IManufacturingCompanyService manufacturingCompanyService,
                                                         ManufacturingCompanyMapper manufacturingCompanyMapper,
                                                         LocationRepository locationRepository, ILocationService locationService,
                                                         LocationMapper locationMapper, CitizenRepository citizenRepository,
                                                         CitizenMapper citizenMapper, CvrsMailService cvrsMailService,
                                                         IVaccineToAgeCategoryService vaccineToAgeCategoryService,
                                                         VaccineToAgeCategoryMapper vaccineToAgeCategoryMapper) {
        super(repository);
        this.vaccineDistributionCenterToVaccineRepository = vaccineDistributionCenterToVaccineRepository;
        this.vaccineDistributionCenterRepository = vaccineDistributionCenterRepository;
        this.vaccineDistributionCenterMapper = vaccineDistributionCenterMapper;
        this.vaccineDistributionCenterService = vaccineDistributionCenterService;
        this.vaccineRepository = vaccineRepository;
        this.vaccineService = vaccineService;
        this.vaccineMapper = vaccineMapper;
        this.manufacturingCompanyRepository = manufacturingCompanyRepository;
        this.manufacturingCompanyService = manufacturingCompanyService;
        this.manufacturingCompanyMapper = manufacturingCompanyMapper;
        this.locationRepository = locationRepository;
        this.locationService = locationService;
        this.locationMapper = locationMapper;
        this.citizenRepository = citizenRepository;
        this.citizenMapper = citizenMapper;
        this.cvrsMailService = cvrsMailService;
        this.vaccineToAgeCategoryService = vaccineToAgeCategoryService;
        this.vaccineToAgeCategoryMapper = vaccineToAgeCategoryMapper;
    }


    @Override
    public VaccineDistributionCenterToVaccineDto saveVaccineDetails(VaccineFormDto vaccineFormDto) {
        VaccineDistributionCenterToVaccineDto vaccineDistributionCenterToVaccineDto = new VaccineDistributionCenterToVaccineDto();

        //check the vaccine details in database
        VaccineDto formVaccineDto = vaccineFormDto.getVaccineDto();
        VaccineEntity vaccineEntity = vaccineRepository.findByBatchNum(formVaccineDto.getBatchNum());
        if(vaccineEntity == null){

            //set the manufacturing company
            ManufacturingCompanyDto formManufacturingCompanyDto = vaccineFormDto.getManufacturingCompanyDto();
            ManufacturingCompanyEntity manufacturingCompanyEntity = manufacturingCompanyRepository.findByName(formManufacturingCompanyDto.getName());
            if(manufacturingCompanyEntity == null){

                //set the company location
                LocationDto formCompanyLocation = vaccineFormDto.getCompanyLocationDto();
                LocationEntity companyLocation = locationRepository.findByWardNoAndMunicipality(formCompanyLocation.getWardNo(), formCompanyLocation.getMunicipality());
                if(companyLocation == null){
                    locationService.save(locationMapper.mapToEntity(formCompanyLocation));
                    LocationEntity companyLocationAfterSave = locationRepository.findByWardNoAndMunicipality(formCompanyLocation.getWardNo(), formCompanyLocation.getMunicipality());
                    formManufacturingCompanyDto.setLocationEntityId(companyLocationAfterSave.getId());
                } else{
                    formManufacturingCompanyDto.setLocationEntityId(companyLocation.getId());
                }

                manufacturingCompanyService.save(manufacturingCompanyMapper.mapToEntity(formManufacturingCompanyDto));
                ManufacturingCompanyEntity manufacturingCompanyAfterSave = manufacturingCompanyRepository.findByName(formManufacturingCompanyDto.getName());
                formVaccineDto.setManufacturingCompanyEntityId(manufacturingCompanyAfterSave.getId());

            } else{
                formVaccineDto.setManufacturingCompanyEntityId(manufacturingCompanyEntity.getId());
            }
            vaccineService.save(vaccineMapper.mapToEntity(formVaccineDto));
            VaccineEntity vaccineEntityAfterSave = vaccineRepository.findByBatchNum(formVaccineDto.getBatchNum());
            vaccineDistributionCenterToVaccineDto.setVaccineEntityId(vaccineEntityAfterSave.getId());

            //Todo: save the vaccine id and ageCategory in VaccineToAgeCategoryEntity
            VaccineToAgeCategoryDto vaccineToAgeCategoryDto = new VaccineToAgeCategoryDto();
            vaccineToAgeCategoryDto.setVaccineEntityId(vaccineEntityAfterSave.getId());
            vaccineToAgeCategoryDto.setAgeCategoryEntityId(vaccineFormDto.getAgeCategory());
            vaccineToAgeCategoryService.save(vaccineToAgeCategoryMapper.mapToEntity(vaccineToAgeCategoryDto));


        } else{
            vaccineDistributionCenterToVaccineDto.setVaccineEntityId(vaccineEntity.getId());
        }

        //check for vaccine distribution center
        VaccineDistributionCenterDto formVaccineDistributionCenterDto = vaccineFormDto.getVaccineDistributionCenterDto();
        VaccineDistributionCenterEntity vaccineDistributionCenterEntity = vaccineDistributionCenterRepository.findByName(formVaccineDistributionCenterDto.getName());
        if (vaccineDistributionCenterEntity == null){

            //set the company location
            LocationDto formCenterLocation = vaccineFormDto.getCenterLocationDto();
            LocationEntity centerLocation = locationRepository.findByWardNoAndMunicipality(formCenterLocation.getWardNo(), formCenterLocation.getMunicipality());
            if(centerLocation == null){
                locationService.save(locationMapper.mapToEntity(formCenterLocation));
                LocationEntity centerLocationAfterSave = locationRepository.findByWardNoAndMunicipality(formCenterLocation.getWardNo(), formCenterLocation.getMunicipality());
                formVaccineDistributionCenterDto.setLocationEntityId(centerLocationAfterSave.getId());
            } else{
                formVaccineDistributionCenterDto.setLocationEntityId(centerLocation.getId());
            }
            vaccineDistributionCenterService.save(vaccineDistributionCenterMapper.mapToEntity(formVaccineDistributionCenterDto));
            VaccineDistributionCenterEntity vaccineDistributionCenterEntityAfterSave = vaccineDistributionCenterRepository.findByName(formVaccineDistributionCenterDto.getName());
            vaccineDistributionCenterToVaccineDto.setVaccineDistributionCenterEntityId(vaccineDistributionCenterEntityAfterSave.getId());
        } else {
            vaccineDistributionCenterToVaccineDto.setVaccineDistributionCenterEntityId(vaccineDistributionCenterEntity.getId());
        }


        return vaccineDistributionCenterToVaccineDto;
    }

    @Override
    public boolean distributeVaccineNotification(VaccineFormDto vaccineFormDto) throws MessagingException {
        //distribution_right
//        if(unit >= count_all_people_of_ageCategory&municipality&status=pending)
//        {
//            for_all_ageCategory_municipality
//            //nothing_to_do_if_there_is_left
//        }
//        //when there is less
//        else{
//            if(unit >= count_all_people_of_ageCategory&municipality&wardNo.&status=pending)
//            {
//                for_all_ageCategory_municipality_wardNo
//                //check_for_remaining_units
//                if(left)
//                {
//                    //FIFO
//                    for_some_ageCategory_municipality(except_given_wardNo)
//                }
//            }
//            else{
//                if(unit >= count_all_people_of_agecategory&municipality&wardno.&priority&status=pending)
//                {for_all_ageCategory_municipality_wardNo_priority}
//                if(left)
//                {
//                    for_some_ageCategory_municipality_wardNo(except_priority)
//                }
//                else{
//                    for_some_ageCategory_municipality_wardNo_priority(FIFO)
//                }
//            }
//        }

        Long units = vaccineFormDto.getVaccineDto().getUnits();
        VaccineDto vaccineDto = vaccineFormDto.getVaccineDto();
        LocationDto centerLocationDto = vaccineFormDto.getCenterLocationDto();
        VaccineDistributionCenterDto vaccineDistributionCenterDto = vaccineFormDto.getVaccineDistributionCenterDto();

        String municipality = vaccineFormDto.getCenterLocationDto().getMunicipality();
        Long ageCategoryId = vaccineFormDto.getAgeCategory();
        Integer wardNo = vaccineFormDto.getCenterLocationDto().getWardNo();

        //municipality & vaccineStatus & ageCategory
        List<CitizenEntity> citizenEntityList = citizenRepository.findAllByLocationEntityMunicipalityAndVaccinatedStatusAndAgeCategoryEntityId(municipality, "pending", ageCategoryId);
        List<CitizenDto> citizenDtoList = citizenMapper.mapToDto(citizenEntityList);
        Long citizenCount = citizenDtoList.stream().count();
        if(units >= citizenCount)
        {
            for (CitizenDto citizenDto:
                 citizenDtoList) {
                cvrsMailService.sendNotificationToCitizen(citizenDto, vaccineDto, vaccineDistributionCenterDto, centerLocationDto);
            }
            //Nothing to do if there is units left because people won't travel from one district to another.
        } else {
            //municipality & wardNo & vaccineStatus & ageCategory
            List<CitizenEntity> citizenEntityList1 = citizenRepository.findAllByLocationEntityMunicipalityAndLocationEntityWardNoAndVaccinatedStatusAndAgeCategoryEntityId(municipality, wardNo, "pending", ageCategoryId);
            List<CitizenDto> citizenDtoList1 = citizenMapper.mapToDto(citizenEntityList1);
            Long citizenCount1 = citizenDtoList1.stream().count();
            if(units >= citizenCount1)
            {
//                Long leftUnits = 0L;
                for (CitizenDto citizenDto1:
                     citizenDtoList1) {
//                    leftUnits++;
                    units--;
                    cvrsMailService.sendNotificationToCitizen(citizenDto1, vaccineDto, vaccineDistributionCenterDto, centerLocationDto);
                }
                if(units > 0 ){
                    //municipality & Except specific wardNo & vaccineStatus & ageCategory
                    //FIFO
                    List<CitizenEntity> citizenEntityList3 = citizenRepository.findAllByLocationEntityMunicipalityAndNotLocationEntityWardNoAndVaccinatedStatusAndAgeCategoryEntityId(municipality, wardNo, "pending", ageCategoryId);
                    List<CitizenDto> citizenDtoList3 = citizenMapper.mapToDto(citizenEntityList3);
                    for (int i = 0; i < units ; i++) {
                        cvrsMailService.sendNotificationToCitizen(citizenDtoList3.get(i), vaccineDto, vaccineDistributionCenterDto, centerLocationDto);
                    }
                }
            } else {
                //municipality & wardNo & priority(true) & vaccineStatus & ageCategory
                List<CitizenEntity> citizenEntityList2 = citizenRepository.findAllByLocationEntityMunicipalityAndLocationEntityWardNoAndPriorityAndVaccinatedStatusAndAgeCategoryEntityId(municipality, wardNo, true, "pending", ageCategoryId);
                List<CitizenDto> citizenDtoList2 = citizenMapper.mapToDto(citizenEntityList2);
                Long citizenCount2 = citizenDtoList2.stream().count();
                if(units >= citizenCount2)
                {
                    for (CitizenDto citizenDto2:
                         citizenDtoList2) {
                        units--;
                        cvrsMailService.sendNotificationToCitizen(citizenDto2, vaccineDto, vaccineDistributionCenterDto, centerLocationDto);
                    }
                    if(units > 0 ){
                        //municipality & wardNo & priority(false) & vaccineStatus & ageCategory
                        //FIFO
                        List<CitizenEntity> citizenEntityList4 = citizenRepository.findAllByLocationEntityMunicipalityAndLocationEntityWardNoAndPriorityAndVaccinatedStatusAndAgeCategoryEntityId(municipality, wardNo, false, "pending", ageCategoryId);
                        List<CitizenDto> citizenDtoList4 = citizenMapper.mapToDto(citizenEntityList4);
                        for (int i = 0; i < units ; i++) {
                            cvrsMailService.sendNotificationToCitizen(citizenDtoList4.get(i), vaccineDto, vaccineDistributionCenterDto, centerLocationDto);
                        }
                    }
                } else {
                    if(units > 0 ){
                        //municipality & wardNo & priority(true) & vaccineStatus & ageCategory
                        //FIFO
                        List<CitizenEntity> citizenEntityList5 = citizenRepository.findAllByLocationEntityMunicipalityAndLocationEntityWardNoAndPriorityAndVaccinatedStatusAndAgeCategoryEntityId(municipality, wardNo, true, "pending", ageCategoryId);
                        List<CitizenDto> citizenDtoList5 = citizenMapper.mapToDto(citizenEntityList5);
                        for (int i = 0; i < units ; i++) {
                            cvrsMailService.sendNotificationToCitizen(citizenDtoList5.get(i), vaccineDto, vaccineDistributionCenterDto, centerLocationDto);
                        }
                    }
                }
            }
        }
        return true;
    }

}
