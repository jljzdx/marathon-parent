package com.newera.marathon.common.utils;

import org.apache.commons.lang3.StringUtils;

public class StringBlankFormat {
    public static String formatBlankString(String field){
        if(StringUtils.isBlank(field)){
            return "--";
        }else{
            return field.trim();
        }
    }
}
