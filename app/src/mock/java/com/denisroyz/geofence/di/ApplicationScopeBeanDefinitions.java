package com.denisroyz.geofence.di;

/**
 * Created by Heralt on 06.09.2017.
 */

public class ApplicationScopeBeanDefinitions extends BaseApplicationScopeBeanDefinitions {

    @Override
    public void inject(DIContext diContext){
        super.inject(diContext);
        diContext.addBeanDefinition(new GeofenceManagerBeanDefinition());
        diContext.addBeanDefinition(new GeofenceReceiverBeanDefinition());
        diContext.addBeanDefinition(new GeofenceRuleRepositoryBeanDefinition());
    }
}
