package com.newera.marathon.base.demo.java8;

import java.util.function.Supplier;

public class SupplierTest {
    public static void main(String[] args) {
        Supplier<Person> s = () -> {
            return new Person();
        };
        s.get().getName();
    }
}
