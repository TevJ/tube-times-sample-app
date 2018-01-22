package com.everton.tom.tubetimessampleapp.depencyinjection.components;

import com.everton.tom.tubetimessampleapp.depencyinjection.modules.PresentersModule;
import com.everton.tom.tubetimessampleapp.depencyinjection.modules.PresentersViewModule;
import com.everton.tom.tubetimessampleapp.nearbystoppoints.NearbyStopPointsFragment;
import com.everton.tom.tubetimessampleapp.util.FragmentScope;

import dagger.Component;

/**
 * Created by Tom on 03/12/2017.
 */

@FragmentScope
@Component(dependencies = {RepositoryComponent.class},
        modules = {PresentersViewModule.class, PresentersModule.class})
public interface PresenterComponent {
    void inject(NearbyStopPointsFragment nearbyStopPointsFragment);
}
