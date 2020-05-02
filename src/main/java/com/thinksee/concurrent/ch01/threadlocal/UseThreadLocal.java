package com.thinksee.concurrent.ch01.threadlocal;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description
 * 模拟ThreadLocal，每个线程使用自己内部的变量，达到变量隔离的效果
 **/
public class UseThreadLocal {
    public void startThreadArray(){
        Thread[] runs = new Thread[3];
        for(int i = 0; i < runs.length; ++i) {
            runs[i] = new Thread(new TestThread(i));
        }
        for(int i = 0; i < runs.length; ++i){
            runs[i].start();
        }
    }

    public static class TestThread implements Runnable{
        int id;
        public TestThread(int id) {
            this.id = id;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": start.....");
        }
    }

    public static void main(String[] args) {
        UseThreadLocal test = new UseThreadLocal();
        test.startThreadArray();
    }
}
