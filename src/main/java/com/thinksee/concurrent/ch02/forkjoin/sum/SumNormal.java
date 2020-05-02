package com.thinksee.concurrent.ch02.forkjoin.sum;

import com.thinksee.concurrent.tools.SleepTools;

/**
 * Created by thinksee on 2020/5/2 0002.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * 一般方法求和
 * The count is 2404817570 spend time:40184m
 **/
public class SumNormal {
    public static void main(String[] args) {
        long count = 0;
        int[] src = MakeArray.makeArray();

        long start = System.currentTimeMillis();
        for(int i = 0; i < src.length; ++i) {
//            SleepTools.ms(1);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count += src[i];
        }
        System.out.println("The count is "+count
                +" spend time:"+(System.currentTimeMillis()-start)+"ms");
    }
}
