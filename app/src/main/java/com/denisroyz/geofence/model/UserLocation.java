package com.denisroyz.geofence.model;

/**
 * Created by Heralt on 07.09.2017.
 */

public class UserLocation {
    private Double latitude;
    private Double longitude;
    private String wifiNetworkName;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getWifiNetworkName() {
        return wifiNetworkName;
    }

    public void setWifiNetworkName(String wifiNetworkName) {
        this.wifiNetworkName = wifiNetworkName;
    }

    @Override
    public String toString() {
        return "UserLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", wifiNetworkName='" + wifiNetworkName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLocation that = (UserLocation) o;

        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null)
            return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null)
            return false;
        return wifiNetworkName != null ? wifiNetworkName.equals(that.wifiNetworkName) : that.wifiNetworkName == null;

    }

    @Override
    public int hashCode() {
        int result = latitude != null ? latitude.hashCode() : 0;
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (wifiNetworkName != null ? wifiNetworkName.hashCode() : 0);
        return result;
    }
}
