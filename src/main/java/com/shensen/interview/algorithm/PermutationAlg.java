package com.shensen.interview.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 题目：给定一个没有重复数字的序列，返回其所有可能的全排列。
 * </p>
 *
 * @author Alwyn
 * @date 2019-07-19 17:42
 */
public class PermutationAlg {


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        permutation(arr, 0);
    }

    /**
     * 全排列输出
     *
     * @param arr
     * @param index 开始全排列索引位置.
     */
    public static void permutation(int[] arr, int index) {
        if (arr == null || index < 0 || index > arr.length) {//1
            return;
        }

        if (index == arr.length - 1) {//2
            printlnArray(arr);
        } else {
            for (int i = index; i < arr.length; i++) {//3
                int temp = arr[i];//交换前缀,使之产生下一个前缀
                arr[i] = arr[index];
                arr[index] = temp;
                permutation(arr, index + 1);//4
                temp = arr[i]; //将前缀换回来,继续做上一个的前缀排列.//5
                arr[i] = arr[index];
                arr[index] = temp;
            }
        }
    }

    private static void printlnArray(int[] arr) {
        Arrays.stream(arr).forEach(a -> System.err.print(a));
        System.err.print("\n");
    }

}
