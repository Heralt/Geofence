package com.denisroyz.geofence.dao;

import java.io.Serializable;

public class WifiRule implements Serializable {

    private static final long serialVersionUID = 3L;

    private String wifiNetworkName;

    public String getWifiNetworkName() {
        return wifiNetworkName;
    }

    public void setWifiNetworkName(String wifiNetworkName) {
        this.wifiNetworkName = wifiNetworkName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WifiRule wifiRule = (WifiRule) o;

        return wifiNetworkName != null ? wifiNetworkName.equals(wifiRule.wifiNetworkName) : wifiRule.wifiNetworkName == null;

    }

    @Override
    public int hashCode() {
        return wifiNetworkName != null ? wifiNetworkName.hashCode() : 0;
    }
}
