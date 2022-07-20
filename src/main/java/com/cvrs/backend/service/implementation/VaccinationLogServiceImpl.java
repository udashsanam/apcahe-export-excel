package com.cvrs.backend.service.implementation;

import com.cvrs.backend.entity.VaccinationLogEntity;
import com.cvrs.backend.repository.VaccinationLogRepository;
import com.cvrs.backend.service.IVaccinationLogService;
import com.cvrs.backend.service.base.IBaseService;
import com.cvrs.backend.service.implementation.base.BaseServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class VaccinationLogServiceImpl extends BaseServiceImpl<VaccinationLogEntity, Long> implements IVaccinationLogService {

    private VaccinationLogRepository vaccinationLogRepository;
    public VaccinationLogServiceImpl(JpaRepository<VaccinationLogEntity, Long> repository,
                                     VaccinationLogRepository vaccinationLogRepository) {
        super(repository);
        this.vaccinationLogRepository = vaccinationLogRepository;
    }

    @Override
    public List<VaccinationLogEntity> findAllByRegistrationNumber(String registrationNumber) {

        List<VaccinationLogEntity> vaccinationLogEntities = new ArrayList<>();

        vaccinationLogEntities = vaccinationLogRepository.findAllByRegistrationNumberOrderByCreatedDateDesc(registrationNumber);
        if(vaccinationLogEntities.isEmpty()) {
            return null;
        }
        return vaccinationLogEntities;
    }
}
