package com.everton.tom.tubetimessampleapp.data.source.stoppoints.remote;

import android.support.annotation.NonNull;

import com.everton.tom.tubetimessampleapp.data.TflRouteSequenceResponse;
import com.everton.tom.tubetimessampleapp.data.TflStopPointsResponse;
import com.everton.tom.tubetimessampleapp.data.source.stoppoints.StopPointsDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by Tom on 29/11/2017.
 */

@Singleton
public class StopPointsRemoteDataSource implements StopPointsDataSource {

    private final StopPointsService mStopPointsService;

    @Inject
    public StopPointsRemoteDataSource(StopPointsService stopPointsService) {
        mStopPointsService = stopPointsService;
    }

    @Override
    public Single<TflStopPointsResponse> getStopPointsInRadius(int radiusInMetres, double latitude, double longitude) {
        return mStopPointsService.getStopPointsInRadius(latitude, longitude, radiusInMetres);
    }

    @Override
    public Single<TflRouteSequenceResponse> getStopPointsOnLine(@NonNull String lineId, @NonNull String direction) {
        return mStopPointsService.getStopPointsOnLine(lineId, direction);
    }

}
