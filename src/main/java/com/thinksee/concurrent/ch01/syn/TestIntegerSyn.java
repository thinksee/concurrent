package com.thinksee.concurrent.ch01.syn;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 **/
public class TestIntegerSyn {
    public static void main(String[] args){
        Worker worker=new Worker(1);
        //Thread.sleep(50);
        for(int i=0;i<5;i++) {
            new Thread(worker).start();
        }
    }

    private static class Worker implements Runnable{
        private Integer i;
        public Worker(Integer i) {
            this.i=i;
        }

        @Override
        public void run() {
            synchronized (i) {
                Thread thread=Thread.currentThread();
                System.out.println(thread.getName()+"--@"+System.identityHashCode(i));
                i++;
                System.out.println(thread.getName()+"-------"+i+"-@"+System.identityHashCode(i));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName()+"-------"+i+"--@"+System.identityHashCode(i));
            }
        }
    }
}
