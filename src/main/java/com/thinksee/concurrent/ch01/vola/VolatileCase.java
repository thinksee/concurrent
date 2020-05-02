package com.thinksee.concurrent.ch01.vola;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description volatile关键字使得不同线程之间都可以见到相应的变量
 **/
public class VolatileCase {
    // volatile 提供了可见性
    private static volatile boolean ready;
    private static int number;
    //  AtomicInteger
    private static class PrintThread extends Thread {
        @Override
        public void run() {
            System.out.println("PrintThread is running.......");
            System.out.println(ready);
            while(!ready);
            System.out.println("number = "+number);
        }
    }

    public static void main(String[] args) {
        new PrintThread().start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        number = 51;
        ready = true;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main is ended!");
    }
}
