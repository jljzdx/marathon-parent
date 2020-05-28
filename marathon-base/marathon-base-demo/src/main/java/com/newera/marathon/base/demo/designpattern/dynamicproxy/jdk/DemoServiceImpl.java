package com.newera.marathon.base.demo.designpattern.dynamicproxy.jdk;

public class DemoServiceImpl implements DemoService {

    @Override
    public void addOrder(String order) {
        System.out.println("向数据库添加一条订单："+order);
    }
}
