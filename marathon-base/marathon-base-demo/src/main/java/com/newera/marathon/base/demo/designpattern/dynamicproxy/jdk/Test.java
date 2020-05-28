package com.newera.marathon.base.demo.designpattern.dynamicproxy.jdk;

public class Test {
    public static void main(String[] args) {
        JdkProxy proxy = new JdkProxy(new DemoServiceImpl());
        //创建代理对象
        Object obj = proxy.createProxyObject();
        DemoService demoService = (DemoService) obj;
        // 调用相关方法
        demoService.addOrder("OP100000023");
    }
}