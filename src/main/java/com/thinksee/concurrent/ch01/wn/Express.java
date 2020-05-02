package com.thinksee.concurrent.ch01.wn;

/**
 * Created by thinksee on 2020/5/1 0001.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description wait-notifyAll
 * 1. wait会释放锁
 * 2. notifyAll会使得唤醒所有线程自然包括自身。
 **/
public class Express {
    public final static String CITY = "ShangHai";
    private int km;/*快递运输里程数*/
    private String site;/*快递到达地点*/

    public Express() {
    }

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /* 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理*/
    public synchronized void changeKm(){
        //TODO
        this.km = 101;
        notifyAll();
    }

    /* 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理*/
    public  synchronized  void changeSite(){
        this.site = "BeiJing";
        notifyAll();
    }

    public synchronized void waitKm(){
        //TODO
        while (this.km < 100) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the Km is "+this.km+",I will change db");
    }

    public synchronized void waitSite(){
        while(this.site.equals(CITY)){//快递到达目的地
            try {
                wait();
                System.out.println("Check Site thread["+Thread.currentThread().getId()
                        +"] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the site is "+this.site+",I will call user");
    }
}
