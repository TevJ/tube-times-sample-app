package com.everton.tom.tubetimessampleapp.nearbystoppoints;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.everton.tom.tubetimessampleapp.BaseFragment;
import com.everton.tom.tubetimessampleapp.R;
import com.everton.tom.tubetimessampleapp.data.Arrival;
import com.everton.tom.tubetimessampleapp.data.SimpleLocation;
import com.everton.tom.tubetimessampleapp.data.StopPointWithArrivals;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Tom on 02/12/2017.
 */

public class NearbyStopPointsFragment extends BaseFragment implements NearbyStopPointsContract.View {

    @Inject public NearbyStopPointsContract.Presenter mPresenter;

    /**
     * Listener for arrival clicks.
     */
    private ArrivalClickListener mArrivalClickListener = new ArrivalClickListener() {
        @Override
        public void onArrivalClicked(Arrival arrivalClicked) {
            mPresenter.arrivalClicked(arrivalClicked);
        }
    };

    private StopPointsAdapter mStopPointsAdapter;

    public NearbyStopPointsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nearby_stop_points, container, false);

        mStopPointsAdapter = new StopPointsAdapter(getActivity(), mArrivalClickListener);
        RecyclerView stopPointsRecyclerView = rootView.findViewById(R.id.recycler_nearby_stop_points);
        stopPointsRecyclerView.setAdapter(mStopPointsAdapter);
        stopPointsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemDecoration horizontalDivider = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        stopPointsRecyclerView.addItemDecoration(horizontalDivider);

        getPresenterComponent().inject(this);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        mPresenter.unsubscribe();
        super.onPause();
    }

    @Override
    public void showStopPointsWithArrivals(@NonNull List<StopPointWithArrivals> stopPointWithArrivals) {
        mStopPointsAdapter.updateStopPointsWithArrivals(stopPointWithArrivals);
    }

    @Override
    public void showLineLocationUi(@NonNull String lineId, @NonNull SimpleLocation location, @NonNull String selectedStopPointId) {
        // Not yet implemented
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (getView() == null) return;

        ProgressBar progressBar = getView().findViewById(R.id.progress_nearby_stop_points);
        if (active) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    // TODO: Implement as snack bar messages
    @Override
    public void showNoStopPointsNearby() {
        Log.v(this.getClass().getSimpleName(), "No stop points nearby");
    }

    @Override
    public void showErrorGettingStopPoints() {
        Log.e(this.getClass().getSimpleName(), "Error getting stop points");
    }

    @Override
    public void showRequestLocationPermissionsUi() {
        Log.v(this.getClass().getSimpleName(), "showRequestLocationPermissionsUi");
    }

    @Override
    public void showChangeLocationSettingsUi() {
        Log.v(this.getClass().getSimpleName(), "showChangeLocationSettingsUi");
    }

    @Override
    public void showCannotGetLocationError() {
        Log.e(this.getClass().getSimpleName(), "Error getting location");
    }

    interface ArrivalClickListener {
        void onArrivalClicked(Arrival arrivalClicked);
    }

    public static class StopPointsAdapter extends RecyclerView.Adapter<StopPointsAdapter.ViewHolder> {

        private ArrivalClickListener mArrivalClickListener;

        private List<StopPointWithArrivals> mStopPointWithArrivals = new ArrayList<>();

        private Context mContext;

        public StopPointsAdapter(Context context, ArrivalClickListener arrivalClickListener) {
            mContext = context;
            mArrivalClickListener = arrivalClickListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_stoppoint, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            final StopPointWithArrivals stopPointWithArrivals = mStopPointWithArrivals.get(position);
            holder.updateStopPointWithArrivals(stopPointWithArrivals);
            holder.stopPointNameTextView.setText(stopPointWithArrivals.getStopPoint().getCommonName());

            if (stopPointWithArrivals.getArrivals().size() > 0)
                setArrivalTimePrediction(holder.arrivalOneTextView, stopPointWithArrivals.getArrivals().get(0));
            if (stopPointWithArrivals.getArrivals().size() > 1)
                setArrivalTimePrediction(holder.arrivalTwoTextView, stopPointWithArrivals.getArrivals().get(1));
            if (stopPointWithArrivals.getArrivals().size() > 2)
                setArrivalTimePrediction(holder.arrivalThreeTextView, stopPointWithArrivals.getArrivals().get(2));
        }

        private void setArrivalTimePrediction(TextView textView, Arrival arrival) {
            int arrivalMinutePrediction = (int) Math.floor(arrival.getTimeToStation()/60);
            if (arrivalMinutePrediction > 1) {
                textView.setText(mContext.getString(R.string.arrival_msg_mins_to_stop, arrivalMinutePrediction));
            } else if (arrivalMinutePrediction == 1) {
                textView.setText(mContext.getString(R.string.arrival_msg_one_min_to_stop));
            } else if (arrivalMinutePrediction == 0) {
                textView.setText(mContext.getString(R.string.arrival_msg_due));
            }
        }

        @Override
        public int getItemCount() {
            return mStopPointWithArrivals.size();
        }

        public void updateStopPointsWithArrivals(List<StopPointWithArrivals> stopPointWithArrivals) {
            mStopPointWithArrivals = stopPointWithArrivals;
            notifyDataSetChanged();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            StopPointWithArrivals mStopPointWithArrivals;

            final TextView stopPointNameTextView;
            final TextView arrivalOneTextView;
            final TextView arrivalTwoTextView;
            final TextView arrivalThreeTextView;

            ViewHolder(View itemView) {
                super(itemView);
                stopPointNameTextView = itemView.findViewById(R.id.text_stop_name);
                arrivalOneTextView = itemView.findViewById(R.id.text_arrival_1);
                arrivalOneTextView.setOnClickListener(view -> mArrivalClickListener.onArrivalClicked(mStopPointWithArrivals.getArrivals().get(0)));
                arrivalTwoTextView = itemView.findViewById(R.id.text_arrival_2);
                arrivalTwoTextView.setOnClickListener(view -> mArrivalClickListener.onArrivalClicked(mStopPointWithArrivals.getArrivals().get(1)));
                arrivalThreeTextView = itemView.findViewById(R.id.text_arrival_3);
                arrivalThreeTextView.setOnClickListener(view -> mArrivalClickListener.onArrivalClicked(mStopPointWithArrivals.getArrivals().get(2)));
            }

            void updateStopPointWithArrivals(StopPointWithArrivals stopPointWithArrivals) {
                mStopPointWithArrivals = stopPointWithArrivals;
            }
        }
    }
}
