package com.everton.tom.tubetimessampleapp.nearbystoppoints;

import android.support.annotation.NonNull;

import com.everton.tom.tubetimessampleapp.BasePresenter;
import com.everton.tom.tubetimessampleapp.BaseView;
import com.everton.tom.tubetimessampleapp.data.Arrival;
import com.everton.tom.tubetimessampleapp.data.SimpleLocation;
import com.everton.tom.tubetimessampleapp.data.StopPointWithArrivals;

import java.util.List;

/**
 * Contract for the nearby stop points screen.
 */

public interface NearbyStopPointsContract {

    interface View extends BaseView {
        void showStopPointsWithArrivals(@NonNull List<StopPointWithArrivals> stopPointWithArrivals);

        void showLineLocationUi(@NonNull String lineId, @NonNull SimpleLocation location, @NonNull String selectedStopPointId);

        void setLoadingIndicator(boolean active);

        void showNoStopPointsNearby();

        void showErrorGettingStopPoints();

        void showRequestLocationPermissionsUi();

        void showChangeLocationSettingsUi();

        void showCannotGetLocationError();
    }

    interface Presenter extends BasePresenter {
        void loadCurrentLocation();

        void loadNearbyStopPoints(@NonNull SimpleLocation currentLocation);

        void arrivalClicked(Arrival arrival);

    }

}
