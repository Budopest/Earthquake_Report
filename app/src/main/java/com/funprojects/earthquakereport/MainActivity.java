package com.funprojects.earthquakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<Earthquake> quakeList = QueryUtils.extractEarthquakes();
        EarthquakeAdabter earthquakeAdabter = new EarthquakeAdabter(this,quakeList);
        ListView listView = (ListView) findViewById(R.id.quakelist);
        listView.setAdapter(earthquakeAdabter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent webPage = new Intent();
                webPage.setAction(Intent.ACTION_VIEW);
                webPage.setData(Uri.parse(quakeList.get(position).getUrl()));
                startActivity(webPage);
            }
        });


    }
}
