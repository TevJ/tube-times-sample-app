package com.everton.tom.tubetimessampleapp.data.source.arrivals.remote;

import android.support.annotation.NonNull;

import com.everton.tom.tubetimessampleapp.data.Arrival;
import com.everton.tom.tubetimessampleapp.data.source.arrivals.ArrivalsDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Tom on 01/12/2017.
 */

public class ArrivalsRemoteDataSource implements ArrivalsDataSource {

    private static final int REQUEST_INTERVAL_TIME = 30;

    private final ArrivalsService mArrivalsService;

    @Inject
    public ArrivalsRemoteDataSource(ArrivalsService arrivalsService) {
        mArrivalsService = arrivalsService;
    }

    @Override
    public Single<List<Arrival>> getArrivalsAtStopPoint(@NonNull String stopPointId) {
        return mArrivalsService.getArrivalsAtStopPoint(stopPointId);
//        return Observable.interval(REQUEST_INTERVAL_TIME, TimeUnit.SECONDS).startWith(1L)
//                .flatMap(tick ->
//                        mArrivalsService.getArrivalsAtStopPoint(stopPointId));
    }
}
