package com.everton.tom.tubetimessampleapp.depencyinjection.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger Module used to pass the context dependency to the location data source.
 */

@Module
public class ApplicationModule {
    private final Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}
