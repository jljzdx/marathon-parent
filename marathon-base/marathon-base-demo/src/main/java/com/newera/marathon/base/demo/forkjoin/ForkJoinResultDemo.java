package com.newera.marathon.base.demo.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * RecursiveTask：有返回值的
 */
public class ForkJoinResultDemo {
    class SumTask extends RecursiveTask<List<String>> {

        private static final int THRESHOLD = 20;

        private int arr[];
        private int start;
        private int end;

        public SumTask(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        /**
         * 小计
         */
        private List<String> subtotal() {
            List<String> list = new ArrayList<>();
            for (int i = start; i < end; i++) {
                list.add(arr[i]+"");
                System.out.println(Thread.currentThread().getName() + ": ∑(" + arr[i] + ")");
            }
            return list;
        }

        @Override
        protected List<String> compute() {
            if ((end - start) <= THRESHOLD) {
                return subtotal();
            }else {
                List<String> result = new ArrayList<>();
                int middle = (start + end) / 2;
                SumTask left = new SumTask(arr, start, middle);
                SumTask right = new SumTask(arr, middle, end);
                left.fork();
                right.fork();
                List<String> leftList = left.join();
                List<String> rightList = right.join();
                result.addAll(leftList);
                result.addAll(rightList);
                return result;
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<List<String>> result = pool.submit(new ForkJoinResultDemo().new SumTask(arr, 0, arr.length));
        List<String> list = result.invoke();
        System.out.println("最终计算结果: " + list.toString());
        pool.shutdown();
    }
}
