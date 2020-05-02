package com.thinksee.concurrent.ch01.pool;

import com.thinksee.concurrent.tools.SleepTools;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description 线程池的基本实现：wait和notify/notifyAll
 * CountDownLatch的方式：是一个发令枪，使一个线程等待其他线程完成各自的工作后再执行。
 * CountDownLatch是通过一个计数器来实现的，计数器的初始值为初始化任务的数量。
 * 每当完成一个任务之后，CountDownLatch的值就会减1。
 *
 * 每个线程都有 20 次机会去获得锁，在这个固定的时间内需要获得更多的锁
 * todo : 每次都有线程大小的CountDown数目无法进行消耗
 **/
public class DBPoolTest {
    // 线程池的大小
    static DBPool pool = new DBPool(20);
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 50;
        end = new CountDownLatch(threadCount);        // 允许的CountDownLatch的次数
        int count = 10;                               // 每个线程的操作次数
        AtomicInteger got = new AtomicInteger();      // 计数器：统计可以拿到连接的线程
        AtomicInteger notGot = new AtomicInteger();   // 计数器：统计没有拿到连接的线程
        for(int i = 0; i < threadCount; ++i) {
            Thread thread = new Thread(new Worker(count, got, notGot),
                    "worker_" + i);
            thread.start();
        }
        System.out.println("End");
        end.await();// main线程在此处等待

        System.out.println("总共尝试了: " + (threadCount * count)); //  1000次连接
        System.out.println("拿到连接的次数：  " + got);
        System.out.println("没能连接的次数： " + notGot);
    }

    static class Worker implements Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public Worker(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }
        @Override
        public void run() {
            while (count > 0) {
                try {
                    // 从线程池中获取连接，如果1000ms内无法获取到，将会返回null
                    // 分别统计连接获取的数量got和未获取到的数量notGot
                    Connection connection = null;
                    connection = pool.fetchConnection(1000);
                    if (connection != null) {
//                        try {
//                            connection.createStatement();
////                            PreparedStatement preparedStatement
////                                    = connection.prepareStatement("");
////                            preparedStatement.execute();
//                            connection.commit();
//                        } finally {
//
//                        }
                        SleepTools.ms(2);
                        pool.releaseConnection(connection);
                        got.incrementAndGet();
                    } else {
                        notGot.incrementAndGet();
                        System.out.println(Thread.currentThread().getName()
                                +"等待超时!");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
            System.out.println("当前countDown : " + end.getCount());
            System.out.println("当前线程" + Thread.currentThread().getName() + "已经尝试过了。。。。");
        }
    }
}
