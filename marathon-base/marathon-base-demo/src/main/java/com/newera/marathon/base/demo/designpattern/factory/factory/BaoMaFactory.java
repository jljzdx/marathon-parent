package com.newera.marathon.base.demo.designpattern.factory.factory;

import com.newera.marathon.base.demo.designpattern.factory.easy.BaoMa;
import com.newera.marathon.base.demo.designpattern.factory.easy.Car;

public class BaoMaFactory implements Factory {
    @Override
    public Car buyCar() {
        return new BaoMa();
    }
}
