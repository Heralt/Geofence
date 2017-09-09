package com.denisroyz.geofence.di;

import android.content.Context;

import com.denisroyz.geofence.repository.GeofenceRuleRepository;
import com.denisroyz.geofence.repository.GeofenceRuleRepositoryImpl;
import com.denisroyz.geofence.service.GeofenceWiFiManager;
import com.denisroyz.geofence.service.ObjectSerializer;

/**
 * Created by Heralt on 06.09.2017.
 *
 * Defines, how to create {@link GeofenceRuleRepository} bean
 */
public class GeofenceRuleRepositoryBeanDefinition implements BeanDefinition<GeofenceRuleRepository> {
    @Override
    public GeofenceRuleRepository createBean(DIContext diContext) {
        Context context = (Context) diContext.getBean(BeanTags.CONTEXT);
        ObjectSerializer objectSerializer = (ObjectSerializer) diContext.getBean(BeanTags.OBJECT_SERIALIZER);
        return new GeofenceRuleRepositoryImpl(context, objectSerializer);
    }

    @Override
    public String getTag() {
        return BeanTags.RULES_REPOSITORY;
    }
}
