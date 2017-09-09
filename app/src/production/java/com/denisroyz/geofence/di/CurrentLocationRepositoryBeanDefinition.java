package com.denisroyz.geofence.di;

import com.denisroyz.geofence.repository.UserLocationRepository;
import com.denisroyz.geofence.repository.UserLocationRepositoryImpl;

/**
 * Defines, how to create {@link UserLocationRepository} bean
 */
public class CurrentLocationRepositoryBeanDefinition implements BeanDefinition<UserLocationRepository> {
    @Override
    public UserLocationRepository createBean(DIContext diContext) {
        return new UserLocationRepositoryImpl();
    }

    @Override
    public String getTag() {
        return BeanTags.USER_LOCATION_REPOSITORY;
    }
}
