package com.newera.marathon.base.demo.thread;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {
        /*ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 20,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                new ThreadPoolExecutor.AbortPolicy());*/
//        ExecutorService service = Executors.newCachedThreadPool();
//        ExecutorService service = Executors.newScheduledThreadPool(10);
//        ExecutorService service = Executors.newSingleThreadExecutor();
//        ExecutorService service = Executors.newWorkStealingPool(10);
        ExecutorService service = Executors.newFixedThreadPool(5);
        ThreadPoolExecutor executor = (ThreadPoolExecutor)service;
        /*Runnable runnable = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };*/
        for(int i=0;i<15;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行完别的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}
class MyTask implements Runnable {
    private int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行task "+taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }
}
