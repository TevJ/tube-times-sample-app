package com.everton.tom.tubetimessampleapp.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Tom on 04/12/2017.
 */

public class TflRouteSequenceResponse {

    @NonNull
    private final List<StopPoint> stations;

    @NonNull
    private final List<Line> orderedLineRoutes;

    public TflRouteSequenceResponse(@NonNull List<StopPoint> stations, @NonNull List<Line> orderedLineRoutes) {
        this.stations = stations;
        this.orderedLineRoutes = orderedLineRoutes;
    }

    @NonNull
    public List<StopPoint> getStations() {
        return stations;
    }

    @NonNull
    public List<Line> getOrderedLineRoutes() {
        return orderedLineRoutes;
    }
}
