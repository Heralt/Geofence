package com.denisroyz.geofence.model;

import java.io.Serializable;

/**
 * Created by Heralt on 06.09.2017.
 */

public class GPSRule implements GeofenceRule, Serializable {

    private double lat;
    private double lon;
    private double radius;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
