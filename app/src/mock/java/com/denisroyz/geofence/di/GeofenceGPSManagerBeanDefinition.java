package com.denisroyz.geofence.di;

import com.denisroyz.geofence.service.GeofenceGPSManager;
import com.denisroyz.geofence.service.GeofenceGPSManagerMockImpl;

/**
 * Created by Heralt on 07.09.2017.
 */

public class GeofenceGPSManagerBeanDefinition implements BeanDefinition<GeofenceGPSManager> {
    @Override
    public GeofenceGPSManager createBean(DIContext diContext) {
        return new GeofenceGPSManagerMockImpl();
    }

    @Override
    public String getTag() {
        return BeanTags.GEOFENCE_GPS_MANAGER;
    }
}
