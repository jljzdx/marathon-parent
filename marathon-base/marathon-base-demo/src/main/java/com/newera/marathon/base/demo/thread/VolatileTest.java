package com.newera.marathon.base.demo.thread;

import java.util.concurrent.TimeUnit;

/**
 * 执行结果：程序永远不会跳出循环了。
 * 原因：首先主线程会把flag与i从‘主内存’中拷贝一份到自己的工作内存中，因为vt.flag = true;只是更新了主内存中的flag值，
 * 而主线程读的又是自己的工作内存，所以flag一直都是false。
 * 解决：volatile boolean flag = false;
 */
public class VolatileTest {
    static class MyTest {
        public volatile int number = 0;
        public void changeNumber(){
            number = 100;
        }
    }
    public static void main(String[] args) throws InterruptedException{
        MyTest myTest = new MyTest();

        new Thread(() -> {
            System.out.println(String.format("线程%s开始执行", Thread.currentThread().getName()));

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myTest.changeNumber();
            System.out.println(String.format("线程%s的number：%d", Thread.currentThread().getName(), myTest.number));
        }, "NewThread").start();

        while (myTest.number == 0){

        }

        System.out.println("执行完毕");
    }
}
