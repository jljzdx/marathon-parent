package com.newera.marathon.base.demo.base;

public class EqualsTest {
    public static void main(String[] args) {
        EqualsTest a = new EqualsTest();
        EqualsTest b = new EqualsTest();
        System.out.println(a.equals(b));
        System.out.println(a.hashCode());

        String s = "a";
        String g = "s";
        System.out.println(s.equals(g));

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
