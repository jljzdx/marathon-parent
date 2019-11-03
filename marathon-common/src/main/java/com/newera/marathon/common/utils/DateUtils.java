package com.newera.marathon.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static final String FORMAT1 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期转字符串
     * @param date
     * @param format
     * @return
     */
    public static String dateToStringFormat(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 获取当前时间到第二天0点相差多少秒
     * @return
     */
    public static Long getExpireSeconds() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Long expirySeconds =  (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
        return expirySeconds;
    }
}
