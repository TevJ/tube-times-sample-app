package com.everton.tom.tubetimessampleapp.data.source.arrivals.remote;

import com.everton.tom.tubetimessampleapp.data.Arrival;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Tom on 01/12/2017.
 */

public interface ArrivalsService {

    @GET("StopPoint/{stopPointId}/Arrivals?mode=tube")
    Single<List<Arrival>> getArrivalsAtStopPoint(@Path("stopPointId") String stopPointId);
}
