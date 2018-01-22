package com.everton.tom.tubetimessampleapp.data.source.stoppoints.remote;

import com.everton.tom.tubetimessampleapp.data.TflRouteSequenceResponse;
import com.everton.tom.tubetimessampleapp.data.TflStopPointsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Tom on 30/11/2017.
 */

public interface StopPointsService {

    @GET("StopPoint?stopTypes=NaptanMetroStation")
    Single<TflStopPointsResponse> getStopPointsInRadius(@Query("lat") double latitude, @Query("lon") double longitude,
                                                        @Query("radius") int radius);

    @GET("Line/{lineId}/Route/Sequence/{direction}?serviceTypers=Regular,Night")
    Single<TflRouteSequenceResponse> getStopPointsOnLine(@Path("lineId") String lineId, @Path("direction") String direction);
}
