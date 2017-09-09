package com.denisroyz.geofence.ui.geofence;

import com.denisroyz.geofence.dao.GPSRule;
import com.denisroyz.geofence.dao.WifiRule;

/**
 * Created by Heralt on 05.09.2017.
 */

public interface GeofenceView {
    void displayGeofenceStatus(boolean geoFenceStatus);

    void displayRulesPicker(GPSRule gpsRule, WifiRule wifiRule);

    void displayGeoFenceEnabled(boolean isSearchEnabled);

    void displayPermissionRequestView(boolean visible);
}
