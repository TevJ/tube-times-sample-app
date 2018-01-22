package com.everton.tom.tubetimessampleapp.nearbystoppoints;

import com.everton.tom.tubetimessampleapp.data.AdditionalProperty;
import com.everton.tom.tubetimessampleapp.data.Arrival;
import com.everton.tom.tubetimessampleapp.data.SimpleLocation;
import com.everton.tom.tubetimessampleapp.data.StopPoint;
import com.everton.tom.tubetimessampleapp.data.StopPointWithArrivals;
import com.everton.tom.tubetimessampleapp.data.source.arrivals.ArrivalsRepository;
import com.everton.tom.tubetimessampleapp.data.source.location.LocationDataSource;
import com.everton.tom.tubetimessampleapp.data.source.location.LocationRepository;
import com.everton.tom.tubetimessampleapp.data.source.stoppoints.StopPointsRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Unit test for {@link NearbyStopPointsPresenter} implementation.
 */

public class NearbyStopPointsPresenterTest {

    @Mock
    private NearbyStopPointsContract.View mView;

    @Mock
    private ArrivalsRepository mArrivalsRepository;

    @Mock
    private StopPointsRepository mStopPointsRepository;

    @Mock
    private LocationRepository mLocationRepository;

    @Captor
    private ArgumentCaptor<LocationDataSource.GetLastKnownLocationCallback> mGetLastKnownLocationCallbackArgumentCaptor;

    // Reference to the class under test
    private NearbyStopPointsPresenter mPresenter;

    @Before
    public void setUpMocksAndPresenter() {
        MockitoAnnotations.initMocks(this);

        mPresenter = new NearbyStopPointsPresenter(mView, mLocationRepository,
                mStopPointsRepository, mArrivalsRepository);
    }

    @Test
    public void start_loadCurrentLocation() {
        mPresenter.subscribe();

        verify(mLocationRepository).getLastKnownLocation(any(LocationDataSource.GetLastKnownLocationCallback.class));
    }

    @Test
    public void loadCurrentLocation_success_loadNearbyStopPoints() {
        SimpleLocation testLocation = new SimpleLocation(51.253, -0.0849);

        mPresenter.loadCurrentLocation();

        verify(mLocationRepository).getLastKnownLocation(mGetLastKnownLocationCallbackArgumentCaptor.capture());
//        mGetLastKnownLocationCallbackArgumentCaptor.getValue().onLocationLoaded(testLocation);

//        verify(mStopPointsRepository).getStopPointsInRadius(eq(1000), eq(testLocation.getLatitude()), eq(testLocation.getLongitude()));
    }

    @Test
    public void loadCurrentLocation_noPermission_showRequestPermissionsUi() {
        mPresenter.loadCurrentLocation();

        verify(mLocationRepository).getLastKnownLocation(mGetLastKnownLocationCallbackArgumentCaptor.capture());
        mGetLastKnownLocationCallbackArgumentCaptor.getValue().onPermissionNotGranted();

        verify(mView).showRequestLocationPermissionsUi();
    }

    @Test
    public void loadCurrentLocation_settingsUnsatisfied_canBeFixed_showChangeSettingsUi() {
        mPresenter.loadCurrentLocation();

        verify(mLocationRepository).getLastKnownLocation(mGetLastKnownLocationCallbackArgumentCaptor.capture());
        mGetLastKnownLocationCallbackArgumentCaptor.getValue().onLocationSettingsUnsatisfied(true);

        verify(mView).showChangeLocationSettingsUi();
    }

    @Test
    public void loadCurrentLocation_nullValue_showError() {
        mPresenter.loadCurrentLocation();

        verify(mLocationRepository).getLastKnownLocation(mGetLastKnownLocationCallbackArgumentCaptor.capture());
        mGetLastKnownLocationCallbackArgumentCaptor.getValue().onLocationObjectNull();

        verify(mView).showCannotGetLocationError();
    }

    @Test
    public void loadStopPoints_success_showInView() {

        StopPoint testStopPoint = new StopPoint(
                "1", "testStop", 1.000, 2.000, 3.000,
                new ArrayList<AdditionalProperty>());

        Arrival testArrival = new Arrival(testStopPoint.getId(), testStopPoint.getCommonName(), "test_dest",
                "test_plat", 200, "test_line", "test_line", "outbound", "test_towards");

        List<Arrival> testArrivals = new ArrayList<>();
        testArrivals.add(testArrival);

        List<StopPointWithArrivals> testStopPointsList = new ArrayList<>();
        testStopPointsList.add(new StopPointWithArrivals(testStopPoint, testArrivals));

        SimpleLocation testLocation = new SimpleLocation(51.253, -0.0849);

//        mPresenter.loadNearbyStopPoints(testLocation);

//        verify(mStopPointsRepository).getStopPointsInRadius(eq(1000), eq(testLocation.getLatitude()), eq(testLocation.getLongitude()));

        mPresenter.processStopPointsWithArrivals(testStopPointsList);
        verify(mView).showStopPointsWithArrivals(eq(testStopPointsList));
    }
}
