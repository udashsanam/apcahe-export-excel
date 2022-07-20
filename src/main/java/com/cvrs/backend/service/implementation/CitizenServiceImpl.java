package com.cvrs.backend.service.implementation;

import com.cvrs.backend.dto.*;
import com.cvrs.backend.dto.CustomDto.DashboardCustomDto;
import com.cvrs.backend.dto.CustomDto.FormDto;
import com.cvrs.backend.dto.CustomDto.VaccineRegisterDto;
import com.cvrs.backend.dto.CustomDto.VaccineReportCustomDto;
import com.cvrs.backend.entity.*;
import com.cvrs.backend.exception.AlreadyExistedException;
import com.cvrs.backend.exception.NotFoundException;
import com.cvrs.backend.mapper.*;
import com.cvrs.backend.repository.*;
import com.cvrs.backend.service.*;
import com.cvrs.backend.service.implementation.base.BaseServiceImpl;
import com.cvrs.backend.util.APIConstant;
import com.cvrs.backend.util.CvrsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;


@Transactional
@Service
public class CitizenServiceImpl extends BaseServiceImpl<CitizenEntity, Long> implements ICitizenService {
    private CitizenRepository citizenRepository;
    private ILocationService locationService;
    private LocationMapper locationMapper;
    private LocationRepository locationRepository;
    private OccupationRepository occupationRepository;
    private OccupationMapper occupationMapper;
    private IOccupationService occupationService;
    private IMedicalConditionService medicalConditionService;
    private MedicalConditionRepository medicalConditionRepository;
    private MedicalConditionMapper medicalConditionMapper;
    private CitizenMapper citizenMapper;
    private VaccineRepository vaccineRepository;
    private VaccineMapper vaccineMapper;
    private ICitizenService iCitizenService;
    private IVaccineService vaccineService;
    private IVaccinationLogService vaccinationLogService;
    private IAdminService adminService;

    @Autowired
    public CitizenServiceImpl(JpaRepository<CitizenEntity, Long> repository,
                              CitizenRepository citizenRepository, ILocationService locationService,
                              LocationMapper locationMapper, LocationRepository locationRepository,
                              OccupationRepository occupationRepository, OccupationMapper occupationMapper,
                              IOccupationService occupationService, IMedicalConditionService medicalConditionService,
                              MedicalConditionRepository medicalConditionRepository, MedicalConditionMapper medicalConditionMapper,
                              CitizenMapper citizenMapper,
                              VaccineRepository vaccineRepository,
                              VaccineMapper vaccineMapper,
                              IVaccineService vaccineService,
                              IVaccinationLogService vaccinationLogService,
                              IAdminService adminService) {
        super(repository);
        this.citizenRepository = citizenRepository;
        this.locationService = locationService;
        this.locationMapper = locationMapper;
        this.locationRepository = locationRepository;
        this.occupationMapper = occupationMapper;
        this.occupationRepository = occupationRepository;
        this.occupationService = occupationService;
        this.medicalConditionRepository = medicalConditionRepository;
        this.medicalConditionService = medicalConditionService;
        this.medicalConditionMapper = medicalConditionMapper;
        this.citizenMapper = citizenMapper;
        this.vaccineMapper = vaccineMapper;
        this.vaccineService = vaccineService;
        this.vaccinationLogService = vaccinationLogService;
        this.adminService = adminService;
    }

