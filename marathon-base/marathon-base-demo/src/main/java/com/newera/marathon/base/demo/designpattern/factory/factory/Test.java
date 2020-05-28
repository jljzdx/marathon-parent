package com.newera.marathon.base.demo.designpattern.factory.factory;

public class Test {
    public static void main(String[] args) {
        Factory baoMaFactory = new BaoMaFactory();
        System.out.println(baoMaFactory.buyCar().getName());
        Factory benChiFactory = new BenChiFactory();
        System.out.println(benChiFactory.buyCar().getName());
    }
}
