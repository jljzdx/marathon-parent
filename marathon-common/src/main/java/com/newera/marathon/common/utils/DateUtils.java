package com.newera.marathon.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final String FORMAT1 = "yyyy-MM-dd HH:mm:ss";
    public static String dateToStringFormat(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
