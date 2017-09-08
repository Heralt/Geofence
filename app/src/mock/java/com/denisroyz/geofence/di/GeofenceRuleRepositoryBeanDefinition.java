package com.denisroyz.geofence.di;


import com.denisroyz.geofence.repository.GeofenceRuleRepository;
import com.denisroyz.geofence.repository.GeofenceRuleRepositoryImpl;

/**
 * Created by Heralt on 06.09.2017.
 */

public class GeofenceRuleRepositoryBeanDefinition implements BeanDefinition<GeofenceRuleRepository> {
    @Override
    public GeofenceRuleRepository createBean(DIContext diContext) {
        return new GeofenceRuleRepositoryImpl();
    }

    @Override
    public String getTag() {
        return BeanTags.RULES_REPOSITORY;
    }
}
