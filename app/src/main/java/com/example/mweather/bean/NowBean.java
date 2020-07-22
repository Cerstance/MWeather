package com.example.mweather.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class NowBean {
    private String code;
    private Date updateTime;
    private String fxLink;
    private Now now;
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

    public Now getNow() {
        return now;
    }

    public Refer getRefer() {
        return refer;
    }

    public class Now {
        private Date obsTime;
        private String temp;
        private String feelsLike;
        private String icon;
        private String text;
        private String wind360;
        private String windDir;
        private String windScale;
        private String windSpeed;
        private String humidity;
        private String precip;
        private String pressure;
        private String vis;
        private String cloud;
        private String dew;

        public Date getObsTime() {
            return obsTime;
        }

        public String getTemp() {
            return temp;
        }

        public String getFeelsLike() {
            return feelsLike;
        }

        public String getIcon() {
            return icon;
        }

        public String getText() {
            return text;
        }

        public String getWind360() {
            return wind360;
        }

        public String getWindDir() {
            return windDir;
        }

        public String getWindScale() {
            return windScale;
        }

        public String getWindSpeed() {
            return windSpeed;
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

        public String getDew() {
            return dew;
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
