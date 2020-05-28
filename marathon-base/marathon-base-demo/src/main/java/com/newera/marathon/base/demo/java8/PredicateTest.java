package com.newera.marathon.base.demo.java8;

import java.util.function.Predicate;

public class PredicateTest {
    public static void main(String[] args) {
        Predicate<String> p01=(str)->str.isEmpty()||str.trim().isEmpty();
        System.out.println(p01.test(""));
        System.out.println(p01.test("  "));
        System.out.println(p01.test("admin"));
    }
}
