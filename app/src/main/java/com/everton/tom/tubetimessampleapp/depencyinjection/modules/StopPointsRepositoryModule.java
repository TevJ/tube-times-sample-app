package com.everton.tom.tubetimessampleapp.depencyinjection.modules;

import com.everton.tom.tubetimessampleapp.data.source.Remote;
import com.everton.tom.tubetimessampleapp.data.source.stoppoints.StopPointsDataSource;
import com.everton.tom.tubetimessampleapp.data.source.stoppoints.remote.StopPointsRemoteDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Tom on 30/11/2017.
 */

@Module
public abstract class StopPointsRepositoryModule {
    @Singleton
    @Binds
    @Remote
    abstract StopPointsDataSource provideStopPointsRemoteDataSource(StopPointsRemoteDataSource stopPointsDataSource);
}
