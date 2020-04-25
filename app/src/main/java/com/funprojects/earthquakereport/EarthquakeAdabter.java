package com.funprojects.earthquakereport;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class EarthquakeAdabter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdabter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }
}
