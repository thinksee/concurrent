package com.thinksee.concurrent.ch01.base;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description 测试sleep对锁的影响
 * 1. 对象锁，内置synchronized的修饰对象是一个对象类型（即Object的子类）
 * 2. 类锁、this锁
 **/
public class SleepLock {
    /*
    * 3个线程：Main, SleepLock和ThreadNotSleep
    * sleep线程不会丢弃cpu
    * */
    private Object lock = new Object();

    public static void main(String[] args) {
        SleepLock sleepLock = new SleepLock();
        Thread thread1 = sleepLock.new ThreadSleep();
        thread1.setName("Sleep Thread");

        Thread thread2 = sleepLock.new ThreadNotSleep();
        thread2.setName("Not Sleep Thread");
        // thread1是sleep线程，开始之后
        thread1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }

    private class ThreadSleep extends Thread {
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " will take the lock....");
            try {
                synchronized (lock) {
                    System.out.println(threadName + " has taken the lock.");
                    Thread.sleep(5000);
                    System.out.println("Finish the work : " + threadName);
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class ThreadNotSleep extends Thread {
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " will take the lock....");
            synchronized (lock) {
                System.out.println(threadName + " has taken the lock.");
                System.out.println("Finish the work : " + threadName);
            }
        }

    }
    /*
    * 小结：
    * 1. sleep是Thread类方法，wait是Object类中定义的方法
    * 2. Thread.sleep不会导致锁行为的改变，
    *    若当前线程是拥有锁的，那么Thread.sleep不会让出线程释放锁
    *    （原因：Thread.sleep是不会影响锁的相关行为的）
    * 3. Thread.sleep和Object.wait方法都会暂停当前线程，对于CPU资源
    *   不管哪种方式暂停的线程，都表示它暂时不再需要CPU的执行时间。
    *   OS会将执行时间分配给其他线程。区别是调用wait之后，
    *   需要使用notify和notifyAll才能重新获得CPU执行时间。
    * 4. Thread.State.BLOCKED（阻塞）表示线程正在获取锁时，
    *    因为锁不能获取到而被迫暂停执行下面的指令，一直等到这个锁被别的线程释放。
    *    BLOCKED状态下线程，OS调度机制需要决定下一个能够获取锁的线程是哪个，
    *    这种情况下，就是产生锁的争用，无论如何这都是很耗时的操作。
    * 5. Thread.State.BLOCKED（阻塞）表示线程正在获取锁时，因为锁不能获取到而被迫暂停执行下面的指令，
    *   一直等到这个锁被别的线程释放。BLOCKED状态下线程，
    *   OS调度机制需要决定下一个能够获取锁的线程是哪个，
    *   这种情况下，就是产生锁的争用，无论如何这都是很耗时的操作。
    * */
}
