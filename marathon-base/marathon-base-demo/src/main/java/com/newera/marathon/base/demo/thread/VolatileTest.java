package com.newera.marathon.base.demo.thread;

/**
 * 执行结果：程序永远不会跳出循环了。
 * 原因：首先主线程会把flag与i从‘主内存’中拷贝一份到自己的工作内存中，因为vt.flag = true;只是更新了主内存中的flag值，
 * 而主线程读的又是自己的工作内存，所以flag一直都是false。
 * 解决：volatile boolean flag = false;
 */
public class VolatileTest {
    static boolean flag = false;

    public static void main(String[] args) throws Exception {
        new Thread(()->{
            System.out.println("waiting data......");
            while (!flag){
                //System.out.println("8");
            }
            System.out.println("success......");
        }).start();
        Thread.sleep(1000);
        new Thread(()->{
            System.out.println("prepare data......");
            flag = true;
            System.out.println("prepare data end......");
        }).start();

    }
}
