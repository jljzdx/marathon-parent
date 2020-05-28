package com.newera.marathon.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static final String FORMAT1 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT2 = "yyyy-MM-dd";
    public static final String FORMAT3 = "yyyyMMdd";

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


    /**
     * 获取当前时间字符串
     * @return
     */
    public static String getCurrentTimeString(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT1);
        return sdf.format(date);
    }

    /**
     * 日期格式之间转换
     * @param dateString
     * @param formatSource
     * @param formatTarget
     * @return
     */
    public static String formatConvertFormat(String dateString, String formatSource, String formatTarget){
        SimpleDateFormat sdf = new SimpleDateFormat(formatSource);
        String result = "";
        try {
            Date date = sdf.parse(dateString);
            result = dateToStringFormat(date, formatTarget);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取给定日期多少年/月/日之后的日期
     * @param date
     * @param type Calendar.YEAR/Calendar.DATE/Calendar.MONTH/Calendar.WEEK_OF_MONTH
     * @param interval
     * @return
     */
    public static Date getAfterDate(Date date, int type, int interval){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(type,interval);
        return cal.getTime();
    }

    public static void main(String[] args) {
        System.out.println(dateToStringFormat(getAfterDate(new Date(),Calendar.YEAR,1),FORMAT1));
        System.out.println(dateToStringFormat(getAfterDate(new Date(),Calendar.DATE,1),FORMAT1));
        System.out.println(dateToStringFormat(getAfterDate(new Date(),Calendar.MONTH,1),FORMAT1));
        System.out.println(dateToStringFormat(getAfterDate(new Date(),Calendar.WEEK_OF_MONTH,1),FORMAT1));
    }

}
