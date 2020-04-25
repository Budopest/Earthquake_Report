package com.funprojects.earthquakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Earthquake> earthquakeArray = new ArrayList<Earthquake>();
        earthquakeArray.add(new Earthquake("San francisco"));
        earthquakeArray.add(new Earthquake("Tokyo"));
        earthquakeArray.add(new Earthquake("Los Anglos"));

        EarthquakeAdabter earthquakeAdabter = new EarthquakeAdabter(this,earthquakeArray);
        ListView listView = (ListView) findViewById(R.id.quakelist);
        listView.setAdapter(earthquakeAdabter);


    }
}
