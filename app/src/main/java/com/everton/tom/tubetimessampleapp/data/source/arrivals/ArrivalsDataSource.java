package com.everton.tom.tubetimessampleapp.data.source.arrivals;

import android.support.annotation.NonNull;

import com.everton.tom.tubetimessampleapp.data.Arrival;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Tom on 01/12/2017.
 */

public interface ArrivalsDataSource {

    Single<List<Arrival>> getArrivalsAtStopPoint(@NonNull String stopPointId);
}
