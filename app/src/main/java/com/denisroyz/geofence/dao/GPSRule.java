package com.denisroyz.geofence.dao;

import java.io.Serializable;

/**
 * Created by Heralt on 06.09.2017.
 */
public class GPSRule implements Serializable {

    private static final long serialVersionUID = 1L;

    private double lat;
    private double lon;
    private double radius;

    private boolean isInitialized = false;


    public double getLat() {
        return lat;
    }

    public void setLatLng(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        isInitialized = true;
    }

    public double getLon() {
        return lon;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GPSRule gpsRule = (GPSRule) o;

        if (Double.compare(gpsRule.lat, lat) != 0) return false;
        if (Double.compare(gpsRule.lon, lon) != 0) return false;
        if (Double.compare(gpsRule.radius, radius) != 0) return false;
        return isInitialized == gpsRule.isInitialized;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(lat);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lon);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isInitialized ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GPSRule{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", radius=" + radius +
                ", isInitialized=" + isInitialized +
                '}';
    }
}
