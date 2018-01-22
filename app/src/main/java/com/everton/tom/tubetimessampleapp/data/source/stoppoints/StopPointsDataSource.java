package com.everton.tom.tubetimessampleapp.data.source.stoppoints;

import android.support.annotation.NonNull;

import com.everton.tom.tubetimessampleapp.data.TflRouteSequenceResponse;
import com.everton.tom.tubetimessampleapp.data.TflStopPointsResponse;

import io.reactivex.Single;

/**
 * Created by Tom on 29/11/2017.
 */

public interface StopPointsDataSource {

    Single<TflStopPointsResponse> getStopPointsInRadius(int radiusInMetres, double latitude, double longitude);

    Single<TflRouteSequenceResponse> getStopPointsOnLine(@NonNull String lineId, @NonNull String direction);

}
