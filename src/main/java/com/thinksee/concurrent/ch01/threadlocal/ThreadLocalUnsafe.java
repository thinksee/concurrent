package com.thinksee.concurrent.ch01.threadlocal;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description 由于使用的是static类变量，
 * 会导致其中每个线程的修改都会影响到其他线程，修改方式改为类的属性变量
 **/
public class ThreadLocalUnsafe implements Runnable{
    public static Number number = new Number(0);

    public void run() {
        //每个线程计数加一
        number.setNum(number.getNum()+1);
        //将其存储到ThreadLocal中
        value.set(number);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出num值
        System.out.println(Thread.currentThread().getName()+"="+value.get().getNum());
    }

    public static ThreadLocal<Number> value = new ThreadLocal<Number>() {
    };

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadLocalUnsafe()).start();
        }
    }

    private static class Number {
        public Number(int num) {
            this.num = num;
        }

        private int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Number [num=" + num + "]";
        }
    }
}
