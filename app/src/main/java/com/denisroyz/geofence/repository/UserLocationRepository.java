package com.denisroyz.geofence.repository;

import com.denisroyz.geofence.dao.UserLocation;

/**
 * Created by Heralt on 07.09.2017.
 *
 * Implementation of this class should encapsulate all job with storing, getting instances of
 * objects related to "Current device location"
 */
public interface UserLocationRepository {
    void updateUserLocation(UserLocation userLocation);
    UserLocation getUserLocation();

    void addOnChangeListener(UserLocationRepositoryUpdateListener listener);
    void removeOnChangeListener(UserLocationRepositoryUpdateListener listener);

    interface UserLocationRepositoryUpdateListener {
        void onUserLocationUpdated(UserLocation userLocation);
    }
}
