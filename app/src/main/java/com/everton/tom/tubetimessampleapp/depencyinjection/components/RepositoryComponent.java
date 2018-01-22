package com.everton.tom.tubetimessampleapp.depencyinjection.components;

import com.everton.tom.tubetimessampleapp.data.source.arrivals.ArrivalsRepository;
import com.everton.tom.tubetimessampleapp.data.source.location.LocationRepository;
import com.everton.tom.tubetimessampleapp.data.source.stoppoints.StopPointsRepository;
import com.everton.tom.tubetimessampleapp.depencyinjection.modules.ApplicationModule;
import com.everton.tom.tubetimessampleapp.depencyinjection.modules.ArrivalsRepositoryModule;
import com.everton.tom.tubetimessampleapp.depencyinjection.modules.LocationRepositoryModule;
import com.everton.tom.tubetimessampleapp.depencyinjection.modules.ServiceModule;
import com.everton.tom.tubetimessampleapp.depencyinjection.modules.StopPointsRepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Tom on 03/12/2017.
 */
@Singleton
@Component(modules = {ServiceModule.class, ApplicationModule.class, ArrivalsRepositoryModule.class,
        StopPointsRepositoryModule.class, LocationRepositoryModule.class})
public interface RepositoryComponent {
    StopPointsRepository stopPointsRepository();
    ArrivalsRepository arrivalsRepository();
    LocationRepository locationRepository();
}
