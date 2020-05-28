package com.newera.marathon.base.demo;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        //创建n个1M大小的数组，耗尽内存
        for (int i = 0; i < 10000000; i++)
            list.add(new byte[1024 * 1024]);
    }
}