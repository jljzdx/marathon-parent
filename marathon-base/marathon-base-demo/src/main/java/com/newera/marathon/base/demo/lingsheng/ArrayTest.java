package com.newera.marathon.base.demo.lingsheng;

import java.util.HashMap;
import java.util.Map;

public class ArrayTest {
    public static void main(String[] args) {
        Map<String,String> m = new HashMap();
        m.put("yu","wei");
        m.put("wang","bin");
        m.put("wei","bin");
        m.forEach((k,v)->{
            System.out.println(k+"-"+v);
        });
    }
}
