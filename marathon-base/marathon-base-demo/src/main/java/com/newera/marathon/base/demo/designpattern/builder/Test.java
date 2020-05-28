package com.newera.marathon.base.demo.designpattern.builder;

public class Test {
    public static void main(String[] args) {
        LombokComputer lombokComputer = LombokComputer.builder().cpu("i8").screen("15.3寸").build();
        System.out.println(lombokComputer.toString());
        Computer computer = Computer.builder().cpu("i9").screen("15.5寸").build();
        System.out.println(computer.toString());
    }
}
