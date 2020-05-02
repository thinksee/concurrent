package com.thinksee.concurrent.ch02.forkjoin.sort;

import com.thinksee.concurrent.ch02.forkjoin.sum.MakeArray;

import java.util.Arrays;

/**
 * Created by thinksee on 2020/5/2 0002.
 *
 * @author 1563896950@qq.com
 * @github https://www.github.com/thinksee
 **/
public class MergeSort {
    public static int[] sort(int[] array) {
        if(array.length <= MakeArray.THRESHOLD) {
            return InsertionSort.sort(array);
        }else{
            int mid = array.length / 2;
            int[] left = Arrays.copyOfRange(array, 0, mid);
            int[] right = Arrays.copyOfRange(array, mid, array.length);
            return merge(sort(left), sort(right));
        }
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for(int index = 0, i = 0, j = 0; index < result.length; ++index) {
            if(i >= left.length) {
                result[index] = right[j++];
            }else if(j >= right.length) {
                result[index] = left[i++];
            }else if(left[i] > right[j]){
                result[index] = right[j++];
            }else {
                result[index] = left[i++];
            }
        }
//        Arrays.sort();
//        Collections.sort();
        return result;
    }
    public static void main(String[] args) {
        int[] src = MakeArray.makeArray();
        long start = System.currentTimeMillis();
        sort(src);
        System.out.println(" spend time:"+(System.currentTimeMillis()-start)+"ms");
    }
}
