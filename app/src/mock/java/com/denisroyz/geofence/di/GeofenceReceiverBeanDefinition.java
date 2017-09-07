package com.denisroyz.geofence.di;

import com.denisroyz.geofence.service.GeofenceReceiver;
import com.denisroyz.geofence.service.GeofenceReceiverImpl;

/**
 * Created by Heralt on 06.09.2017.
 */

public class GeofenceReceiverBeanDefinition implements BeanDefinition<GeofenceReceiver> {
    @Override
    public GeofenceReceiver createBean(DIContext diContext) {
        return new GeofenceReceiverImpl();
    }

    @Override
    public String getTag() {
        return BeanTags.GEOFENCE_RECEIVER;
    }
}
