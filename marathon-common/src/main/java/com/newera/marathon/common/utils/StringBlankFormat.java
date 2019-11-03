package com.newera.marathon.common.utils;

import org.apache.commons.lang3.StringUtils;

public class StringBlankFormat {

    public static final String FORMAT1 = "--";

    public static String formatBlankString(String field){
        if(StringUtils.isBlank(field)){
            return FORMAT1;
        }else{
            return field.trim();
        }
    }
}
