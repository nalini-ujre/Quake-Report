package com.example.android.quakereport;

import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Nalini on 9/28/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> eq) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, eq);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Earthquake currentEq = getItem(position);


        TextView magTextView = (TextView) listItemView.findViewById(R.id.mag_text_view);
        String currentMag = formatMagnitude(currentEq.getMagnitude());
        magTextView.setText(currentMag);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEq.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        String[] place;
        place = splitLocation(currentEq.getPlace());

        TextView place1TextView = (TextView) listItemView.findViewById(R.id.place1_text_view);
        place1TextView.setText(place[0]);

        TextView place2TextView = (TextView) listItemView.findViewById(R.id.place2_text_view);
        place2TextView.setText(place[1]);

        Date dateObject = new Date(currentEq.getDate());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_text_view);
        dateTextView.setText(formatDate(dateObject));

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_text_view);
        timeTextView.setText(formatTime(dateObject));

        return listItemView;
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String[] splitLocation(String location){
       String[] loc_Offset = new String[2];
        if (location.contains(LOCATION_SEPARATOR)) {
            String[] parts = location.split(LOCATION_SEPARATOR);
            loc_Offset[0] = parts[0] + LOCATION_SEPARATOR;
            loc_Offset[1] = parts[1];
        } else {
            loc_Offset[0] = getContext().getString(R.string.near_the);
            loc_Offset[1] = location;
        }

        return loc_Offset;
    }

    private int getMagnitudeColor(double magnitude)
    {   int magColor;
        int mag = (int) Math.floor(magnitude);
        switch (mag) {
            case 0:
            case 1:
                magColor = R.color.magnitude1;
                break;
            case 2:
                magColor = R.color.magnitude2;
                break;
            case 3:
                magColor = R.color.magnitude3;
                break;
            case 4:
                magColor = R.color.magnitude4;
                break;
            case 5:
                magColor = R.color.magnitude5;
                break;
            case 6:
                magColor = R.color.magnitude6;
                break;
            case 7:
                magColor = R.color.magnitude7;
                break;
            case 8:
                magColor = R.color.magnitude8;
                break;
            case 9:
                magColor = R.color.magnitude9;
                break;
            default:
                magColor = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magColor);
    }
}
