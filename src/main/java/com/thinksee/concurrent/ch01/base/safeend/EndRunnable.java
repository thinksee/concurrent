package com.thinksee.concurrent.ch01.base.safeend;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description runnable方式
 * 调用interrupt方式，使得中断信号置为true，获取中断信号然后跳出循环。
 **/
public class EndRunnable {
    private static class UseRunable implements Runnable {
        @Override
        public void run() {
            boolean flag ;
            while(!(flag = Thread.currentThread().isInterrupted())) {
                System.out.println(Thread.currentThread().getName());
            }
            System.out.println(Thread.currentThread().getName() + "interrupt flag is " + flag);
        }
    }

    public static void main(String[] args) {
        UseRunable useRunable = new UseRunable();
        Thread endThread = new Thread(useRunable, "endThread");
        endThread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        endThread.interrupt();
    }
}
