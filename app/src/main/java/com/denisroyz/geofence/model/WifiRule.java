package com.denisroyz.geofence.model;

import java.io.Serializable;

/**
 * Created by Heralt on 06.09.2017.
 */

public class WifiRule implements GeofenceRule, Serializable {

    private String wifiNetworkName;

    public String getWifiNetworkName() {
        return wifiNetworkName;
    }

    public void setWifiNetworkName(String wifiNetworkName) {
        this.wifiNetworkName = wifiNetworkName;
    }
}
