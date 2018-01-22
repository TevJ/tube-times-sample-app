package com.everton.tom.tubetimessampleapp.data;

import java.io.Serializable;

/**
 * Simple Location class
 */

public class SimpleLocation implements Serializable {

    private final double mLatitude;

    private final double mLongitude;

    public SimpleLocation(double latitude, double longitude) {
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }
}
