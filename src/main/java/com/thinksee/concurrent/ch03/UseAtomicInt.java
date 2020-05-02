package com.thinksee.concurrent.ch03;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by thinksee on 2020/5/3 0003.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 **/
public class UseAtomicInt {
    static AtomicInteger ai = new AtomicInteger(10);

    public static void main(String[] args) {
        ai.getAndIncrement();
        ai.incrementAndGet();
        //ai.compareAndSet();
        ai.addAndGet(24);
    }
}
