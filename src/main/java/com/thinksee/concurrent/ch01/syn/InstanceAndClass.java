package com.thinksee.concurrent.ch01.syn;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description
 * 1. static变量也称作静态变量，静态变量和非静态变量的区别是：
 *      静态变量被所有的对象所共享，在内存中只有一个副本，它当且仅当在类初次加载时会被初始化。
 *      非静态变量是对象所拥有的，在创建对象的时候被初始化，存在多个副本，各个对象拥有的副本互不影响。
 *      static成员变量的初始化顺序按照定义的顺序进行初始化
 * 2. static 方法
 *      一般称为静态方法，静态方法不依赖于任何类的实例就可以进行访问，即通过类就可以执行方法。
 *      静态方法中不能访问 this 的，因为它不需要实例就可以执行。
 *
 * 类锁：类本身的静态方法加锁，实例锁：是对类的一个实例化对象进行加锁。
 **/
public class InstanceAndClass {
    private static class SynClass extends Thread {
        // 类静态方法锁
        @Override
        public void run() {
            System.out.println("Test Class is running....");
            synClass();
        }
    }

    private static class InstanceSyn implements Runnable {
        // 类实例锁
        private InstanceAndClass SynClassAndInstance;
        public InstanceSyn(InstanceAndClass synClassAndInstance) {
            this.SynClassAndInstance = synClassAndInstance;
        }
        @Override
        public void run() {
            System.out.println("Test Instance is running..." + SynClassAndInstance);
            SynClassAndInstance.instance();
        }
    }
    private synchronized void instance(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("synInstance is going..."+this.toString());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("synInstance ended "+this.toString());
    }

    private static synchronized void synClass(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("synClass going...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("synClass end");
    }

    public static void main(String[] args) {
        InstanceAndClass synClassAndInstance = new InstanceAndClass();
        Thread t1 = new SynClass();
        Thread t2 = new Thread(new InstanceSyn(synClassAndInstance));
        t2.start();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        t1.start();
    }
}
