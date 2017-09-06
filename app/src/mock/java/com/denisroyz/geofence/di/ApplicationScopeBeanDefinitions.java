package com.denisroyz.geofence.di;

/**
 * Created by Heralt on 06.09.2017.
 */

public class ApplicationScopeBeanDefinitions {

    public void inject(DIContext diContext){
        GeofenceManagerBeanDefinition geofenceManagerBeanDefinition = new GeofenceManagerBeanDefinition();
        diContext.addBeanDefinition(geofenceManagerBeanDefinition);
    }
}
