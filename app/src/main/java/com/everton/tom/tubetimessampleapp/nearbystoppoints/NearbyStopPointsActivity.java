package com.everton.tom.tubetimessampleapp.nearbystoppoints;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.everton.tom.tubetimessampleapp.R;

/**
 * Created by Tom on 03/12/2017.
 */

public class NearbyStopPointsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_stop_points);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
            checkPermission();
        }

        if (savedInstanceState == null) {
            NearbyStopPointsFragment nearbyStopPointsFragment = new NearbyStopPointsFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_fragment, nearbyStopPointsFragment)
                    .commit();
        }
    }

    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    123);
        }
    }
}
