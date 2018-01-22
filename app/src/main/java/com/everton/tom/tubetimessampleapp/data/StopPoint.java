package com.everton.tom.tubetimessampleapp.data;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 * Immutable class for a stop point.
 */

public class StopPoint implements Serializable {

    @NonNull
    private final String id;

    @NonNull
    private final String commonName;

    private final double lat;

    private final double lon;

    private final double distance;

    private final List<AdditionalProperty> additionalProperties;

    public StopPoint(@NonNull String id, @NonNull String commonName, double lat, double lon, double distance, List<AdditionalProperty> additionalProperties) {
        this.id = id;
        this.commonName = commonName;
        this.lat = lat;
        this.lon = lon;
        this.distance = distance;
        this.additionalProperties = additionalProperties;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getCommonName() {
        return commonName;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public List<AdditionalProperty> getAdditionalProperties() {
        return additionalProperties;
    }

    public double getDistance() {
        return distance;
    }

    public int compareDistanceTo(StopPoint b) {
        return ((this.getDistance() < b.getDistance()) ? -1 : ((this.getDistance() < b.getDistance()) ? 1 : 0));
    }
}
