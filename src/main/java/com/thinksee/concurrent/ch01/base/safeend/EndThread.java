package com.thinksee.concurrent.ch01.base.safeend;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description thread和runnable的调用方式不同，runnable是使用的Thread的静态方式
 **/
public class EndThread {
    private static class UseThread extends Thread {
        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " interrupt flag = " + isInterrupted());
            while(!isInterrupted()) {
                System.out.println(threadName + " is running....");
            }
            System.out.println(threadName + " interrupt flag : " + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread endThread = new UseThread("endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();
    }
}
