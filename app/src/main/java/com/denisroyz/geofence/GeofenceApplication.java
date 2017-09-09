package com.denisroyz.geofence;

import android.app.Application;

import com.denisroyz.geofence.di.ApplicationScopeBeanDefinitions;
import com.denisroyz.geofence.di.BeanTags;
import com.denisroyz.geofence.di.DIContext;

/**
 * Created by Heralt on 06.09.2017.
 *
 * Have to emphasize. {@link DIContext} class is written because of server reasons.
 * 1) As we discussed with "Dane McLeod" i am not using any external non-android dependency.
 * 2) This is just test assignment, not a production grade code. So i can write some unstable,
 * unsupportable code, invent wheels, write something, i could never do in production.
 * 3) I wanted to implement some DI infrastructure without using (out of the box) solutions, and
 * without reflection.
 * 4) This code was written after 9 hours working day on my current job. So i am sure this can look
 * pretty terrifyingly for a person with fresh mind. Unfortunately my mind was exhausted during
 * this code assignment implementation (you can check commits time), so i had no opportunity to
 * check it with 'fresh mind"
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
