package com.denisroyz.geofence.service;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.List;

/**
 * Created by Heralt on 07.09.2017.
 */

public class GeofenceGPSManagerImpl implements GeofenceGPSManager, LocationListener {

    private static final String LOG_TAG = "GeofenceGPSManagerImpl";

    private static final int MIN_TIME = 0;
    private static final int MIN_DISTANCE = 0;
    LocationManager locationManager;
    Context context;
    Location mLastLocation;


    public GeofenceGPSManagerImpl(Context context) {
        this.context = context;
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public boolean subscribe(){
        if (!havePermission()){
            Log.e(LOG_TAG,"GPS permission is not provided. Request permission is not implemented");
            return false;
        } else {
            try {
                List<String> allLocationManagerProviders = locationManager.getAllProviders();
                boolean haveAtLeastOne = false;
                if (allLocationManagerProviders.contains(LocationManager.NETWORK_PROVIDER)){
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this);
                    Location lastKnown = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (mLastLocation==null) mLastLocation = lastKnown;
                    Log.i(LOG_TAG,"LocationManager.NETWORK_PROVIDER enabled");
                    haveAtLeastOne = true;
                } else {
                    Log.w(LOG_TAG,"LocationManager.NETWORK_PROVIDER is not presented in device");
                }
                if (allLocationManagerProviders.contains(LocationManager.GPS_PROVIDER)){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DISTANCE, this);
                    Location lastKnown = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (mLastLocation==null) mLastLocation = lastKnown;
                    Log.i(LOG_TAG, "LocationManager.GPS_PROVIDER enabled");
                    haveAtLeastOne = true;
                } else {
                    Log.w(LOG_TAG,"LocationManager.GPS_PROVIDER is not presented in device");
                }
                return haveAtLeastOne;
            } catch (SecurityException ex){
                Log.e(LOG_TAG, "Lost location permission during execution",ex);
                return false;
            }
        }
    }

    @Override
    public void unSubscribe(){
        locationManager.removeUpdates(this);
    }

    @Override
    public Location getLastLocation() {
        return mLastLocation;
    }

    @Override
    public boolean isEnabled() {
        return  ( locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) )||( locationManager.isProviderEnabled( LocationManager.NETWORK_PROVIDER ) );
    }

    @Override
    public boolean havePermission() {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public void onLocationChanged(Location location) {
        this.mLastLocation = location;
    }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


}