    public CitizenDto saveAllDetails(FormDto formDto){

        CitizenDto citizenDto = formDto.getCitizenDto();

        //check already registered citizen
        CitizenEntity citizenEntity = citizenRepository.findByCitizenshipEquals(citizenDto.getCitizenship());
        if(citizenEntity != null){
            throw new AlreadyExistedException("Citizen already registered");
        }



        LocationDto formLocationDto = formDto.getLocationDto();

        //check already existed location by ward and municipality
        LocationEntity locationEntity = locationRepository.findByWardNoAndMunicipality(formLocationDto.getWardNo(), formLocationDto.getMunicipality());
        if (locationEntity == null){
//            LocationDto locationDto = new LocationDto();
//            locationDto.setWardNo(formLocationDto.getWardNo());
//            locationDto.setMunicipality(formLocationDto.getMunicipality());
//            locationDto.setDistrict(formLocationDto.getDistrict());
//            locationDto.setZone(formLocationDto.getZone());
//            locationDto.setState(formLocationDto.getState());

//            LocationEntity saveLocationEntity = locationMapper.mapToEntity(formLocationDto);
//            locationService.save(saveLocationEntity);
//
//            //save the location and get the location_id;
//            LocationEntity locationEntityAfterSave = locationRepository.findByWardNoAndMunicipality(formLocationDto.getWardNo(), formLocationDto.getMunicipality());
//            citizenDto.setLocationEntityId(locationEntityAfterSave.getId());
            //or
            LocationEntity saveLocationEntity = locationRepository.save(locationMapper.mapToEntity(formLocationDto));
            citizenDto.setLocationEntityId(saveLocationEntity.getId());

        }else{
            citizenDto.setLocationEntityId(locationEntity.getId());
        }

        //check already existed occupation by occupation name
        OccupationDto formOccupationDto = formDto.getOccupationDto();
        OccupationEntity occupationEntity = occupationRepository.findByName(formOccupationDto.getName());
        if(occupationEntity == null){
//            OccupationDto occupationDto = new OccupationDto();
//            occupationDto.setName(formOccupationDto.getName());

            OccupationEntity saveOccupationEntity = occupationMapper.mapToEntity(formOccupationDto);
            occupationService.save(saveOccupationEntity);

//            OccupationEntity occupationEntityAfterSave = occupationRepository.findByName(formOccupationDto.getName());
//            citizenDto.setOccupationEntityId(occupationEntityAfterSave.getId());
            //or
            citizenDto.setOccupationEntityId(occupationRepository.findByName(formOccupationDto.getName()).getId());
        }else{
            citizenDto.setOccupationEntityId(occupationEntity.getId());
        }


        //check already existed medical condition by name
        MedicalConditionDto formMedicalConditionDto = formDto.getMedicalConditionDto();
        MedicalConditionEntity medicalConditionEntity = medicalConditionRepository.findByName(formMedicalConditionDto.getName());
        if(medicalConditionEntity == null){
//            MedicalConditionDto medicalConditionDto = new MedicalConditionDto();
//            medicalConditionDto.setName(formMedicalConditionDto.getName());
//            medicalConditionDto.setSerious(formMedicalConditionDto.getSerious());

            MedicalConditionEntity saveMedicalConditionEntity = medicalConditionMapper.mapToEntity(formMedicalConditionDto);
            medicalConditionService.save(saveMedicalConditionEntity);

            MedicalConditionEntity medicalConditionEntityAfterSave = medicalConditionRepository.findByName(formMedicalConditionDto.getName());
            citizenDto.setMedicalConditionEntityId(medicalConditionEntityAfterSave.getId());
            medicalConditionEntity = medicalConditionEntityAfterSave;
        }else{
            citizenDto.setMedicalConditionEntityId(medicalConditionEntity.getId());
        }

        //set the priority of citizen
        if(medicalConditionEntity.getName().equalsIgnoreCase("NORMAL") || medicalConditionEntity.getName().equalsIgnoreCase("BLOOD PRESSURE")
        || medicalConditionEntity.getName().equalsIgnoreCase("DIABETES")){
            citizenDto.setPriority(false);
        } else {
            citizenDto.setPriority(true);
        }

        //set the age category
        String dob = dateInString(citizenDto.getDob());
        LocalDate localDob = LocalDate.parse(dob);
        LocalDate currentDate = LocalDate.now();
        Integer age = Period.between(localDob, currentDate).getYears();

        if(age <= 12){
            citizenDto.setAgeCategoryEntityId(Long.parseLong(CvrsUtils.CHILDREN));
        } else if (age >= 13 && age <= 18){
            citizenDto.setAgeCategoryEntityId(Long.parseLong(CvrsUtils.TEEN));
        } else if (age >= 19 && age <= 40){
        citizenDto.setAgeCategoryEntityId(Long.parseLong(CvrsUtils.YOUNG_ADULT));
        } else if (age >= 41 && age <= 60){
            citizenDto.setAgeCategoryEntityId(Long.parseLong(CvrsUtils.MIDDLE_AGED_ADULT));
        } else if (age >= 61 && age <= 120){
            citizenDto.setAgeCategoryEntityId(Long.parseLong(CvrsUtils.OLD_ADULT));
        } else {
            citizenDto.setAgeCategoryEntityId(null);
        }

        //vaccinated status
        citizenDto.setVaccinatedStatus(CvrsUtils.PENDING);

        //Registration Number
        citizenDto.setRegNum(Long.valueOf(System.currentTimeMillis()).toString());

        return citizenDto;

    }


