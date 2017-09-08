package com.denisroyz.geofence.repository;

import com.denisroyz.geofence.model.GPSRule;
import com.denisroyz.geofence.model.WifiRule;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by Heralt on 06.09.2017.
 */

public class GeofenceRuleRepositoryImpl implements GeofenceRuleRepository {

    private static final String LOG_TAG = "RuleRepositoryImpl";

    private Set<RuleRepositoryUpdateListener> listeners = new HashSet<>();

    private static final String GPS_RULE_KEY = "com.denisroyz.geofence.rule.prefs.gps_rule";
    private static final String WIFI_RULE_KEY = "com.denisroyz.geofence.rule.prefs.wifi_rule";


    private GPSRule gpsRule;
    private WifiRule wifiRule;
    public GeofenceRuleRepositoryImpl(){
    }

    @Override
    public GPSRule getGpsRule() {
        Object object = gpsRule;
        if (object!=null ) return (GPSRule) object;
        return new GPSRule();
    }

    @Override
    public WifiRule getWifiRule() {
        Object object = wifiRule;
        if (object!=null ) return (WifiRule) object;
        return new WifiRule();
    }

    @Override
    public boolean saveWifiRule(WifiRule wifiRule) {
        this.wifiRule = wifiRule;
        notifyWifiRuleUpdated(wifiRule);
        return true;
    }

    @Override
    public boolean saveGpsRule(GPSRule gpsRule) {
        this.gpsRule = gpsRule;
        notifyGpsRuleUpdated(gpsRule);
        return true;
    }

    private void notifyGpsRuleUpdated(GPSRule gpsRule) {
        for (RuleRepositoryUpdateListener listener: listeners){
            listener.onGpsRuleUpdated(gpsRule);
        }

    }

    private void notifyWifiRuleUpdated(WifiRule wifiRule) {
        for (RuleRepositoryUpdateListener listener: listeners){
            listener.onWifiRuleUpdated(wifiRule);
        }
    }

    @Override
    public void addOnChangeListener(RuleRepositoryUpdateListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeOnChangeListener(RuleRepositoryUpdateListener listener) {
        listeners.remove(listener);
    }

}
