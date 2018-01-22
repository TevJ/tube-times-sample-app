package com.everton.tom.tubetimessampleapp.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Tom on 03/12/2017.
 */

public class StopPointWithArrivals {

    @NonNull
    private final StopPoint mStopPoint;

    @NonNull
    private final List<Arrival> mArrivals;

    public StopPointWithArrivals(@NonNull StopPoint stopPoint, @NonNull List<Arrival> arrivals) {
        mStopPoint = stopPoint;
        mArrivals = arrivals;
    }

    @NonNull
    public StopPoint getStopPoint() {
        return mStopPoint;
    }

    @NonNull
    public List<Arrival> getArrivals() {
        return mArrivals;
    }

    public int compareDistanceTo(StopPointWithArrivals b) {
        return ((this.getStopPoint().getDistance() < b.getStopPoint().getDistance()) ? -1 : ((this.getStopPoint().getDistance() < b.getStopPoint().getDistance()) ? 1 : 0));
    }
}
