package com.denisroyz.geofence.di;

import android.content.Context;

import com.denisroyz.geofence.service.GeofenceManager;
import com.denisroyz.geofence.service.GeofenceManagerImpl;

/**
 * Created by Heralt on 06.09.2017.
 */

public class GeofenceManagerBeanDefinition implements BeanDefinition<GeofenceManager> {
    @Override
    public GeofenceManager createBean(DIContext diContext) {
        Context context = (Context) diContext.getBean(BeanTags.CONTEXT);
        return new GeofenceManagerImpl(context);
    }

    @Override
    public String getTag() {
        return BeanTags.GEOFENCE_MANAGER;
    }
}
