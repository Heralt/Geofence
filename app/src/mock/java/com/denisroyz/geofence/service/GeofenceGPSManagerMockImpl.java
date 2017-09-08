package com.denisroyz.geofence.service;

import android.location.Location;

/**
 * Created by Heralt on 09.09.2017.
 */

public class GeofenceGPSManagerMockImpl implements GeofenceGPSManager {

    private static final String MOCK_LOCATION_PROVIDER = "MOCK";
    private static final double MOCK_LAT = 50.440d;
    private static final double MOCK_LON = 30.545d;

    @Override
    public boolean subscribe() {
        return false;
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public Location getLastLocation() {
        Location loc = new Location(MOCK_LOCATION_PROVIDER);
        loc.setLatitude(MOCK_LAT);
        loc.setLongitude(MOCK_LON);
        return loc;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean havePermission() {
        return false;
    }
}
