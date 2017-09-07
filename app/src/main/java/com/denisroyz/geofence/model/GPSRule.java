package com.denisroyz.geofence.model;

import java.io.Serializable;

/**
 * Created by Heralt on 06.09.2017.
 */

public class GPSRule implements GeofenceRule, Serializable {

    private static final long serialVersionUID = 1L;

    private Double lat;
    private Double lon;
    private Double radius;

    public Double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GPSRule gpsRule = (GPSRule) o;

        if (Double.compare(gpsRule.lat, lat) != 0) return false;
        if (Double.compare(gpsRule.lon, lon) != 0) return false;
        return Double.compare(gpsRule.radius, radius) == 0;

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
        return result;
    }
}
