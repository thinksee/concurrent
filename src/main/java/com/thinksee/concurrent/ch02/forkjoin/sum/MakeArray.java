package com.thinksee.concurrent.ch02.forkjoin.sum;

import java.util.Random;

/**
 * Created by thinksee on 2020/5/2 0002.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 * @description 数组生成模拟器
 **/
public class MakeArray {
    public static final int ARRAY_LENGTH = 40000;
    public static final int THRESHOLD = 47;

    public static int[] makeArray(){
        Random random = new Random();
        int[] result = new int[ARRAY_LENGTH];
        for(int i = 0; i < ARRAY_LENGTH; ++i) {
            result[i] = random.nextInt(ARRAY_LENGTH * 3);
        }
        return result;
    }
}
