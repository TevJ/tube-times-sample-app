package com.everton.tom.tubetimessampleapp;

import android.support.v4.app.Fragment;

import com.everton.tom.tubetimessampleapp.depencyinjection.components.DaggerPresenterComponent;
import com.everton.tom.tubetimessampleapp.depencyinjection.components.PresenterComponent;
import com.everton.tom.tubetimessampleapp.depencyinjection.modules.PresentersViewModule;

/**
 * Created by Tom on 03/12/2017.
 */

public class BaseFragment extends Fragment {

    protected PresenterComponent getPresenterComponent() {
        return DaggerPresenterComponent.builder()
                .repositoryComponent(((TubeTimeSampleApp) getActivity().getApplication()).getRepositoryComponent())
                .presentersViewModule(new PresentersViewModule(this))
                .build();
    }
}
