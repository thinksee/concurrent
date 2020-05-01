package com.thinksee.concurrent.ch01.base;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description Join方式
 *  谁join谁的权利大
 **/
public class UseJoin {
    static class Goddess implements Runnable {
        private Thread thread;
        public Goddess(){}
        public Goddess(Thread thread){
            this.thread = thread;
        }
        @Override
        public void run() {
            Thread.currentThread().setName("Goddess");
            System.out.println("Goddess开始排队打饭。。。");
            if(thread.isAlive()) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "Goddess打饭完成。。。");
            }
        }
    }

    static class GoddessBoyfriend implements Runnable {
        private final static int MAX = 500;
        @Override
        public void run() {
            Thread.currentThread().setName("GoddessBoyfriend");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            doSomething();
            System.out.println(Thread.currentThread().getName() + "完成打饭。。。");
        }

        private void doSomething(){
            int idx = 0;
            while((idx++) < MAX) {

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = Thread.currentThread();
        t1.setName("thinksee");
        // 继承runnable接口， 业务实现
        GoddessBoyfriend goddessBoyfriend = new GoddessBoyfriend();
        Thread t2 = new Thread(goddessBoyfriend);
        // t2传入至t3，这样一来t2会打断t3，先让t2执行
        Goddess goddess = new Goddess(t2);
        Thread t3 = new Thread(goddess);
        t3.start();
        t2.start();

        System.out.println(t1.getName() + "排队打饭。。。。");
        // t3抢占cpu，使得t1让出cpu。
        t3.join();
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + "打饭OK。");
    }

}
