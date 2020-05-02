package com.thinksee.concurrent.tools;

import java.util.concurrent.TimeUnit;

/**
 * Created by thinksee on 2020/5/2 0002.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description 线程辅助类
 **/
public class SleepTools {
    public static final void second(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static final void ms(int seconds) {
        try {
            TimeUnit.MINUTES.sleep(seconds);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
