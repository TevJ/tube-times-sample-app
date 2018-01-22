package com.everton.tom.tubetimessampleapp.depencyinjection.modules;

import com.everton.tom.tubetimessampleapp.data.source.Local;
import com.everton.tom.tubetimessampleapp.data.source.location.LocationDataSource;
import com.everton.tom.tubetimessampleapp.data.source.location.local.LocationLocalDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Dagger module that provides the data source to the location stopPointsRepository.
 */

@Module
public abstract class LocationRepositoryModule {
    @Singleton
    @Binds
    @Local
    abstract LocationDataSource provideLocationLocalDataSource(LocationLocalDataSource locationLocalDataSource);
}
