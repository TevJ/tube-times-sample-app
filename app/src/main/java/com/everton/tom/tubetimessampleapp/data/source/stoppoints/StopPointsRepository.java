package com.everton.tom.tubetimessampleapp.data.source.stoppoints;

import android.support.annotation.NonNull;

import com.everton.tom.tubetimessampleapp.data.TflRouteSequenceResponse;
import com.everton.tom.tubetimessampleapp.data.TflStopPointsResponse;
import com.everton.tom.tubetimessampleapp.data.source.Remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by Tom on 30/11/2017.
 */

@Singleton
public class StopPointsRepository implements StopPointsDataSource {

    private final StopPointsDataSource mStopPointsRemoteDataSource;

    @Inject
    public StopPointsRepository(@Remote StopPointsDataSource stopPointsRemoteDataSource) {
        mStopPointsRemoteDataSource = stopPointsRemoteDataSource;
    }
    @Override
    public Single<TflStopPointsResponse> getStopPointsInRadius(int radiusInMetres, double latitude, double longitude) {
        return mStopPointsRemoteDataSource.getStopPointsInRadius(radiusInMetres, latitude, longitude);
    }

    @Override
    public Single<TflRouteSequenceResponse> getStopPointsOnLine(@NonNull String lineId, @NonNull String direction) {
        return mStopPointsRemoteDataSource.getStopPointsOnLine(lineId, direction);
    }
}
