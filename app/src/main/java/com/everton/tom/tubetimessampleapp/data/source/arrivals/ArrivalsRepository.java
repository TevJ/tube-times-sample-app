package com.everton.tom.tubetimessampleapp.data.source.arrivals;

import android.support.annotation.NonNull;

import com.everton.tom.tubetimessampleapp.data.Arrival;
import com.everton.tom.tubetimessampleapp.data.source.Remote;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Repository for accessing arrivals information.
 */

@Singleton
public class ArrivalsRepository implements ArrivalsDataSource {

    private final ArrivalsDataSource mArrivalsRemoteDataSource;

    @Inject
    public ArrivalsRepository(@Remote ArrivalsDataSource arrivalsRemoteDataSource) {
        mArrivalsRemoteDataSource = arrivalsRemoteDataSource;
    }

    @Override
    public Single<List<Arrival>> getArrivalsAtStopPoint(@NonNull String stopPointId) {
        return mArrivalsRemoteDataSource.getArrivalsAtStopPoint(stopPointId);
    }
}
