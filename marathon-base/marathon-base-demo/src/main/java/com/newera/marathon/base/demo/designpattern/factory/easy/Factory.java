package com.newera.marathon.base.demo.designpattern.factory.easy;

public class Factory {
    public Car buyCar(String name){
        Car car = null;
        switch (name){
            case "BM":
                car = new BaoMa();
                break;
            case "BC":
                car = new BenChi();
                break;
        }
        return car;
    }
}
