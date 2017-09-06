package com.denisroyz.geofence.service;

/**
 * Created by Heralt on 06.09.2017.
 */

public interface GeofenceReceiver {

    void addGeofenceReceiverListener(GeofenceReceiverListener geofenceReceiverListener);

    void removeGeofenceReceiverListener(GeofenceReceiverListener geofenceReceiverListener);

    void notifyListeners();

    boolean getGeoFenceStatus();
}
