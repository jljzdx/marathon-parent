package com.newera.marathon.base.demo.designpattern.adapter;

public class ObjectAdapter implements Cat {
    private Dog dog;

    public ObjectAdapter(Dog dog) {
        this.dog = dog;
    }

    @Override
    public void eat() {
        if(null != dog){
            dog.say();
        }
    }
}
