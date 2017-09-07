package com.denisroyz.geofence;

import com.denisroyz.geofence.model.GPSRule;
import com.denisroyz.geofence.model.WifiRule;
import com.denisroyz.geofence.service.ObjectSerializer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectSerializerTest {

    private ObjectSerializer objectSerializer;

    @Before
    public void init(){
        objectSerializer = new ObjectSerializer();
    }
    @Test
    public void gps_rule_serialize_deserialize_is_correct() throws Exception {
        GPSRule gpsRule = new GPSRule();
        String string =  objectSerializer.writeToString(gpsRule);
        Object object = objectSerializer.readFromString(string);
        assertEquals(gpsRule, object);
    }

    @Test
    public void wifi_rule_serialize_deserialize_is_correct() throws Exception {
        WifiRule wifiRule = new WifiRule();
        String string =  objectSerializer.writeToString(wifiRule);
        Object object = objectSerializer.readFromString(string);
        assertEquals(wifiRule, object);
    }
    @Test
    public void gps_rule_with_values_serialize_deserialize_is_correct() throws Exception {
        GPSRule gpsRule = new GPSRule();
        gpsRule.setLon(13);
        gpsRule.setLat(14.3);
        gpsRule.setRadius(0.01);
        String string =  objectSerializer.writeToString(gpsRule);
        Object object = objectSerializer.readFromString(string);
        assertEquals(gpsRule, object);
    }

    @Test
    public void wifi_rule_with_values_serialize_deserialize_is_correct() throws Exception {
        WifiRule wifiRule = new WifiRule();
        wifiRule.setWifiNetworkName("Test");
        String string =  objectSerializer.writeToString(wifiRule);
        Object object = objectSerializer.readFromString(string);
        assertEquals(wifiRule, object);
    }
}