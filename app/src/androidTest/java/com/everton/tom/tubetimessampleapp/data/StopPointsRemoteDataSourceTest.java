package com.everton.tom.tubetimessampleapp.data;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.everton.tom.tubetimessampleapp.depencyinjection.components.DaggerStopPointsRepositoryComponent;
import com.everton.tom.tubetimessampleapp.depencyinjection.components.StopPointsRepositoryComponent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * Created by Tom on 30/11/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class StopPointsRemoteDataSourceTest {

    private StopPointsRepositoryComponent mStopPointsRepository;

    @Before
    public void setUp() {
//        StopPointsRepositoryComponent =
        mStopPointsRepository = DaggerStopPointsRepositoryComponent.builder()
                .build();
    }

    @Test
    public void testGetStations() {
        TestObserver<List<StopPoint>> testObserver = new TestObserver<>();
        mStopPointsRepository.stopPointsRepository().getStopPointsInRadius(1000, 51.525193, -0.083799)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(TflStopPointsResponse::getStopPoints)
                .subscribe(
                        testObserver
                );
        testObserver.awaitTerminalEvent();
        testObserver.assertSubscribed();
        processStopPoints(testObserver.values().get(0));

    }

    private void processStopPoints(List<StopPoint> stopPoints) {
        assert(stopPoints.size() > 0);
    }
}
