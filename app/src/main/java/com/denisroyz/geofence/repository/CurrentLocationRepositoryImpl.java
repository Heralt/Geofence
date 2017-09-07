package com.denisroyz.geofence.repository;

import com.denisroyz.geofence.model.UserLocation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Heralt on 07.09.2017.
 */

public class CurrentLocationRepositoryImpl implements CurrentLocationRepository {
    private UserLocation mUserLocation;

    @Override
    public void updateCurrentLocation(UserLocation userLocation) {
        mUserLocation = userLocation;
        notifyListeners(mUserLocation);
    }

    public void notifyListeners(UserLocation userLocation){
        for (UserLocationRepositoryUpdateListener listener:listeners){
            listener.onUserLocationUpdated(userLocation);
        }
    }

    Set<UserLocationRepositoryUpdateListener> listeners = new HashSet<>();

    @Override
    public void addOnChangeListener(UserLocationRepositoryUpdateListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeOnChangeListener(UserLocationRepositoryUpdateListener listener) {
        listeners.remove(listener);
    }
}
