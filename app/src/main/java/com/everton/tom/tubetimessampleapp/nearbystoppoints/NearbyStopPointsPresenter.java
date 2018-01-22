package com.everton.tom.tubetimessampleapp.nearbystoppoints;

import android.support.annotation.NonNull;

import com.everton.tom.tubetimessampleapp.data.Arrival;
import com.everton.tom.tubetimessampleapp.data.SimpleLocation;
import com.everton.tom.tubetimessampleapp.data.StopPointWithArrivals;
import com.everton.tom.tubetimessampleapp.data.TflStopPointsResponse;
import com.everton.tom.tubetimessampleapp.data.source.arrivals.ArrivalsRepository;
import com.everton.tom.tubetimessampleapp.data.source.location.LocationDataSource;
import com.everton.tom.tubetimessampleapp.data.source.location.LocationRepository;
import com.everton.tom.tubetimessampleapp.data.source.stoppoints.StopPointsRepository;
import com.everton.tom.tubetimessampleapp.util.FragmentScope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tom on 02/12/2017.
 */

@FragmentScope
public class NearbyStopPointsPresenter implements NearbyStopPointsContract.Presenter {

    private static final int SEARCH_RADIUS = 1000;

    private final NearbyStopPointsContract.View mView;

    private final LocationRepository mLocationRepository;

    private final StopPointsRepository mStopPointsRepository;

    private final ArrivalsRepository mArrivalsRepository;

    private List<StopPointWithArrivals> mStopPointWithArrivals;

    private SimpleLocation mCurrentLocation;

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public NearbyStopPointsPresenter(NearbyStopPointsContract.View view, LocationRepository locationRepository, StopPointsRepository stopPointsRepository, ArrivalsRepository arrivalsRepository) {
        mView = view;
        mLocationRepository = locationRepository;
        mStopPointsRepository = stopPointsRepository;
        mArrivalsRepository = arrivalsRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void subscribe() {
        loadCurrentLocation();
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void loadCurrentLocation() {
        mView.setLoadingIndicator(true);
        mLocationRepository.getLastKnownLocation(new LocationDataSource.GetLastKnownLocationCallback() {
            @Override
            public void onLocationLoaded(SimpleLocation simpleLocation) {
                // If outside of London default to St Paul's
                if (simpleLocation.getLatitude() > 51.705355d || simpleLocation.getLatitude() < 51.402265d
                        || simpleLocation.getLongitude() < -0.611093d || simpleLocation.getLongitude() > 0.250614d) {
                    simpleLocation = new SimpleLocation(51.514039d, -0.098382d);
                }
                mCurrentLocation = simpleLocation;
                loadNearbyStopPoints(simpleLocation);
            }

            @Override
            public void onPermissionNotGranted() {
                mView.showRequestLocationPermissionsUi();
                mView.setLoadingIndicator(false);
            }

            @Override
            public void onLocationObjectNull() {
                mView.showCannotGetLocationError();
                mView.setLoadingIndicator(false);
            }

            @Override
            public void onLocationSettingsUnsatisfied(boolean canBeFixed) {
                if (canBeFixed) {
                    mView.showChangeLocationSettingsUi();
                    mView.setLoadingIndicator(false);
                } else {
                    mView.showCannotGetLocationError();
                    mView.setLoadingIndicator(false);
                }
            }
        });
    }

    @Override
    public void loadNearbyStopPoints(@NonNull SimpleLocation currentLocation) {
        Disposable disposable = mStopPointsRepository.getStopPointsInRadius(SEARCH_RADIUS, currentLocation.getLatitude(), currentLocation.getLongitude())
                .flattenAsObservable(TflStopPointsResponse::getStopPoints)
                .flatMap(stopPoint -> mArrivalsRepository.getArrivalsAtStopPoint(stopPoint.getId()).toObservable(),
                        (stopPoint, arrivals) -> {
                            Collections.sort(arrivals, Arrival::compareTimeToStationTo);
                            ArrayList<Arrival> nextThreeArrivals = new ArrayList<>();

                            for (int i = 0; i < 3 && i < arrivals.size(); i++) {
                                nextThreeArrivals.add(arrivals.get(i));
                            }
                            return new StopPointWithArrivals(stopPoint, nextThreeArrivals);
                })
                .toList()
                .repeatWhen(objectFlowable -> objectFlowable.delay(30L, TimeUnit.SECONDS))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::processStopPointsWithArrivals,
                        throwable -> {
                    throwable.printStackTrace();
                    mView.showErrorGettingStopPoints();
                        });

        mCompositeDisposable.add(disposable);
    }

    void processStopPointsWithArrivals(List<StopPointWithArrivals> stopPointWithArrivals) {
        if (stopPointWithArrivals.size() > 0) {
            Collections.sort(stopPointWithArrivals, StopPointWithArrivals::compareDistanceTo);
            mView.setLoadingIndicator(false);
            mView.showStopPointsWithArrivals(stopPointWithArrivals);
        } else {
            mView.showNoStopPointsNearby();
            mView.setLoadingIndicator(false);
        }
    }

    @Override
    public void arrivalClicked(Arrival arrival) {
        mView.showLineLocationUi(arrival.getLineId(), mCurrentLocation, arrival.getStopPointId());
    }
}
