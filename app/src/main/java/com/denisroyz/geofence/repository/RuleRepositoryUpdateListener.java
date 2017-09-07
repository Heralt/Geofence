package com.denisroyz.geofence.repository;

import com.denisroyz.geofence.model.GPSRule;
import com.denisroyz.geofence.model.WifiRule;

/**
 * Created by Heralt on 07.09.2017.
 */

public interface RuleRepositoryUpdateListener {
    void onGpsRuleUpdated(GPSRule gpsRule);
    void onWifiRuleUpdated(WifiRule wifiRule);
}
