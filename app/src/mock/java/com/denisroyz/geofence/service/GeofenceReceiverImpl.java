package com.denisroyz.geofence.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heralt on 06.09.2017.
 */

public class GeofenceReceiverImpl implements GeofenceReceiver{

    private boolean mInside = false;

    private List<GeofenceReceiverListener> listeners = new ArrayList<>();

    @Override
    public void addGeofenceReceiverListener(GeofenceReceiverListener geofenceReceiverListener) {
        listeners.add(geofenceReceiverListener);
    }

    @Override
    public void removeGeofenceReceiverListener(GeofenceReceiverListener geofenceReceiverListener) {
        listeners.remove(geofenceReceiverListener);
    }

    @Override
    public void notifyListeners() {
        for (GeofenceReceiverListener listener: listeners){
            listener.onGeofenceStatusUpdated(mInside);
        }
    }

    @Override
    public boolean getGeoFenceStatus() {
        return mInside;
    }
}
