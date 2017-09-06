package com.denisroyz.geofence.service;

import android.content.Context;
import android.content.Intent;

import com.denisroyz.geofence.Const;
import com.denisroyz.geofence.ui.geofence.GeofenceServiceFg;

/**
 * Created by Heralt on 05.09.2017.
 */

public class GeofenceManager {

    private Context context;

    public GeofenceManager(Context context){
        this.context = context;
    }

    public void startService(){
        Intent startIntent = new Intent(context, GeofenceServiceFg.class);
        startIntent.setAction(Const.ACTION.START_FOREGROUND_ACTION);
        context.startService(startIntent);
    }

    public void stopService() {
        Intent startIntent = new Intent(context, GeofenceServiceFg.class);
        startIntent.setAction(Const.ACTION.STOP_FOREGROUND_ACTION);
        context.startService(startIntent);

    }
}
