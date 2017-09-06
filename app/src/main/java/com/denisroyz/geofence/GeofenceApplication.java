package com.denisroyz.geofence;

import android.app.Application;

import com.denisroyz.geofence.di.ApplicationScopeBeanDefinitions;
import com.denisroyz.geofence.di.BeanTags;
import com.denisroyz.geofence.di.DIContext;

/**
 * Created by Heralt on 05.09.2017.
 */

public class GeofenceApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DIContext diContext = new DIContext();
        diContext.addBean(BeanTags.CONTEXT, this);
        new ApplicationScopeBeanDefinitions().inject(diContext);
        applicationDIContext = diContext;
    }

    private static DIContext applicationDIContext;

    public static DIContext getApplicationDIContext(){
        return applicationDIContext;
    }



}
