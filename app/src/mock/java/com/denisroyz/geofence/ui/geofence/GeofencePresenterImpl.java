package com.denisroyz.geofence.ui.geofence;

import android.content.Context;
import android.util.Log;

import com.denisroyz.geofence.GeofenceApplication;
import com.denisroyz.geofence.di.BeanTags;
import com.denisroyz.geofence.di.DIContext;
import com.denisroyz.geofence.geo.GeofenceManager;

/**
 * Created by Heralt on 05.09.2017.
 */

public class GeofencePresenterImpl implements GeofencePresenter{

    private GeofenceManager geofenceManager;

    public GeofencePresenterImpl(){
        initDependencies();
    }

    private void initDependencies(){
        geofenceManager = (GeofenceManager) GeofenceApplication.getApplicationDIContext().getBean(BeanTags.GEOFENCE_MANAGER);
    }

    public static final String LOG_TAG = "GeofencePresenterImpl";

    @Override
    public void enableSearch(boolean enable) {
        Log.d(LOG_TAG, String.format("enableSearch(%b)", enable));
        if (enable) {
            geofenceManager.startService();
        } else {
            geofenceManager.stopService();
        }
    }
}
