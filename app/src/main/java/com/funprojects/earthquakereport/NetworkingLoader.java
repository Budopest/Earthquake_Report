package com.funprojects.earthquakereport;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;

public class NetworkingLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {

    String url;

    public NetworkingLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

    @Nullable
    @Override
    public ArrayList<Earthquake> loadInBackground() {

        if (url == null) return null;
        return QueryUtils.extractEarthquakes(url);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
