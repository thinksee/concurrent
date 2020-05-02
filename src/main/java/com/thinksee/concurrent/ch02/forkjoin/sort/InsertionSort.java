package com.thinksee.concurrent.ch02.forkjoin.sort;

import com.thinksee.concurrent.ch02.forkjoin.sum.MakeArray;

/**
 * Created by thinksee on 2020/5/2 0002.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 **/
public class InsertionSort {
    public static int[] sort(int[] array) {
        if(array.length == 0) {
            return array;
        }
        int currentValue;
        for(int i = 0; i < array.length - 1; ++i) {
            int preIndex = i;
            currentValue = array[preIndex + 1];

            while(preIndex >= 0 && currentValue < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = currentValue;
        }
        return array;
    }

    public static void main(String[] args) {
        System.out.println("============================================");
        InsertionSort.sort(MakeArray.makeArray());
    }
}
