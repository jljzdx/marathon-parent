package com.newera.marathon.base.demo.designpattern.decorator;

public class Test {
    public static void main(String[] args) {
        Decorator manDecorator = new ManDecoratorA(new Man());
        manDecorator.eat();
    }
}
