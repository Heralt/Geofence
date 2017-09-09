package com.denisroyz.geofence.service;

import com.denisroyz.geofence.repository.GeofenceRuleRepository;
import com.denisroyz.geofence.repository.UserLocationRepository;

/**
 * Created by Heralt on 06.09.2017.
 *
 * Provides User location information from {@link UserLocationRepository}
 * and Rules information from {@link GeofenceRuleRepository}
 *
 *
 */
public interface GeofenceReceiver {

    void addGeofenceReceiverListener(GeofenceReceiverListener geofenceReceiverListener);

    void removeGeofenceReceiverListener(GeofenceReceiverListener geofenceReceiverListener);

    void notifyListeners();

    boolean getGeoFenceStatus();

    interface GeofenceReceiverListener {
        void onGeofenceStatusUpdated(boolean inside);
    }
}
