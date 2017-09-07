package com.denisroyz.geofence.repository;

import com.denisroyz.geofence.model.GPSRule;
import com.denisroyz.geofence.model.WifiRule;

/**
 * Created by Heralt on 06.09.2017.
 */

public interface GeofenceRuleRepository {

    GPSRule getGpsRule();
    boolean saveGpsRule(GPSRule gpsRule);
    WifiRule getWifiRule();
    boolean saveWifiRule(WifiRule wifiRule);


    void addOnChangeListener(RuleRepositoryUpdateListener listener);
    void removeOnChangeListener(RuleRepositoryUpdateListener listener);
}
