package com.denisroyz.geofence.di;

import com.denisroyz.geofence.service.GeofenceWiFiManager;
import com.denisroyz.geofence.service.GeofenceWiFiManagerMockImpl;

/**
 * Created by Heralt on 07.09.2017.
 */

public class GeofenceWifiManagerBeanDefinition implements BeanDefinition<GeofenceWiFiManager> {
    @Override
    public GeofenceWiFiManager createBean(DIContext diContext) {
        return new GeofenceWiFiManagerMockImpl();
    }

    @Override
    public String getTag() {
        return BeanTags.GEOFENCE_WIFI_MANAGER;
    }
}
