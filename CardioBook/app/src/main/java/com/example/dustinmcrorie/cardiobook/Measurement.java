package com.example.dustinmcrorie.cardiobook;


/**
 * This class is the essential class for this application;
 * it stores cardio information, date and time, and a comment if the user
 * so desires to put one.
 * @author Dustin McRorie
 * @version 1.0
 */
public class Measurement {

    private String date;
    private String time;
    private int systolic_pressure;
    private int dystolic_pressure;
    private int heart_rate;
    private String comment;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setSystolic_pressure(int systolic_pressure) {
        if (systolic_pressure >= 0)
            this.systolic_pressure = systolic_pressure;
    }

    public int getSystolic_pressure() {
        return systolic_pressure;
    }

    public void setDystolic_pressure(int dystolic_pressure) {
        if (dystolic_pressure >= 0)
            this.dystolic_pressure = dystolic_pressure;
    }

    public int getDystolic_pressure() {
        return dystolic_pressure;
    }

    public void setHeart_rate(int heart_rate) {
        if (heart_rate >= 0)
            this.heart_rate = heart_rate;
    }

    public int getHeart_rate() {
        return heart_rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
