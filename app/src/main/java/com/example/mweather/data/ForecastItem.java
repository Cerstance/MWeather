package com.example.mweather.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ForecastItem {
    private String tvWeekDay;
    private String tvMax;
    private String tvMin;

    private String ivDay;
    private String ivNight;

    public String getTvWeekDay() {
        return tvWeekDay;
    }

    public void setTvWeekDay(String tvWeekDay) {
        this.tvWeekDay = tvWeekDay;
    }

    public String getTvMax() {
        return tvMax;
    }

    public void setTvMax(String tvMax) {
        this.tvMax = tvMax;
    }

    public String getTvMin() {
        return tvMin;
    }

    public void setTvMin(String tvMin) {
        this.tvMin = tvMin;
    }

    public String getIvDay() {
        return ivDay;
    }

    public void setIvDay(String ivDay) {
        this.ivDay = ivDay;
    }

    public String getIvNight() {
        return ivNight;
    }

    public void setIvNight(String ivNight) {
        this.ivNight = ivNight;
    }
}
