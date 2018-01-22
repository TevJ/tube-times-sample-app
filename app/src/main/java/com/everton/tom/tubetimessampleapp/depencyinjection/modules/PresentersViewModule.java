package com.everton.tom.tubetimessampleapp.depencyinjection.modules;

import android.support.v4.app.Fragment;

import com.everton.tom.tubetimessampleapp.nearbystoppoints.NearbyStopPointsContract;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module used to provide views to the Presenters.
 */

@Module
public class PresentersViewModule {
    private Fragment mView;

    public PresentersViewModule(Fragment view) {
        mView = view;
    }

    @Provides
    NearbyStopPointsContract.View provideNearbyStopPointsView() {
        return (NearbyStopPointsContract.View) mView;
    }
}
