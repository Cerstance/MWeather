package com.example.mweather.bean;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class DailyBean {
    private String code;
    private Date updateTime;
    private String fxLink;
    private List<Daily> daily;
    private Refer refer;

    public String getCode() {
        return code;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getFxLink() {
        return fxLink;
    }

    public List<Daily> getDaily() {
        return daily;
    }

    public Refer getRefer() {
        return refer;
    }

    public class Daily {
        private Date fxDate;
        private String sunrise;
        private String sunset;
        private String moonrise;
        private String moonset;
        private String moonPhase;
        private String tempMax;
        private String tempMin;
        private String iconDay;
        private String textDay;
        private String iconNight;
        private String textNight;
        private String wind360Day;
        private String windDirDay;
        private String windScaleDay;
        private String windSpeedDay;
        private String wind360Night;
        private String windDirNight;
        private String windScaleNight;
        private String windSpeedNight;
        private String humidity;
        private String precip;
        private String pressure;
        private String vis;
        private String cloud;
        private String uvIndex;

        public Date getFxDate() {
            return fxDate;
        }

        public String getSunrise() {
            return sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public String getMoonrise() {
            return moonrise;
        }

        public String getMoonset() {
            return moonset;
        }

        public String getMoonPhase() {
            return moonPhase;
        }

        public String getTempMax() {
            return tempMax;
        }

        public String getTempMin() {
            return tempMin;
        }

        public String getIconDay() {
            return iconDay;
        }

        public String getTextDay() {
            return textDay;
        }

        public String getIconNight() {
            return iconNight;
        }

        public String getTextNight() {
            return textNight;
        }

        public String getWind360Day() {
            return wind360Day;
        }

        public String getWindDirDay() {
            return windDirDay;
        }

        public String getWindScaleDay() {
            return windScaleDay;
        }

        public String getWindSpeedDay() {
            return windSpeedDay;
        }

        public String getWind360Night() {
            return wind360Night;
        }

        public String getWindDirNight() {
            return windDirNight;
        }

        public String getWindScaleNight() {
            return windScaleNight;
        }

        public String getWindSpeedNight() {
            return windSpeedNight;
        }

        public String getHumidity() {
            return humidity;
        }

        public String getPrecip() {
            return precip;
        }

        public String getPressure() {
            return pressure;
        }

        public String getVis() {
            return vis;
        }

        public String getCloud() {
            return cloud;
        }

        public String getUvIndex() {
            return uvIndex;
        }
    }

    public class Refer {
        private List<String> sources;
        private List<String> license;

        public List<String> getSources() {
            return sources;
        }

        public List<String> getLicense() {
            return license;
        }
    }
}