    public String dateInString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    @Override
    public List<CitizenDto> findByStatusCode(String statusCode) {
        List<CitizenEntity> citizenEntities = new ArrayList<>();
        citizenEntities = citizenRepository.findAllByVaccinatedStatus(statusCode);
        if(citizenEntities.size() == 0){
            return null;
        }
        return  citizenMapper.mapToDto(citizenEntities);
    }

    @Override
    public List<CitizenDto> findByAgeCategory(Long id){

        List<CitizenEntity> citizenEntities = new ArrayList<>();
        citizenEntities = citizenRepository.findAllByAgeCategoryEntityId(id);

        if(citizenEntities.size() ==0){
            return null;
        }

        return citizenMapper.mapToDto(citizenEntities);
    }

    @Override
    public List<CitizenDto> findAllByMunicipality(String municipality, Integer wardNo) {
//        return citizenMapper.mapToDto(citizenRepository.findAllByLocationEntityMunicipalityAndVaccinatedStatus(municipality, "pending"));
        return citizenMapper.mapToDto(citizenRepository.findAllByLocationEntityMunicipalityAndLocationEntityWardNoAndVaccinatedStatusAndAgeCategoryEntityId(municipality, wardNo, "pending", 1L));
    }

    @Override
    public List<DashboardCustomDto> findAllCitizenDetailsForDashboard() {
        List<DashboardCustomDto> dashboardCustomDtos = new ArrayList<>();

        List<CitizenEntity> citizenEntities = citizenRepository.findAll();
        for (CitizenEntity citizenEntity:
             citizenEntities) {
            DashboardCustomDto dto = new DashboardCustomDto();
            // citizen
            dto.setFirstName(citizenEntity.getFirstName());
            dto.setLastName(citizenEntity.getLastName());
            dto.setCitizenship(citizenEntity.getCitizenship());
            dto.setAgeCategory(citizenEntity.getAgeCategoryEntity().getName());
            dto.setDob(getDateOnly(citizenEntity.getDob()));
            dto.setEmail(citizenEntity.getEmail());
            dto.setGender(citizenEntity.getGender());
            dto.setPhoneNum(citizenEntity.getPhoneNum());
            dto.setVaccinatedStatus(citizenEntity.getVaccinatedStatus());
            dto.setRegNum(citizenEntity.getRegNum());

            //locaiton
            dto.setDistrict(citizenEntity.getLocationEntity().getDistrict());
            dto.setState(citizenEntity.getLocationEntity().getState());
            dto.setZone(citizenEntity.getLocationEntity().getZone());
            dto.setMunicipality(citizenEntity.getLocationEntity().getMunicipality());
            dto.setWardNo(citizenEntity.getLocationEntity().getWardNo());

            // occupatoin medical condition and age ctegorh
            dto.setOccupation(citizenEntity.getOccupationEntity().getName());
            dto.setMedicalCondition(citizenEntity.getMedicalConditionEntity().getName());
            dto.setAgeCategory(citizenEntity.getAgeCategoryEntity().getName());

            // vaccine detail
            if(citizenEntity.getVaccineEntity() != null) {
                dto.setVaccineId(citizenEntity.getVaccineEntity().getId());
                dto.setVaccineName(citizenEntity.getVaccineEntity().getName());
            }

            dashboardCustomDtos.add(dto);


        }

        if(dashboardCustomDtos.size() != 0) {
            return dashboardCustomDtos;
        }
        return null;
    }

