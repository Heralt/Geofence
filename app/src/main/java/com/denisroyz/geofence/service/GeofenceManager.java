package com.denisroyz.geofence.service;

import com.denisroyz.geofence.repository.UserLocationRepository;

/**
 * Created by Heralt on 07.09.2017.
 *
 * Enables and disables (location listening process).
 * Should put data directly to {@link UserLocationRepository}
 */

public interface GeofenceManager {
    void startService();

    void stopService();

    boolean isEnabled();
}
