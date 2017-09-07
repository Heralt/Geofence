package com.denisroyz.geofence.di;

import android.content.Context;
import android.net.wifi.WifiManager;

import com.denisroyz.geofence.service.GeofenceWiFiManager;
import com.denisroyz.geofence.service.GeofenceWiFiManagerImpl;

/**
 * Created by Heralt on 07.09.2017.
 */

public class GeofenceWifiManagerBeanDefinition implements BeanDefinition<GeofenceWiFiManager> {
    @Override
    public GeofenceWiFiManager createBean(DIContext diContext) {
        Context context = (Context) diContext.getBean(BeanTags.CONTEXT);
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return new GeofenceWiFiManagerImpl(wifiManager);
    }

    @Override
    public String getTag() {
        return BeanTags.GEOFENCE_WIFI_MANAGER;
    }
}
