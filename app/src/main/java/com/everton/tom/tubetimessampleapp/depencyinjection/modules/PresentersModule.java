package com.everton.tom.tubetimessampleapp.depencyinjection.modules;

import com.everton.tom.tubetimessampleapp.nearbystoppoints.NearbyStopPointsContract;
import com.everton.tom.tubetimessampleapp.nearbystoppoints.NearbyStopPointsPresenter;
import com.everton.tom.tubetimessampleapp.util.FragmentScope;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Tom on 03/12/2017.
 */

@Module
public abstract class PresentersModule {

    @FragmentScope
    @Binds
    public abstract NearbyStopPointsContract.Presenter provideNeabyStopPointsPresenter(
            NearbyStopPointsPresenter nearbyStopPointsPresenter);
}
