package com.thinksee.concurrent.ch01.syn;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description 静态方法锁和静态变量锁也是不同的
 * 1. new两个不同的class之后其调用锁static方法，也是按照锁依次进行的
 * 2. static变量更是如此，因为在内存中仅存在一份static变量
 **/
public class StaticAndClass {
    private static class SynClass extends Thread{
        @Override
        public void run() {
            System.out.println(currentThread().getName()
                    +":synClass is running...");
            synClass();
        }
    }

    private static class SynStatic extends Thread{
        @Override
        public void run() {
            System.out.println(currentThread().getName()
                    +":synStatic is running...");
            synStatic();
        }
    }

    private static synchronized void synClass(){
        System.out.println(Thread.currentThread().getName()
                +":synClass going...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()
                +":synClass end");
    }

    private static Object obj = new Object();
    private static void synStatic(){
        synchronized (obj){
            System.out.println(Thread.currentThread().getName()
                    +":synStatic going...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    +":synStatic end");
        }
    }

    public static void main(String[] args) {
        StaticAndClass synClassAndInstance = new StaticAndClass();
//        Thread t1 = new SynClass();
//        //Thread t2 = new SynStatic();
//        Thread t2 = new SynClass();
//        t2.start();
////        try {
////            Thread.sleep(3000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//        t1.start();
        Thread t1 = new SynStatic();
        //Thread t2 = new SynStatic();
        Thread t2 = new SynStatic();
        t2.start();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        t1.start();
    }
}
