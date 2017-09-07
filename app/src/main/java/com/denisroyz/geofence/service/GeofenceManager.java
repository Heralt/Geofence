package com.denisroyz.geofence.service;

/**
 * Created by Heralt on 07.09.2017.
 */

public interface GeofenceManager {
    void startService();

    void stopService();

    boolean isEnabled();
}
