package com.thinksee.concurrent.ch02.forkjoin.sort;

import com.thinksee.concurrent.ch02.forkjoin.sum.MakeArray;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by thinksee on 2020/5/2 0002.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 **/
public class FkSort {
    private static class SumTask extends RecursiveTask<int[]> {
        private final static int THRESHOLD = 2;
        private int[] src;

        public SumTask(int[] src) {
            this.src = src;
        }
        @Override
        protected int[] compute() {
            if(src.length < THRESHOLD) {
                return InsertionSort.sort(src);
            }else {
                int mid = src.length >> 1;
                SumTask left = new SumTask(Arrays.copyOfRange(src, 0, mid));
                SumTask right = new SumTask(Arrays.copyOfRange(src, mid, src.length));
                invokeAll(left, right);
                int[] leftRet = left.join();
                int[] rightRet = right.join();
                return MergeSort.merge(leftRet, rightRet);
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] src = MakeArray.makeArray();
        SumTask innerFind = new SumTask(src);
        long start = System.currentTimeMillis();
//        ForkJoinTask<int[]> invoke = pool.submit(innerFind);
//        int[] ret = invoke.invoke();
        int[] ret = pool.invoke(innerFind);
        System.out.println(" spend time:"+(System.currentTimeMillis()-start)+"ms");
    }
}
