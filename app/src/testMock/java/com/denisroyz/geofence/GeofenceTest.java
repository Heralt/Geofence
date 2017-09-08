package com.denisroyz.geofence;

import com.denisroyz.geofence.model.GPSRule;
import com.denisroyz.geofence.model.UserLocation;
import com.denisroyz.geofence.model.WifiRule;
import com.denisroyz.geofence.repository.CurrentLocationRepository;
import com.denisroyz.geofence.repository.CurrentLocationRepositoryImpl;
import com.denisroyz.geofence.repository.GeofenceRuleRepository;
import com.denisroyz.geofence.repository.GeofenceRuleRepositoryImpl;
import com.denisroyz.geofence.service.GeofenceReceiver;
import com.denisroyz.geofence.service.GeofenceReceiverImpl;
import com.denisroyz.geofence.service.GeofenceReceiverListener;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GeofenceTest {

    private GeofenceReceiver geofenceReceiver;
    private CurrentLocationRepository currentLocationRepository;
    private GeofenceRuleRepository ruleRepository;


    @Before
    public void init(){
        currentLocationRepository = new CurrentLocationRepositoryImpl();
        ruleRepository = new GeofenceRuleRepositoryImpl();
        geofenceReceiver = new GeofenceReceiverImpl(currentLocationRepository, ruleRepository);
    }
    @Test
    public void wifi_network_name_is_right() throws Exception {
        boolean result = performTest(
                "WIFI",10d,10d,
                "WIFI",0d,0d,1d);
        Assert.assertTrue(result);
    }
    @Test
    public void wifi_network_name_is_wrong() throws Exception {
        boolean result =performTest(
                "WIFI",10d,10d,
                "",0d,0d,1d);
        Assert.assertFalse(result);
    }
    @Test
    public void gps_outside_circle() throws Exception {
        boolean result =performTest(
                "WIFI",50.489170,30.497834d,
                "",50.489387,30.487791,700d);
        Assert.assertFalse(result);
    }
    @Test
    public void gps_inside_circle() throws Exception {
        boolean result =performTest(
                "WIFI",50.489170,30.497834d,
                "",50.489387,30.487791,750d);
        Assert.assertTrue(result);
    }

    private boolean performTest( String wifi, Double lat, Double lon,String desiredWifi, Double desiredLat, Double desiredLon, Double radius){
        UserLocation userLocation = new UserLocation();
        userLocation.setLatitude(lat);
        userLocation.setLongitude(lon);
        userLocation.setWifiNetworkName(wifi);
        GPSRule gpsRule = new GPSRule();
        gpsRule.setLat(desiredLat);
        gpsRule.setLon(desiredLon);
        gpsRule.setRadius(radius);
        WifiRule wifiRule = new WifiRule();
        wifiRule.setWifiNetworkName(desiredWifi);
        geofenceReceiver.addGeofenceReceiverListener(new GeofenceReceiverListener() {
            @Override
            public void onGeofenceStatusUpdated(boolean inside) {

            }
        });
        ruleRepository.saveGpsRule(gpsRule);
        ruleRepository.saveWifiRule(wifiRule);
        currentLocationRepository.updateCurrentLocation(userLocation);
        return geofenceReceiver.getGeoFenceStatus();
    }
}