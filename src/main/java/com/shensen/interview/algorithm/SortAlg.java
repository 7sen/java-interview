package com.shensen.interview.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 排序算法.
 * </p>
 *
 * @author Alwyn
 * @date 2019-06-30 10:21
 */
public class SortAlg {

    public static void main(String[] args) {

        int[] arr = new int[]{8, 2, 1, 7, 6, 5};

        QuickSort.sort(arr, 0, arr.length - 1);

        Arrays.stream(arr).forEach(a -> System.err.print(a));
    }

    /**
     * 4、快速排序.算法复杂度O(nlogn)
     *
     * @param arr
     */
    static class QuickSort {

        /**
         * 排序.
         *
         * @param arr
         * @param startIndex
         * @param endIndex
         */
        private static void sort(int[] arr, int startIndex, int endIndex) {
            if (endIndex <= startIndex) {
                return;
            }
            // 切分
            int pivotIndex = bilateralScan(arr, startIndex, endIndex);
            sort(arr, startIndex, pivotIndex - 1);
            sort(arr, pivotIndex + 1, endIndex);
        }

        /**
         * 单边扫描切分.
         *
         * @param arr
         * @param startIndex
         * @param endIndex
         * @return
         */
        private static int unilateralScan(int[] arr, int startIndex, int endIndex) {
            int pivot = arr[startIndex];// 取基准值
            int mark = startIndex;// Mark初始化为起始下标

            for (int i = startIndex + 1; i <= endIndex; i++) {
                if (arr[i] < pivot) {
                    // 小于基准值 则mark+1，并交换位置。
                    mark++;
                    int p = arr[mark];
                    arr[mark] = arr[i];
                    arr[i] = p;
                }
            }
            // 基准值与mark对应元素调换位置
            arr[startIndex] = arr[mark];
            arr[mark] = pivot;
            return mark;
        }


        /**
         * 双边扫描
         *
         * @param arr
         * @param startIndex
         * @param endIndex
         * @return
         */
        private static int bilateralScan(int[] arr, int startIndex, int endIndex) {
            Integer pivot = arr[startIndex];
            int left = startIndex, right = endIndex;
            while (left < right) {
                while (arr[right] >= pivot && left < right) {
                    right--;
                }
                arr[left] = arr[right];
                while (arr[left] <= pivot && left < right) {
                    left++;
                }
                arr[right] = arr[left];
            }
            arr[left] = pivot;
            return left;
        }
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
