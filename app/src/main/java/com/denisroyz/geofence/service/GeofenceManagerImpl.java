package com.denisroyz.geofence.service;

import android.content.Context;
import android.content.Intent;

import com.denisroyz.geofence.Const;
import com.denisroyz.geofence.ui.geofence.GeofenceServiceFg;

/**
 * Created by Heralt on 05.09.2017.
 */
public class GeofenceManagerImpl implements GeofenceManager {

    private Context context;

    // should be asked directly from service
    private boolean enabled;

    public GeofenceManagerImpl(Context context){
        this.context = context;
    }

    @Override
    public void startService(){
        Intent startIntent = new Intent(context, GeofenceServiceFg.class);
        startIntent.setAction(Const.ACTION.START_FOREGROUND_ACTION);
        context.startService(startIntent);
        enabled = true;
    }

    @Override
    public void stopService() {
        Intent startIntent = new Intent(context, GeofenceServiceFg.class);
        startIntent.setAction(Const.ACTION.STOP_FOREGROUND_ACTION);
        context.startService(startIntent);
        enabled = false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
