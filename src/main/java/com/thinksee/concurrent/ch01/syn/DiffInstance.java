package com.thinksee.concurrent.ch01.syn;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description this锁、object锁
 * 锁的是两个不同的实例锁，所以两个线程可以并行执行
 * 线程1和线程2同时启动-运行-结束
 * 若锁的是相同的实例锁，则两个线程不可以并行执行
 **/
public class DiffInstance {
    private static class Instance1Syn implements Runnable {
        // Object锁
        private DiffInstance diffInstance;

        public Instance1Syn(DiffInstance diffInstance) {
            this.diffInstance = diffInstance;
        }

        @Override
        public void run() {
            System.out.println("Test Instance1 is running..." + diffInstance);
            diffInstance.instance1();
        }
    }

    private static class Instance2Syn implements Runnable {
        private DiffInstance diffInstance;

        public Instance2Syn(DiffInstance diffInstance) {
            this.diffInstance = diffInstance;
        }
        @Override
        public void run() {
            System.out.println("Tes Instance2 is running..." + diffInstance);
            diffInstance.instance2();
        }
    }

    private synchronized void instance1() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Synchronized Instance is going ..." + this.toString());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Synchronized Instance ended " + this.toString());
    }

    private synchronized void instance2() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Synchronized Instance is going ..." + this.toString());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Synchronized Instance ended " + this.toString());
    }

    public static void main(String[] args) {
//        DiffInstance instance1 = new DiffInstance();
//        DiffInstance instance2 = new DiffInstance();
//        new Thread(new Instance2Syn(instance1)).start();
//        new Thread(new Instance1Syn(instance2)).start();

        DiffInstance instance = new DiffInstance();
        new Thread(new Instance2Syn(instance)).start();
        new Thread(new Instance1Syn(instance)).start();
    }
}
