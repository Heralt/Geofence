package com.denisroyz.geofence.di;

/**
 * Created by Heralt on 06.09.2017.
 */

public class ApplicationScopeBeanDefinitions {

    public void inject(DIContext diContext){
        diContext.addBeanDefinition(new GeofenceManagerBeanDefinition());
        diContext.addBeanDefinition(new GeofenceReceiverBeanDefinition());
        diContext.addBeanDefinition(new GeofenceRuleRepositoryBeanDefinition());
    }
}
