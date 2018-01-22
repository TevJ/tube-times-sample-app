package com.everton.tom.tubetimessampleapp.data.source.location;

import android.support.annotation.NonNull;

import com.everton.tom.tubetimessampleapp.data.source.Local;

import javax.inject.Inject;

/**
 * Created by Tom on 02/12/2017.
 */

public class LocationRepository implements LocationDataSource {

    private LocationDataSource mLocalLocationDataSource;

    @Inject
    public LocationRepository(@Local LocationDataSource localLocationDataSource) {
        mLocalLocationDataSource = localLocationDataSource;
    }

    @Override
    public void getLastKnownLocation(@NonNull final GetLastKnownLocationCallback getLastKnownLocationCallback) {
        mLocalLocationDataSource.getLastKnownLocation(getLastKnownLocationCallback);
    }
}
