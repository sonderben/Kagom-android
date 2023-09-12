package com.sonderben.kagom.data;

import java.io.Serializable;

public class Schedule implements Serializable {
    int dayOfWeek;
    float startHour,endHour;

    @Override
    public String toString() {
        return "Schedule{" +
                "dayOfWeek=" + dayOfWeek +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                '}';
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public float getStartHour() {
        return startHour;
    }

    public void setStartHour(float startHour) {
        this.startHour = startHour;
    }

    public float getEndHour() {
        return endHour;
    }

    public void setEndHour(float endHour) {
        this.endHour = endHour;
    }

    public Schedule(int dayOfWeek, float startHour, float endHour) {
        this.dayOfWeek = dayOfWeek;
        this.startHour = startHour;
        this.endHour = endHour;
    }
}
