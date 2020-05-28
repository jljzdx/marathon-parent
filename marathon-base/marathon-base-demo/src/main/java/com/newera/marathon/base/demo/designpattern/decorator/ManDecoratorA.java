package com.newera.marathon.base.demo.designpattern.decorator;

public class ManDecoratorA extends Decorator {
    public ManDecoratorA(Person person) {
        super(person);
    }
    public void eat(){
        super.eat();
        reEat();
    }
    public void reEat(){
        System.out.println("男人A再吃一顿饭");
    }
}
