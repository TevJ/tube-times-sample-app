package com.everton.tom.tubetimessampleapp.data.source.location;

import android.support.annotation.NonNull;

import com.everton.tom.tubetimessampleapp.data.SimpleLocation;

/**
 * Created by Tom on 02/12/2017.
 */

public interface LocationDataSource {

    interface GetLastKnownLocationCallback {
        void onLocationLoaded(SimpleLocation simpleLocation);

        void onPermissionNotGranted();

        void onLocationObjectNull();

        void onLocationSettingsUnsatisfied(boolean canBeFixed);
    }

    void getLastKnownLocation(@NonNull GetLastKnownLocationCallback getLastKnownLocationCallback);
}
