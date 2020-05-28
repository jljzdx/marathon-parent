package com.newera.marathon.base.demo.designpattern.dynamicproxy.cglib;

public class Test {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy(new Engineer());
        // 创建代理对象
        Object obj = proxy.createProxyObject();
        Engineer engineerProxy = (Engineer) obj;
        // 调用相关方法
        engineerProxy.eat();
    }
}
