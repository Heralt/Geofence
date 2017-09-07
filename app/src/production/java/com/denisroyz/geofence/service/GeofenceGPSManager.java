package com.denisroyz.geofence.service;

import android.location.Location;

/**
 * Created by Heralt on 07.09.2017.
 */

public interface GeofenceGPSManager {
    boolean subscribe();
    void unSubscribe();
    Location getLastLocation();

    boolean isEnabled();
    boolean havePermission();
}
