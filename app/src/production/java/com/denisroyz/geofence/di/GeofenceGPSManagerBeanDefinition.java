package com.denisroyz.geofence.di;

import android.content.Context;

import com.denisroyz.geofence.service.GeofenceGPSManager;
import com.denisroyz.geofence.service.GeofenceGPSManagerImpl;

/**
 * Created by Heralt on 07.09.2017.
 *
 * Defines, how to create {@link GeofenceGPSManager} bean
 */
public class GeofenceGPSManagerBeanDefinition implements BeanDefinition<GeofenceGPSManager> {
    @Override
    public GeofenceGPSManager createBean(DIContext diContext) {
        Context context = (Context) diContext.getBean(BeanTags.CONTEXT);
        return new GeofenceGPSManagerImpl(context);
    }

    @Override
    public String getTag() {
        return BeanTags.GEOFENCE_GPS_MANAGER;
    }
}
