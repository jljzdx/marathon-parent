package com.newera.marathon.base.demo.base;

public class IntegerTest {
    public static void main(String[] args) {
        Integer a = new Integer(97);
        Integer b = new Integer(97);
        System.out.println(a==b);
        //自动装箱
        Integer x = 128;//Integer.valueOf(128);
        Integer y = 128;
        System.out.println(x==y);
        //自动拆箱
        int n = x;//x.intValue();
    }
}
