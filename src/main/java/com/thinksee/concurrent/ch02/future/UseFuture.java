package com.thinksee.concurrent.ch02.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by thinksee on 2020/5/3 0003.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description
 * 1. Future就是对于具体的Runnable或者Callable任务
 * 执行结果、进行取消、查询是否完成、获取结果。
 * 必要时可以通过get方法获取执行结果，该方法会阻塞直到任务返回结果。
 * 2. FutureTask类实现了RunnableFuture接口，RunnableFuture继承了
 * Runable接口和Future接口，这样一来，FutureTask既可以作为Runnable
 * 被线程执行，又可以作为Future得到Callable的返回值。
 **/
public class UseFuture {
    private static class UseCallable implements Callable<Integer> {
        private int sum;
        @Override
        public Integer call() throws Exception {
            System.out.println("Callable子线程开始计算！");
//			Thread.sleep(1000);
            for(int i=0 ;i<5000;i++){
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("Callable子线程计算任务中断！");
                    return null;
                }
                sum=sum+i;
                System.out.println("sum="+sum);
            }
            System.out.println("Callable子线程计算结束！结果为: "+sum);
            return sum;
        }

        public static void main(String[] args) throws InterruptedException, ExecutionException {
            UseCallable useCallable = new UseCallable();
            //包装
            FutureTask<Integer> futureTask = new FutureTask<>(useCallable);
            Random r = new Random();
            new Thread(futureTask).start();

            Thread.sleep(1);
            if(10>50){
                System.out.println("Get UseCallable result = "+futureTask.get());
            }else{
                System.out.println("Cancel................. ");
                futureTask.cancel(true);  //  调用的是interrupt方法
            }
        }
    }
}
