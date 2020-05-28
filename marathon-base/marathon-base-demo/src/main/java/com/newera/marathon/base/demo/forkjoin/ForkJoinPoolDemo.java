package com.newera.marathon.base.demo.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * ForkJoinPool与其它的ExecutorService区别主要在于它使用“工作窃取”：线程池中的所有线程都企图找到并执行提交给线程池的任务。
 * RecursiveAction：没返回值的
 */
public class ForkJoinPoolDemo {
    class SendMsgTask extends RecursiveAction {

        private final int THRESHOLD = 10;

        private int start;
        private int end;
        private List<String> list;

        public SendMsgTask(int start, int end, List<String> list) {
            this.start = start;
            this.end = end;
            this.list = list;
        }

        @Override
        protected void compute() {

            if ((end - start) <= THRESHOLD) {
                for (int i = start; i < end; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + list.get(i));
                }
            }else {
                int middle = (start + end) / 2;
                invokeAll(new SendMsgTask(start, middle, list), new SendMsgTask(middle, end, list));
            }

        }

    }

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 123; i++) {
            list.add(String.valueOf(i+1));
        }

        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new ForkJoinPoolDemo().new SendMsgTask(0, list.size(), list));
        pool.awaitTermination(10, TimeUnit.SECONDS);
        pool.shutdown();
    }
}
