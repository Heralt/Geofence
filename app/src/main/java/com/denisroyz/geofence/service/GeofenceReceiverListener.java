package com.denisroyz.geofence.service;

/**
 * Created by Heralt on 06.09.2017.
 */

public interface GeofenceReceiverListener {

    void onGeofenceStatusUpdated(boolean inside);
}
