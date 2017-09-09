package com.denisroyz.geofence.service;

import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by Heralt on 07.09.2017.
 *
 * Provides WiFi location information, using android {@link WifiManager}
 */
public class GeofenceWiFiManagerImpl implements GeofenceWiFiManager {

    WifiManager mWifiManager;

    public GeofenceWiFiManagerImpl(WifiManager wifiManager){
        this.mWifiManager = wifiManager;
    }

    @Override
    public String getWifiNetworkName() {
        if (mWifiManager.isWifiEnabled()) {
            WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
            if (wifiInfo != null) {
                NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
                if (state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
                    return extractWifiNetworkName(wifiInfo);
                }
            }
        }
        return null;
    }

    private String extractWifiNetworkName(WifiInfo wifiInfo){
        String serviceSetID =  wifiInfo.getSSID();
        return serviceSetID.substring(1, serviceSetID.length()-1);
    }
}
