package com.everton.tom.tubetimessampleapp.data;

import android.support.annotation.NonNull;

/**
 * Created by Tom on 30/11/2017.
 */

public class AdditionalProperty {

    private final String key;

    private final String value;

    private final String category;

    public AdditionalProperty(String key, String value, String category) {
        this.key = key;
        this.value = value;
        this.category = category;
    }

    @NonNull
    public String getKey() {
        return key;
    }

    @NonNull
    public String getValue() {
        return value;
    }

    public String getCategory() {
        return category;
    }
}
