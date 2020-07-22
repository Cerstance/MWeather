package com.example.mweather;
import java.util.Date;
import java.util.List;

public class AirBean {
    private String code;
    private Date updateTime;
    private String fxLink;
    private Now now;
    private List<Station> station;
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

    public List<Station> getStation() {
        return station;
    }

    public Refer getRefer() {
        return refer;
    }

    public class Now {
        private Date pubTime;
        private String aqi;
        private String category;
        private String primary;
        private String pm10;
        private String pm2p5;
        private String no2;
        private String so2;
        private String co;
        private String o3;

        public Date getPubTime() {
            return pubTime;
        }

        public String getAqi() {
            return aqi;
        }

        public String getCategory() {
            return category;
        }

        public String getPrimary() {
            return primary;
        }

        public String getPm10() {
            return pm10;
        }

        public String getPm2p5() {
            return pm2p5;
        }

        public String getNo2() {
            return no2;
        }

        public String getSo2() {
            return so2;
        }

        public String getCo() {
            return co;
        }

        public String getO3() {
            return o3;
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

    public class Station {
        private Date pubTime;
        private String name;
        private String id;
        private String aqi;
        private String level;
        private String category;
        private String primary;
        private String pm10;
        private String pm2p5;
        private String no2;
        private String so2;
        private String co;
        private String o3;

        public Date getPubTime() {
            return pubTime;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public String getAqi() {
            return aqi;
        }

        public String getLevel() {
            return level;
        }

        public String getCategory() {
            return category;
        }

        public String getPrimary() {
            return primary;
        }

        public String getPm10() {
            return pm10;
        }

        public String getPm2p5() {
            return pm2p5;
        }

        public String getNo2() {
            return no2;
        }

        public String getSo2() {
            return so2;
        }

        public String getCo() {
            return co;
        }

        public String getO3() {
            return o3;
        }
    }

}