package com.everton.tom.tubetimessampleapp.data.source.location.local;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.everton.tom.tubetimessampleapp.data.SimpleLocation;
import com.everton.tom.tubetimessampleapp.data.source.location.LocationDataSource;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import javax.inject.Inject;

/**
 * Created by Tom on 02/12/2017.
 */

public class LocationLocalDataSource implements LocationDataSource {

    private Context mContext;

    private FusedLocationProviderClient mFusedLocationClient;

    @Inject
    public LocationLocalDataSource(Context context) {
        mContext = context;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    @Override
    public void getLastKnownLocation(@NonNull final GetLastKnownLocationCallback getLastKnownLocationCallback) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            getLastKnownLocationCallback.onPermissionNotGranted();
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        getLastKnownLocationCallback.onLocationLoaded(
                                new SimpleLocation(location.getLatitude(), location.getLongitude())
                        );
                    } else {
                        getLastKnownLocationCallback.onLocationObjectNull();
                    }
                }).addOnFailureListener(e -> {
                    int statusCode = ((ApiException) e).getStatusCode();
                    switch (statusCode) {
                        case CommonStatusCodes.RESOLUTION_REQUIRED:
                            getLastKnownLocationCallback.onLocationSettingsUnsatisfied(true);
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            getLastKnownLocationCallback.onLocationSettingsUnsatisfied(false);
                            break;
                    }
                });
    }
}
