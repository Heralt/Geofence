package com.denisroyz.geofence.repository;

import com.denisroyz.geofence.dao.GPSRule;
import com.denisroyz.geofence.dao.WifiRule;

/**
 * Created by Heralt on 06.09.2017.
 *
 * Implementation of this class should encapsulate all job with storing, getting instances of
 * objects related to "Geofence rules"
 */
public interface GeofenceRuleRepository {

    GPSRule getGpsRule();
    boolean saveGpsRule(GPSRule gpsRule);
    WifiRule getWifiRule();
    boolean saveWifiRule(WifiRule wifiRule);

    void addOnChangeListener(RuleRepositoryUpdateListener listener);
    void removeOnChangeListener(RuleRepositoryUpdateListener listener);

    interface RuleRepositoryUpdateListener {
        void onGpsRuleUpdated(GPSRule gpsRule);
        void onWifiRuleUpdated(WifiRule wifiRule);
    }
}
