package com.denisroyz.geofence.repository;

import com.denisroyz.geofence.model.GPSRule;
import com.denisroyz.geofence.model.WifiRule;

/**
 * Created by Heralt on 06.09.2017.
 */

public class GeofenceRuleRepositoryImpl implements GeofenceRuleRepository {

    private GPSRule mGpsRule;
    private WifiRule mWifiRule;

    @Override
    public GPSRule getGpsRule() {
        return mGpsRule;
    }

    @Override
    public void saveGpsRule(GPSRule gpsRule) {
        this.mGpsRule = gpsRule;
    }

    @Override
    public WifiRule getWifiRule() {
        return mWifiRule;
    }

    @Override
    public void saveWifiRule(WifiRule wifiRule) {
        this.mWifiRule = wifiRule;
    }
}
