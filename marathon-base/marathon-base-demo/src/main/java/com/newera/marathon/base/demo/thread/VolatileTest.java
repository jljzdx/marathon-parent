package com.newera.marathon.base.demo.thread;

/**
 * 执行结果：程序永远不会跳出循环了。
 * 原因：首先主线程会把flag与i从‘主内存’中拷贝一份到自己的工作内存中，因为vt.flag = true;只是更新了主内存中的flag值，
 * 而主线程读的又是自己的工作内存，所以flag一直都是false。
 * 解决：volatile boolean flag = false;
 */
public class VolatileTest extends Thread {
    boolean flag = false;
    int i = 0;

    public void run() {
        while (!flag) {
            i++;
        }
    }

    public static void main(String[] args) throws Exception {
        VolatileTest vt = new VolatileTest();
        vt.start();
        Thread.sleep(2000);
        vt.flag = true;
        System.out.println("stope" + vt.i);
    }
}
