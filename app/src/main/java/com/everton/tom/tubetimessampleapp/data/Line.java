package com.everton.tom.tubetimessampleapp.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Immutable class for a line
 */

public class Line {

    @NonNull
    private final String name;

    @NonNull
    @SerializedName("naptantIds")
    private final List<String> stopPointIds;

    public Line(@NonNull String name, @NonNull List<String> stopPointIds) {
        this.name = name;
        this.stopPointIds = stopPointIds;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public List<String> getStopPointIds() {
        return stopPointIds;
    }
}
