package com.newera.marathon.base.demo.designpattern.decorator;

public abstract class Decorator implements Person {
    protected Person person;

    public Decorator(Person person) {
        this.person = person;
    }

    public void eat(){
        person.eat();
    }
}
