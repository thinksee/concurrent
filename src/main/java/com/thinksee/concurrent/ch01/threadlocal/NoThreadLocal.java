package com.thinksee.concurrent.ch01.threadlocal;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description 没有使用ThreadLocal的时候，变量之间是共享的，
 * 每个线程对变量的修改都会影响其他线程的。
 **/
public class NoThreadLocal {
    static Integer count = new Integer(1);

    public void startThreadArray() {
        Thread[] runs = new Thread[3];
        for(int i = 0; i < runs.length; ++i) {
            runs[i] = new Thread(new TestTask(i));
        }
        for(int i=0;i<runs.length;i++){
            runs[i].start();
        }
    }

    public static class TestTask implements Runnable {
        int id;
        public TestTask(int id) {
            this.id = id;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":start");
            count = count + id;
            System.out.println(Thread.currentThread().getName() + ":"
                    + count);
        }
    }

    public static void main(String[] args) {
        NoThreadLocal test = new NoThreadLocal();
        test.startThreadArray();
    }
}
