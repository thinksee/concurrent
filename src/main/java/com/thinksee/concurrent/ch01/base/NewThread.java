package com.thinksee.concurrent.ch01.base;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description
 * 1. 设置线程的几种方式1)extends Thread 2)implements Runnable方法
 * 2. start方法只能被执行一次，否则会抛出
 *     Exception in thread "main" java.lang.IllegalThreadStateException
 * 3. start之后再开始run会？？todo
 **/
public class NewThread {
    /*
    * main start function....
    UserRunnable run function....
    UserRunnable
    UserRunnable run function....
    * */
    private static class UserThread extends Thread{
        @Override
        public void run() {
            this.setName("UserThread");
            System.out.println(Thread.currentThread().getName());
        }

        @Override
        public synchronized void start() {
//            super.start();
            /*
            * main start function....
            UserRunnable run function....
            UserRunnable
            UserRunnable run function....
            UserThread
            * */
            System.out.println(Thread.currentThread().getName() + " start function....");
        }
    }

    private static class UseRunnable implements Runnable{
        @Override
        public void run() {
            Thread.currentThread().setName("UserRunnable");
            System.out.println(Thread.currentThread().getName() + " run function....");
        }
    }

    public static void main(String[] args) {
        UserThread userThread = new UserThread();
//        userThread.start();
//        userThread.start();
        UseRunnable useRunnable = new UseRunnable();
        useRunnable.run();
        // UserRunnable run function....
        //UserRunnable
//        new Thread(useRunnable).start();
        userThread.run();
    }
}
