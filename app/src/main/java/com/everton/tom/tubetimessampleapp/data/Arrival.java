package com.everton.tom.tubetimessampleapp.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Immutable class for an arrival.
 */

public class Arrival implements Serializable {

    @NonNull
    @SerializedName("naptanId")
    private final String stopPointId;

    @NonNull
    private final String stationName;

    @NonNull
    private final String destinationName;

    @NonNull
    private final String platformName;

    private final int timeToStation;

    @NonNull
    private final String lineId;

    @NonNull
    private final String lineName;

    @NonNull
    private final String direction;

    @NonNull
    private final String towards;

    public Arrival(@NonNull String stopPointId, @NonNull String stationName, @NonNull String destinationName, @NonNull String platformName, int timeToStation,
                   @NonNull String lineId, @NonNull String lineName, @NonNull String direction, @NonNull String towards) {
        this.stopPointId = stopPointId;
        this.stationName = stationName;
        this.destinationName = destinationName;
        this.platformName = platformName;
        this.timeToStation = timeToStation;
        this.lineId = lineId;
        this.lineName = lineName;
        this.direction = direction;
        this.towards = towards;
    }

    @NonNull
    public String getDestinationName() {
        return destinationName;
    }

    @NonNull
    public String getStopPointId() {
        return stopPointId;
    }

    @NonNull
    public String getStationName() {
        return stationName;
    }

    @NonNull
    public String getPlatformName() {
        return platformName;
    }

    public int getTimeToStation() {
        return timeToStation;
    }

    @NonNull
    public String getLineId() {
        return lineId;
    }

    @NonNull
    public String getLineName() {
        return lineName;
    }

    @NonNull
    public String getDirection() {
        return direction;
    }

    @NonNull
    public String getTowards() {
        return towards;
    }

    public int compareTimeToStationTo(Arrival b) {
        return ((this.getTimeToStation() < b.getTimeToStation()) ? -1 : ((this.timeToStation < b.timeToStation) ? 1 : 0));
    }

}
