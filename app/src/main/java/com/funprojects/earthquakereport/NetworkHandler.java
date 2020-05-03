package com.funprojects.earthquakereport;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import java.util.ArrayList;

public class NetworkHandler extends AsyncTask<String,Void, ArrayList<Earthquake>> {

    Context context;

    @Override
    protected ArrayList<Earthquake> doInBackground(String... url) {
        /*
         *perform no operations if there is no url
         */
        if(url.length<1 || url[0] ==null) return null;

        return QueryUtils.extractEarthquakes(url[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<Earthquake> earthquakes) {

        EarthquakeAdabter earthquakeAdabter = new EarthquakeAdabter(context,earthquakes) ;
        ListView listView = (ListView) ((Activity)context).findViewById(R.id.quakelist);
        listView.setAdapter(earthquakeAdabter);

    }
}
