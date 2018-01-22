package com.everton.tom.tubetimessampleapp;



import android.support.multidex.MultiDexApplication;

import com.everton.tom.tubetimessampleapp.depencyinjection.components.DaggerRepositoryComponent;
import com.everton.tom.tubetimessampleapp.depencyinjection.components.RepositoryComponent;
import com.everton.tom.tubetimessampleapp.depencyinjection.modules.ApplicationModule;
import com.everton.tom.tubetimessampleapp.depencyinjection.modules.ServiceModule;

/**
 * Created by Tom on 02/12/2017.
 */

public class TubeTimeSampleApp extends MultiDexApplication {

    private RepositoryComponent mRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mRepositoryComponent = DaggerRepositoryComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .serviceModule(new ServiceModule())
                .build();
    }

    public RepositoryComponent getRepositoryComponent() {
        return mRepositoryComponent;
    }
}
