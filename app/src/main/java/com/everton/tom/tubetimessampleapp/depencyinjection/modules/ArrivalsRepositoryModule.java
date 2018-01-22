package com.everton.tom.tubetimessampleapp.depencyinjection.modules;

import com.everton.tom.tubetimessampleapp.data.source.Remote;
import com.everton.tom.tubetimessampleapp.data.source.arrivals.ArrivalsDataSource;
import com.everton.tom.tubetimessampleapp.data.source.arrivals.remote.ArrivalsRemoteDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Module that provides data sources for the Arrivals Repository.
 */

@Module
public abstract class ArrivalsRepositoryModule {
    @Singleton
    @Binds
    @Remote
    abstract ArrivalsDataSource provideArrivalsRemoteDataSource(ArrivalsRemoteDataSource arrivalsRemoteDataSource);
}
