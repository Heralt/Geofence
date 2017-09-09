package com.denisroyz.geofence.repository;

import com.denisroyz.geofence.dao.UserLocation;

import java.util.HashSet;
import java.util.Set;

/**
 * Simple implementation of {@link UserLocationRepository}.
 * Stores {@link UserLocation} object inside it's own fields.
 * Notifies listeners once this object is changed.
 */
public class UserLocationRepositoryImpl implements UserLocationRepository {

    private UserLocation mUserLocation;
    private Set<UserLocationRepositoryUpdateListener> listeners = new HashSet<>();

    @Override
    public void updateUserLocation(UserLocation userLocation) {
        mUserLocation = userLocation;
        notifyListeners(mUserLocation);
    }

    @Override
    public UserLocation getUserLocation() {
        return mUserLocation;
    }

    @Override
    public void addOnChangeListener(UserLocationRepositoryUpdateListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeOnChangeListener(UserLocationRepositoryUpdateListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(UserLocation userLocation){
        for (UserLocationRepositoryUpdateListener listener:listeners){
            listener.onUserLocationUpdated(userLocation);
        }
    }
}
