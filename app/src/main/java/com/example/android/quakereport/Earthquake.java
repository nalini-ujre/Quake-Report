package com.example.android.quakereport;

/**
 * Created by Nalini on 9/28/2017.
 */

public class Earthquake {
    private double mMagnitude;
    private String mPlace;
    private long mTimeInMilliSecs;
    private String mUrl;

    public Earthquake(double mMag, String place, long time, String url)
    {
        mMagnitude = mMag;
        mPlace = place;
        mTimeInMilliSecs = time;
        mUrl = url;
    }

    public String getPlace()
    {
        return mPlace;
    }

    public double getMagnitude() {return mMagnitude; }

    public long getDate()
    {
        return mTimeInMilliSecs;
    }

    public String getURL()
    {
        return mUrl;
    }
}
