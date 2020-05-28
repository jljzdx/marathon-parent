package com.newera.marathon.base.demo.designpattern.factory.factory;

import com.newera.marathon.base.demo.designpattern.factory.easy.BenChi;
import com.newera.marathon.base.demo.designpattern.factory.easy.Car;

public class BenChiFactory implements Factory {
    @Override
    public Car buyCar() {
        return new BenChi();
    }
}
