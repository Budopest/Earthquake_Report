package com.funprojects.earthquakereport;

public class Earthquake {
    private String location;

    public Earthquake(String location){
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
