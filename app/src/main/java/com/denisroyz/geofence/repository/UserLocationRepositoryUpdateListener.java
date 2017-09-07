package com.denisroyz.geofence.repository;

import com.denisroyz.geofence.model.UserLocation;

/**
 * Created by Heralt on 07.09.2017.
 */

public interface UserLocationRepositoryUpdateListener {
    void onUserLocationUpdated(UserLocation userLocation);
}
