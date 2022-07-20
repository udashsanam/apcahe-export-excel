package com.cvrs.backend.service.implementation;

import com.cvrs.backend.dto.VaccineDto;
import com.cvrs.backend.entity.VaccineDistributionCenterEntity;
import com.cvrs.backend.entity.VaccineDistributionCenterToVaccineEntity;
import com.cvrs.backend.entity.VaccineEntity;
import com.cvrs.backend.mapper.VaccineMapper;
import com.cvrs.backend.repository.VaccineDistributionCenterRepository;
import com.cvrs.backend.repository.VaccineDistributionCenterToVaccineRepository;
import com.cvrs.backend.repository.VaccineRepository;
import com.cvrs.backend.service.IVaccineService;
import com.cvrs.backend.service.implementation.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class VaccineServiceImpl extends BaseServiceImpl<VaccineEntity, Long> implements com.cvrs.backend.service.IVaccineService {
    private VaccineRepository vaccineRepository;
    private VaccineDistributionCenterToVaccineRepository vaccineDistributionCenterToVaccineRepository;
    private VaccineDistributionCenterRepository vaccineDistributionCenterRepository;
    private VaccineMapper vaccineMapper;

    @Autowired
    public VaccineServiceImpl(JpaRepository<VaccineEntity, Long> repository, VaccineRepository vaccineRepository , VaccineDistributionCenterToVaccineRepository vaccineDistributionCenterToVaccineRepository,
                              VaccineDistributionCenterRepository vaccineDistributionCenterRepository,
                              VaccineMapper vaccineMapper) {
        super(repository);
        this.vaccineRepository = vaccineRepository;
        this.vaccineDistributionCenterToVaccineRepository = vaccineDistributionCenterToVaccineRepository;
        this.vaccineDistributionCenterRepository = vaccineDistributionCenterRepository;
        this.vaccineMapper = vaccineMapper;
    }

    @Override
    public List<VaccineDto> findAllWithDistributionCenter(String center) {

        VaccineDistributionCenterEntity centerEntity = vaccineDistributionCenterRepository.findByName(center);
        List<VaccineEntity> vaccineEntityList = new ArrayList<>();
        if(centerEntity != null){

            Long centerId = centerEntity.getId();
            List<VaccineDistributionCenterToVaccineEntity> vaccineToDistributionEntityList = vaccineDistributionCenterToVaccineRepository.findAllById(Collections.singleton(centerId));
            if(vaccineEntityList.size() !=0){
                for (VaccineDistributionCenterToVaccineEntity entity:
                     vaccineToDistributionEntityList) {
                    if(entity.getVaccineEntity() != null)
                    {
                        vaccineEntityList.add(entity.getVaccineEntity());
                    }

                }
            }

        }

        if (vaccineEntityList.size() == 0){
            return null;
        }

        return vaccineMapper.mapToDto(vaccineEntityList);
    }
}
