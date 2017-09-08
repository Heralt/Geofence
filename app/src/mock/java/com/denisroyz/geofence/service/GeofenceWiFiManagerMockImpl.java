package com.denisroyz.geofence.service;

/**
 * Created by Heralt on 09.09.2017.
 */

public class GeofenceWiFiManagerMockImpl implements GeofenceWiFiManager {
    @Override
    public String getWifiNetworkName() {
        return "MOCK";
    }
}
