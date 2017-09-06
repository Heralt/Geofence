package com.denisroyz.geofence.repository;

import com.denisroyz.geofence.model.GPSRule;
import com.denisroyz.geofence.model.WifiRule;

/**
 * Created by Heralt on 06.09.2017.
 */

public interface GeofenceRuleRepository {

    GPSRule getGpsRule();
    void saveGpsRule(GPSRule gpsRule);
    WifiRule getWifiRule();
    void saveWifiRule(WifiRule wifiRule);
}
