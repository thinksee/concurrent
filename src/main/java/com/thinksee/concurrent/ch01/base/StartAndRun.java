package com.thinksee.concurrent.ch01.base;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description start和run
 * 1. start表示 线程开始
 * 2. run表示   定义业务逻辑，使用start启动业务逻辑
 **/
public class StartAndRun {
    public static class ThreadRun extends Thread {
        @Override
        public void run() {
            int i = 6;
            while(--i > 0) {
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadRun threadRun = new ThreadRun();
        threadRun.setName("threadRun");
        threadRun.start();
    }
}
