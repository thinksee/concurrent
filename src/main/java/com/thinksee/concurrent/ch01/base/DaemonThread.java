package com.thinksee.concurrent.ch01.base;

/**
 * Created by thinksee on 2020/4/30 0030.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description 设置为守护线程的功能是为了让守护线程随着主线程的结束而结束。
 *          使用场景包括GC的内存收集。
 **/
public class DaemonThread {
    private static class UseThread extends Thread {
        UseThread(){
            this.setName("UserThread");
        }
        @Override
        public void run() {
            try{
                boolean flag;
                while (!(flag = isInterrupted())) {
                    System.out.println("中断标志为" + flag + "\t当前线程为" + Thread.currentThread().getName());
                }
                System.out.println("中断标志为" + flag + "\t当前线程为" + Thread.currentThread().getName());
            }finally {
                // 有一定的概率不被执行
                System.out.println("其他资源释放。。。。。。");
            }
        }
    }
    /*
    * 1. setDeamon不要放在start之后
    * 2. setDeamon的线程在finally函数中可能执行不了
    * 3. setDeamon的线程其子线程也为setDeamon线程
    */
    public static void main(String[] args) throws InterruptedException {
        UseThread useThread = new UseThread();
//
        useThread.start();
//useThread.setDaemon(true);
        useThread.setDaemon(true);
        Thread.sleep(5);
        useThread.interrupt();
    }
}
