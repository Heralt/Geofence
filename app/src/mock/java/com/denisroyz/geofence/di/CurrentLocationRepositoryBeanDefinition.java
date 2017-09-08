package com.denisroyz.geofence.di;


import com.denisroyz.geofence.repository.CurrentLocationRepository;
import com.denisroyz.geofence.repository.CurrentLocationRepositoryImpl;

/**
 * Created by Heralt on 06.09.2017.
 */

public class CurrentLocationRepositoryBeanDefinition implements BeanDefinition<CurrentLocationRepository> {
    @Override
    public CurrentLocationRepository createBean(DIContext diContext) {
        return new CurrentLocationRepositoryImpl();
    }

    @Override
    public String getTag() {
        return BeanTags.USER_LOCATION_REPOSITORY;
    }
}
