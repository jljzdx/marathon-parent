package com.newera.marathon.common.web;

import java.util.HashMap;
import java.util.Map;

public class ListResponseConverter {
    public static Map listResponseConverter(boolean success, String code, String msg, Long total, Object dataList){
        Map map = new HashMap();
        if(success){
            map.put("code",0);
        }else{
            map.put("code",code);
        }
        map.put("msg",msg);
        map.put("count",total);
        map.put("data",dataList);
        return map;
    }
    public static Map listResponseConverter(boolean success, String code, String msg, Object dataList){
        Map map = new HashMap();
        if(success){
            map.put("code",0);
        }else{
            map.put("code",code);
        }
        map.put("msg",msg);
        map.put("data",dataList);
        return map;
    }
}
