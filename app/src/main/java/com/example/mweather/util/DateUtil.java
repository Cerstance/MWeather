package com.example.mweather.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String getWeek(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int num = cal.get(Calendar.DAY_OF_WEEK);
        switch (num){
            case 1:
                return new String("周日");
            case 2:
                return new String("周一");
            case 3:
                return new String("周二");
            case 4:
                return new String("周三");
            case 5:
                return new String("周四");
            case 6:
                return new String("周五");
            case 7:
                return new String("周六");
            default:
                return null;
        }
    }
}