    @Override
    public CitizenEntity findByCitizenship(String citizenship) {
        CitizenEntity citizenEntity = new CitizenEntity();
        citizenEntity =  citizenRepository.findByCitizenshipEquals(citizenship);
        if(citizenEntity.getCitizenship() != null) {
            return citizenEntity;
        }

        return null;
    }

    @Override
    public List<CitizenEntity> findByRegistrationNumber(Long registrationNumber) {
        List<CitizenEntity> citizenEntities = citizenRepository.findAllByRegNum(registrationNumber.toString());
        if (citizenEntities.isEmpty()) {
            return null;
        }

        return citizenEntities;

    }

    @Override
    public Map<String, Object> generateRegistrationReport(CitizenDto citizenDto) {
        Map<String, Object> objectMap = new HashMap<>();
        Map<String, String> firstDose = new HashMap<>();
        Map<String,String> secondDose= new HashMap<>();
        List<Map<String,String>> maps = new ArrayList<>();

        List<VaccinationLogEntity> vaccinationLogEntities = vaccinationLogService.findAllByRegistrationNumber(citizenDto.getRegNum());

        if (citizenDto.getVaccinatedStatus().equalsIgnoreCase(CvrsUtils.PENDING)) {
            VaccineReportCustomDto reportCustomDto = new VaccineReportCustomDto();
            reportCustomDto.setCitizenDto(citizenDto);
            objectMap.put("-1",reportCustomDto);
            return objectMap;
        }
        if(citizenDto.getVaccinatedStatus().equalsIgnoreCase(CvrsUtils.GOT_ONE)) {
            VaccineEntity vaccineEntity = new VaccineEntity();
//            if(vaccineService.findById(citizenDto.getVaccineId()).isPresent()) {
//                vaccineEntity = vaccineRepository.findById(citizenDto.getVaccineId()).get();
//            } else {
//                throw new NotFoundException("Vaccine details not found");
//            }
            vaccineEntity = vaccineService.findById(citizenDto.getVaccineId());

            VaccineDto vaccineDto = vaccineMapper.mapToDto(vaccineEntity);


            VaccineReportCustomDto reportCustomDto = new VaccineReportCustomDto(citizenDto, vaccineDto);

            if(vaccinationLogEntities !=null){
                firstDose.put("firstDoseDate", getDateOnly(vaccinationLogEntities.get(0).getCreatedDate()));
                firstDose.put("givenBy", adminService.findById(vaccinationLogEntities.get(0).getAdminId()).getName());
            }
            maps.add(firstDose);
            reportCustomDto.setVaccineLog(maps);

            objectMap.put("-2", reportCustomDto);

            return objectMap;
        }
        firstDose.put("firstDoseDate", getDateOnly(vaccinationLogEntities.get(0).getCreatedDate()));
        firstDose.put("givenBy", adminService.findById(vaccinationLogEntities.get(0).getAdminId()).getName());

        secondDose.put("secondDoseDate", getDateOnly(vaccinationLogEntities.get(0).getCreatedDate()));
        secondDose.put("givenBy", adminService.findById(vaccinationLogEntities.get(1).getAdminId()).getName());

        maps.add(firstDose);
        maps.add(secondDose);

        VaccineDto vaccineDto1 = vaccineMapper.mapToDto(vaccineService.findById(citizenDto.getVaccineId()));

        objectMap.put("-3", new VaccineReportCustomDto(citizenDto,vaccineDto1,maps));

        return objectMap;
    }

    @Override
    public CitizenEntity findByRegistrationNUmberStr(String registrationNumber) {
        return citizenRepository.findByRegNum(registrationNumber);
    }

    private String getDateOnly(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }


}


