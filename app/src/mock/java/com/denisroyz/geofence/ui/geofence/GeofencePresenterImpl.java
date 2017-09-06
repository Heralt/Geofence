package com.denisroyz.geofence.ui.geofence;

import android.util.Log;

import com.denisroyz.geofence.GeofenceApplication;
import com.denisroyz.geofence.di.BeanTags;
import com.denisroyz.geofence.service.GeofenceManager;
import com.denisroyz.geofence.service.GeofenceReceiver;
import com.denisroyz.geofence.service.GeofenceReceiverListener;

/**
 * Created by Heralt on 05.09.2017.
 */

public class GeofencePresenterImpl implements GeofencePresenter, GeofenceReceiverListener {

    public static final String LOG_TAG = "GeofencePresenterImpl";

    private GeofenceManager geofenceManager;
    private GeofenceReceiver geofenceReceiver;

    private GeofenceView geofenceView;

    public GeofencePresenterImpl(GeofenceView geofenceView){
        this.geofenceView = geofenceView;
        initDependencies();

    }

    private void initDependencies(){
        geofenceManager = (GeofenceManager) GeofenceApplication.getApplicationDIContext().getBean(BeanTags.GEOFENCE_MANAGER);
        geofenceReceiver =  (GeofenceReceiver) GeofenceApplication.getApplicationDIContext().getBean(BeanTags.GEOFENCE_RECEIVER);
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
        fillGeofenceStatusView(geofenceReceiver.getGeoFenceStatus());
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
    public void onGeofenceStatusUpdated(boolean inside) {
        fillGeofenceStatusView(inside);
    }

    private void fillGeofenceStatusView(boolean isInsideGofenceArea){
        geofenceView.displayGeofenceStatus(isInsideGofenceArea);
    }
}
