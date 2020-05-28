package com.newera.marathon.base.demo.thread;

import java.util.concurrent.FutureTask;

public class ThreadTest {
    public static void main(String[] args) {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println(i);
            }
            return 1;
        });
        new Thread(task).start();
    }
}
