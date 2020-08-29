package com.thinksee.concurrent.ch01.base.safeend;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description InterruptedException中断sleep，会产生sleep interrupted异常，但是不会设置中断标志位。
 **/
public class HasInterrputException {

    private static class UseThread extends Thread {
        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            while (!isInterrupted()) {
                try {
                    Thread.sleep(10); // 若在执行这条语句的时候产生中断会抛出 java.lang.InterruptedException: sleep interrupted
                } catch (InterruptedException e) {
                    System.out.println(name + " in InterruptionException interrupt flag is " + interrupted());
                    interrupt();
                    e.printStackTrace();
                }
                System.out.println(name + ", I am extends Thread.");
            }
            System.out.println(name + " interrupt flag is " + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("HasInterruptEX");
        endThread.start();
        /*
         * 50 :
         * 1. java.lang.InterruptedException: sleep interrupted
         * 2. HasInterruptEX interrupt flag is true
         * 500 :
         * 1. java.lang.InterruptedException: sleep interrupted
         *
         * 以上情况出现的原因在于中断执行时，UseThread运行语句：isInterrupted或者sleep
         * 若在sleep中则，不会导致中断标志位的修改，仅是抛出异常；而isInterrupted会导致标志位的修改。
         *
         * I. 当一个线程被中断后，在进入wait，sleep，join方法时会抛出异常。
         * II. 当一个线程被中断之后，其运行状态只是处于中断转台并不是线程停止，
         */
        Thread.sleep(50);
        endThread.interrupt();
    }
}
