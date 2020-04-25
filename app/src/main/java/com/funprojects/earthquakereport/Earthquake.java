package com.funprojects.earthquakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {

    private int magnitude;
    private long time;
    private String location;
    private String url;

    public Earthquake(int magnitude,long time,String location,String url){
        this.location = location;
        this.magnitude = magnitude;
        this.time = time;
        this.url = url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(int magnitude) {
        this.magnitude = magnitude;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getDisplayedDate(){
        Date date = new Date(getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return simpleDateFormat.format(date);

    }
    public String getDisplayedTime(){
        Date date = new Date(getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
        return simpleDateFormat.format(date);
    }
}
