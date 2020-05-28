package com.newera.marathon.base.demo.java8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {
    public static void main(String[] args) {
        Consumer<Collection> c01 = (collection) -> {
            if (null != collection && collection.size() > 0) {
                for (Object c : collection) {
                    System.out.println(c);
                }
            }
        };

        List<String> list = new ArrayList<String>();
        list.add("诸葛亮");
        list.add("曹操");
        list.add("关羽");
        c01.accept(list);
    }
}
