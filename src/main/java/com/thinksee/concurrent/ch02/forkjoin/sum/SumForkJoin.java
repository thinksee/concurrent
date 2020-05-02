package com.thinksee.concurrent.ch02.forkjoin.sum;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by thinksee on 2020/5/2 0002.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description 使用Fork-Join框架进行求和
 * 1. Fork/Join是一个抽象类，一般不会直接继承与ForkJoinTask，
 *   1.1 RecursiveAction用于没有返回结果的任务
 *   1.2 RecursiveTask用于有返回结果的任务
 *   需要实现其中的compute方法
 * 2. task要通过ForkJoinPool来执行，使用submit和invoke进行提交，
 *   2.1 invoke是同步执行，调用之后需要等待任务完成，才能执行后面的代码
 *   2.2 submit是异步执行
 * 3. join和get方法返回计算结果，
 * 4. compute方法，
 * The count is 2389639209 spend time:10314ms
 **/
public class SumForkJoin {
    private static class SumTask extends RecursiveTask<Long> {
        private final static int THRESHOLD = MakeArray.ARRAY_LENGTH / 10; // 拆分数组阈值
        private int[] src;
        private int fromIndex;
        private int toIndex;
        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }
        @Override
        protected Long compute() {
            if(toIndex - fromIndex < THRESHOLD) {
                long count = 0;
                for(int i = fromIndex; i <= toIndex; ++i) {
//                    SleepTools.ms(1);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count += src[i];
                }
                return count;
            }else {
                int mid = (fromIndex + toIndex) / 2;
                SumTask left = new SumTask(src, fromIndex, mid);
                SumTask right = new SumTask(src, mid + 1, toIndex);
                invokeAll(left, right);
//                try {
//                    return left.get() + right.get();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
                return left.join() + right.join();
            }
        }
    }

    public static void main(String[] args) {
        int[] src = MakeArray.makeArray();
        ForkJoinPool pool = new ForkJoinPool();
        SumTask innerFind = new SumTask(src, 0, src.length - 1);
        long start = System.currentTimeMillis();
        pool.invoke(innerFind);
        System.out.println("The count is "+innerFind.join()
                +" spend time:"+(System.currentTimeMillis()-start)+"ms");
    }
}
