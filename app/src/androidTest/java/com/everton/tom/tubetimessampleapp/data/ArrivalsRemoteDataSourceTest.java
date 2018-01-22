package com.everton.tom.tubetimessampleapp.data;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.everton.tom.tubetimessampleapp.data.source.arrivals.ArrivalsRepository;
import com.everton.tom.tubetimessampleapp.depencyinjection.components.DaggerArrivalsRepositoryComponent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tom on 01/12/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ArrivalsRemoteDataSourceTest {

    private ArrivalsRepository mArrivalsRepository;

    @Before
    public void setUp() {
        mArrivalsRepository = DaggerArrivalsRepositoryComponent.builder()
                .build().repository();
    }

    @Test
    public void testGetArrivals() {
        TestObserver<List<Arrival>> testObserver = new TestObserver<>();
        mArrivalsRepository.getArrivalsAtStopPoint("940GZZLUODS")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(testObserver);

        testObserver.awaitTerminalEvent();
        testObserver.values();
    }
}
