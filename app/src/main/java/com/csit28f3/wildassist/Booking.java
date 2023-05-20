package com.csit28f3.wildassist;

import java.text.SimpleDateFormat;

public class Booking {
    private SimpleDateFormat date;
    private String startTime;
    private String endTime;
    private String destination;
    private String purpose;

    public Booking() {}

    public Booking(SimpleDateFormat date, String startTime, String endTime, String destination, String purpose) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.destination = destination;
        this.purpose = purpose;
    }

    public SimpleDateFormat getDate() {
        return date;
    }

    public void setDate(SimpleDateFormat date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
