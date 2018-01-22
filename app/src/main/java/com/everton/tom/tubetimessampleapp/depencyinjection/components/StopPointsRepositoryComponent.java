package com.everton.tom.tubetimessampleapp.depencyinjection.components;

import com.everton.tom.tubetimessampleapp.data.source.stoppoints.StopPointsRepository;
import com.everton.tom.tubetimessampleapp.depencyinjection.modules.ServiceModule;
import com.everton.tom.tubetimessampleapp.depencyinjection.modules.StopPointsRepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Tom on 30/11/2017.
 */

@Singleton
@Component(modules = {ServiceModule.class, StopPointsRepositoryModule.class})
public interface StopPointsRepositoryComponent {

    // Expose to child components
    StopPointsRepository stopPointsRepository();

}
