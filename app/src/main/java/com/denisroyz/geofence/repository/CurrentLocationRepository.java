package com.denisroyz.geofence.repository;

import com.denisroyz.geofence.model.UserLocation;
import com.denisroyz.geofence.service.GeofenceReceiverImpl;

/**
 * Created by Heralt on 07.09.2017.
 */

public interface CurrentLocationRepository {
    void updateCurrentLocation(UserLocation userLocation);

    void addOnChangeListener(UserLocationRepositoryUpdateListener listener);
    void removeOnChangeListener(UserLocationRepositoryUpdateListener listener);
}
