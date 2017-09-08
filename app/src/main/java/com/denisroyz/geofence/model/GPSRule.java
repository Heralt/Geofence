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

        if (lat != null ? !lat.equals(gpsRule.lat) : gpsRule.lat != null) return false;
        if (lon != null ? !lon.equals(gpsRule.lon) : gpsRule.lon != null) return false;
        return radius != null ? radius.equals(gpsRule.radius) : gpsRule.radius == null;

    }

    @Override
    public int hashCode() {
        int result = lat != null ? lat.hashCode() : 0;
        result = 31 * result + (lon != null ? lon.hashCode() : 0);
        result = 31 * result + (radius != null ? radius.hashCode() : 0);
        return result;
    }
}
