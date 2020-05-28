package com.newera.marathon.base.demo.designpattern.factory.easy;

public class Test {
    public static void main(String[] args) {
        Factory factory = new Factory();
        Car car = factory.buyCar("BM");
        System.out.println(car.getName());
    }
}
