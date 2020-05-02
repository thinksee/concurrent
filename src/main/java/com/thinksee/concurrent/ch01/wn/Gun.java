package com.thinksee.concurrent.ch01.wn;

/**
 * Created by thinksee on 2020/5/2 0002.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description 小案例
 * 装弹-射击 -> 模型生产者消费者
 **/
public class Gun {
    private int volumn;    // 子弹容量
    public Gun(int volumn) {
        this.volumn = volumn;
    }
    private Integer gun = 0; // 标识子弹是否存在
    public synchronized void put() {
        while(gun >= volumn) {
            System.out.println(Thread.currentThread().getName() + " 发现子弹已经装满了，当前子弹剩余=" + gun);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gun++;
        notifyAll();
    }

    public synchronized void get() {
        while(gun <= 0) {
            System.out.println(Thread.currentThread().getName() + " 发现子弹已经射完了，当前子弹剩余=" + gun);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gun--;
        System.out.println(Thread.currentThread().getName() + " 发射一枚子弹，当前子弹剩余=" + gun);
        notifyAll();
    }

    static class Constom implements Runnable {
        Gun gun;
        public Constom(Gun gun) {
            this.gun = gun;
        }
        @Override
        public void run() {
            while(true) {
                gun.get();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Product implements Runnable {
        Gun gun;
        public Product(Gun gun) {
            this.gun = gun;
        }
        @Override
        public void run() {
            while(true) {
                gun.put();
                try {
                    Thread.sleep(6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        // 传入同一个class是为了修改使得多个线程之间都是互相可见的
        Gun gun = new Gun(20);
        for(int i =0;i<6;i++) {
            new Thread(new Product(gun),"生产者"+i).start();
        }
        for(int i =0;i<6;i++) {
            new Thread(new Constom(gun),"消费者"+i).start();
        }
    }
}
