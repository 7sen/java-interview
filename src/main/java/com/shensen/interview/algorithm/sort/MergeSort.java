package com.shensen.interview.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序算法.
 * 步骤：分解 -> 归并
 *
 * @author Alwyn
 * @date 2021-04-25 08:26
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] data = new int[]{1, 9, 3, 5, 6, 8, 4, 7, 0};
        sort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));
    }

    public static void sort(int[] arr, int left, int right) {
        if (left >= right) return;
        // 找出中间元素
        int center = (left + right) / 2;
        sort(arr, left, center);
        sort(arr, center + 1, right);
        // 归并操作
        merge(arr, left, center, right);
    }

    private static void merge(int[] data, int left, int center, int right) {
        int[] tmpArr = new int[data.length];
        int mid = center + 1;
        // 记录临时数组元素
        int third = left;
        // 缓存左边数组第一个元素
        int tmp = left;
        while (left <= center && mid <= right) {
            // 从两边数组中取出最小值放进临时数组
            if (data[left] <= data[mid]) {
                tmpArr[third++] = data[left++];
            } else {
                tmpArr[third++] = data[mid++];
            }
        }
        // 将剩余部分依次放入数组,两个while只会执行一个
        while (left <= center) {
            tmpArr[third++] = data[left++];
        }
        while (mid <= right) {
            tmpArr[third++] = data[mid++];
        }
        // 将临时数组内容复制到原始数组内
        while (tmp <= right) {
            data[tmp] = tmpArr[tmp++];
        }
    }

}