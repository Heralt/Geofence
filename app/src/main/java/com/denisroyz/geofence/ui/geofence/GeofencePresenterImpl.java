package com.denisroyz.geofence.ui.geofence;

import android.util.Log;

import com.denisroyz.geofence.GeofenceApplication;
import com.denisroyz.geofence.di.BeanTags;
import com.denisroyz.geofence.dao.GPSRule;
import com.denisroyz.geofence.dao.WifiRule;
import com.denisroyz.geofence.repository.GeofenceRuleRepository;
import com.denisroyz.geofence.service.GeofenceManager;
import com.denisroyz.geofence.service.GeofenceReceiver;
import com.denisroyz.geofence.service.GeofenceReceiverListener;

/**
 * Created by Heralt on 05.09.2017.
 */

public class GeofencePresenterImpl implements GeofencePresenter, GeofenceReceiverListener {

    public static final String LOG_TAG = "GeofencePresenterImpl";

    private GeofenceActivityAPI geofenceActivityAPI;
    private GeofenceManager geofenceManager;
    private GeofenceReceiver geofenceReceiver;
    private GeofenceRuleRepository rulesRepository;
    private GeofenceView geofenceView;

    public GeofencePresenterImpl(GeofenceView geofenceView, GeofenceActivityAPI geofenceActivityAPI){
        this.geofenceView = geofenceView;
        this.geofenceActivityAPI = geofenceActivityAPI;
        initDependencies();
    }

    private void initDependencies(){
        geofenceManager  = (GeofenceManager)  GeofenceApplication.getApplicationDIContext().getBean(BeanTags.GEOFENCE_MANAGER);
        geofenceReceiver = (GeofenceReceiver) GeofenceApplication.getApplicationDIContext().getBean(BeanTags.GEOFENCE_RECEIVER);
        rulesRepository  = (GeofenceRuleRepository)  GeofenceApplication.getApplicationDIContext().getBean(BeanTags.RULES_REPOSITORY);
    }

    @Override
    public void enableSearch(boolean enable) {
        Log.d(LOG_TAG, String.format("enableSearch(%b)", enable));
        if (enable) {
            geofenceManager.startService();
        } else {
            geofenceManager.stopService();
        }
    }

    @Override
    public void fillView() {
        boolean isPermissionGranted = geofenceActivityAPI.checkPermissions();
        boolean isGeofenceSearchEnabled = geofenceManager.isEnabled();
        GPSRule gpsRule = rulesRepository.getGpsRule();
        WifiRule wifiRule = rulesRepository.getWifiRule();
        fillGeofenceStatusView(geofenceReceiver.getGeoFenceStatus(), isGeofenceSearchEnabled, isPermissionGranted);
        fillRuleConfigurationView(gpsRule, wifiRule);
    }

    @Override
    public void subscribe() {
        geofenceReceiver.addGeofenceReceiverListener(this);
    }

    @Override
    public void unSubscribe() {
        geofenceReceiver.removeGeofenceReceiverListener(this);
    }

    @Override
    public void save(GPSRule gpsRule) {
        boolean success = rulesRepository.saveGpsRule(gpsRule);
        Log.i(LOG_TAG, String.format("Save GPSRule Success: %b", success));
    }

    @Override
    public void save(WifiRule wifiRule) {
        boolean success = rulesRepository.saveWifiRule(wifiRule);
        Log.i(LOG_TAG, String.format("Save WiFiRule Success: %b", success));
    }

    @Override
    public void savePermissionState(boolean isPermissionGranted) {
        geofenceView.displayPermissionRequestView(!isPermissionGranted);
    }

    @Override
    public void onGeofenceStatusUpdated(boolean inside) {
        fillGeofenceStatusView(inside);
    }

    private void fillGeofenceStatusView(boolean isInsideGeofenceArea){
        geofenceView.displayGeofenceStatus(isInsideGeofenceArea);
    }
    private void fillGeofenceStatusView(boolean isInsideGeofenceArea, boolean isSearchEnabled, boolean isPermissionGranted){
        fillGeofenceStatusView(isInsideGeofenceArea);
        geofenceView.displayGeoFenceEnabled(isSearchEnabled);
        geofenceView.displayPermissionRequestView(!isPermissionGranted);
    }
    private void fillRuleConfigurationView(GPSRule gpsRule, WifiRule wifiRule){
        geofenceView.displayRulesPicker(gpsRule, wifiRule);
    }
}
