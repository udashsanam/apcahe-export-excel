package com.cvrs.backend.service.implementation;

import com.cvrs.backend.entity.LocationEntity;
import com.cvrs.backend.repository.LocationRepository;
import com.cvrs.backend.service.ILocationService;
import com.cvrs.backend.service.implementation.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class LocationServiceImpl extends BaseServiceImpl<LocationEntity, Long> implements ILocationService {
    private LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(JpaRepository<LocationEntity, Long> repository, LocationRepository locationRepository) {
        super(repository);
        this.locationRepository = locationRepository;
    }

}
