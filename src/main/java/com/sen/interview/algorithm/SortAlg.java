package com.sen.interview.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 排序算法.
 * </p>
 *
 * @author Leo
 * @date 2019-06-30 10:21
 */
public class SortAlg {

    public static void main(String[] args) {

        int[] arr = new int[]{8, 2, 1, 7, 6};

        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j = 0; // 插入的位置
            for (j = i; j > 0; j--) {
                if (arr[j - 1] > temp) {
                    arr[j] = arr[j - 1]; // 移动数据
                } else {
                    break;
                }
            }
            arr[j] = temp;  // 插入数据
        }

        Arrays.stream(arr).forEach(a -> System.err.print(a));
    }

    /**
     * 3、 选择排序.时间复杂度O(n²)
     *
     * @param arr
     */
    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;// 最小元素的下标
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;// 找最小值
                }
            }
            // 交换位置
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    /**
     * 2、插入排序.时间复杂度O(n²)
     *
     * @param arr
     */
    private static void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j = 0; // 插入的位置
            for (j = i; j > 0; j--) {
                if (arr[j - 1] > temp) {
                    arr[j] = arr[j - 1]; // 移动数据
                } else {
                    break;
                }
            }
            arr[j] = temp;  // 插入数据
        }
    }

    /**
     * 1、冒泡排序.算法复杂度O(n²)
     *
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 避免正确顺序遍历排序.
            boolean flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                int temp = arr[j + 1];
                if (arr[j] > arr[j + 1]) {
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    flag = false;
                }
            }
            if (flag) break;
        }
    }

}
