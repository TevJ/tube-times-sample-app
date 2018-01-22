package com.everton.tom.tubetimessampleapp.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle station list response
 */

public class TflStopPointsResponse {

    private List<StopPoint> stopPoints;

    public TflStopPointsResponse() {
        stopPoints = new ArrayList<>();
    }

    public static TflStopPointsResponse parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(response, TflStopPointsResponse.class);
    }

    public List<StopPoint> getStopPoints() {
        return stopPoints;
    }
}
