package com.funprojects.earthquakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView ==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);
        }
        final Earthquake currentEarthquake = getItem(position);
        TextView mag = (TextView) listItemView.findViewById(R.id.magText);
        TextView location = (TextView) listItemView.findViewById(R.id.locationText);
        TextView smallLocation = (TextView) listItemView.findViewById(R.id.smallLocationText);
        TextView time = (TextView) listItemView.findViewById(R.id.timeText);
        TextView date = (TextView) listItemView.findViewById(R.id.dateText);
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = ContextCompat.getColor(getContext(), currentEarthquake.getMagColor());
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);



        date.setText(currentEarthquake.getDisplayedDate());
        time.setText(currentEarthquake.getDisplayedTime());
        mag.setText(currentEarthquake.getFormattedMAG());
        String originalLocation = currentEarthquake.getLocation();
        // Check whether the originalLocation string contains the " of " text
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            // Split the string into different parts (as an array of Strings)
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            smallLocation.setText(parts[0] + LOCATION_SEPARATOR);
            location.setText(parts[1]);
        } else {
            smallLocation.setText("near the ");
            location.setText(originalLocation);
        }
        final Context context = getContext();
        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webpage = new Intent();
                webpage.setAction(Intent.ACTION_VIEW);
                webpage.setData(Uri.parse(currentEarthquake.getUrl()));
                context.startActivity(webpage);
            }
        });
        return listItemView;
    }
}
