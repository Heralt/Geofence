package com.denisroyz.geofence.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.denisroyz.geofence.Const;
import com.denisroyz.geofence.model.GPSRule;
import com.denisroyz.geofence.model.WifiRule;
import com.denisroyz.geofence.service.ObjectSerializer;

import java.io.IOException;
import java.io.Serializable;


/**
 * Created by Heralt on 06.09.2017.
 */

public class GeofenceRuleRepositoryImpl implements GeofenceRuleRepository {

    private static final String LOG_TAG = "RuleRepositoryImpl";

    private SharedPreferences sharedPreferences;
    private ObjectSerializer objectSerializer;

    private static final String GPS_RULE_KEY = "com.denisroyz.geofence.rule.prefs.gps_rule";
    private static final String WIFI_RULE_KEY = "com.denisroyz.geofence.rule.prefs.wifi_rule";


    public GeofenceRuleRepositoryImpl(Context context, ObjectSerializer objectSerializer){
        this.objectSerializer = objectSerializer;
        sharedPreferences = context.getSharedPreferences(
                Const.PREFS.PREFS_GEOFENCE_RULE, Context.MODE_PRIVATE);
    }

    @Override
    public GPSRule getGpsRule() {
        //TODO rework default object

        Object object = readPref(GPS_RULE_KEY);
        if (object!=null && object instanceof GPSRule) return (GPSRule) object;
        return new GPSRule();
    }

    @Override
    public WifiRule getWifiRule() {
        //TODO rework default object
        Object object = readPref(WIFI_RULE_KEY);
        if (object!=null && object instanceof WifiRule) return (WifiRule) object;
        return new WifiRule();
    }

    @Override
    public boolean saveGpsRule(GPSRule gpsRule) {
        return savePrefs(gpsRule, GPS_RULE_KEY);
    }

    @Override
    public boolean saveWifiRule(WifiRule wifiRule) {
        return savePrefs(wifiRule, WIFI_RULE_KEY);
    }

    /**
     * @param key, under which object is stored in sharedPrefs
     * @return Object, stored in {@link #sharedPreferences} under {@param key},
     * or null, if no object found, or any exception occurred.
     */
    private Object readPref(String key){
        try {
            String serialized = sharedPreferences.getString(key, null);
            if (serialized==null) return null ;
            return objectSerializer.readFromString(serialized);
        } catch (IOException | ClassNotFoundException e) {
            Log.w(LOG_TAG, String.format("Can not load object. key: %s", key), e);
            return null;
        } catch (Exception e){
            Log.e(LOG_TAG, String.format("Can not load object. key: %s", key), e);
            return null;
        }
    }

    private boolean savePrefs(Serializable object, String key){
        try {
            String serialized = objectSerializer.writeToString(object);
            sharedPreferences
                    .edit()
                    .putString(key, serialized)
                    .apply();
            return true;
        } catch (IOException e) {
            Log.w(LOG_TAG, String.format("Can not save object. key: %s", key), e);
            return false;
        } catch (Exception e){
            Log.e(LOG_TAG, String.format("Can not save object. key: %s", key), e);
            return false;
        }
    }
}
