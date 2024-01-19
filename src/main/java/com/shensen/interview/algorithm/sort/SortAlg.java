package com.shensen.interview.algorithm.sort;

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

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 4、快速排序.算法复杂度O(nlogn)
     */
    static class QuickSort {

        private static int[] quickSort(int[] arr, int low, int high) {
            // 选出基准值
            int pivot = arr[low];
            // 从前向后索引
            int start = low;
            // 从后向前索引
            int end = high;
            while (start < end) {
                // 从后向前
                while (end > start && arr[end] >= pivot) {
                    end--;
                }
                if (arr[end] <= pivot) {
                    swap(arr, start, end);
                }
                // 从前向后
                while (end > start && arr[start] <= pivot) {
                    start++;
                }
                if (arr[start] >= pivot) {
                    swap(arr, start, end);
                }
            }
            if (start > low) quickSort(arr, low, start - 1);
            if (end < high) quickSort(arr, end + 1, high);
            return arr;
        }

        /* 选取三个元素的中位数*/
        static int medianThree(int[] nums, int left, int mid, int right) {
            // 此处使用异或运算来简化代码
            // 异或规则为0 ^ 0 = 1 ^ 1 = 0, 0 ^ 1 = 1 ^ 0 = 1
            if ((nums[left] < nums[mid]) ^ (nums[left] < nums[right])) return left;
            else if ((nums[mid] < nums[left]) ^ (nums[mid] < nums[right])) return mid;
            else return right;
        }

        /*
         * 哨兵划分（三数取中值），基准优化
         */
        private static int midPartition(int[] nums, int left, int right) {
            // 选取三个候选元素的中位数
            int med = medianThree(nums, left, (left + right) / 2, right);
            // 将中位数交换至数组最左端
            swap(nums, left, med);
            return partition(nums, left, right); // 返回基准数的索引
        }

        private static int partition(int[] nums, int left, int right) {
            // 以nums[left] 作为基准数
            int i = left, j = right;
            while (i < j) {
                while (i < j && nums[j] >= nums[left]) {
                    j--; // 从右向左找首个小于基准数的元素
                }
                while (i < j && nums[i] <= nums[left]) {
                    i++; // 从左向右找首个大于基准数的元素
                }
                swap(nums, i, j); // 交换这两个元素
            }
            swap(nums, i, left); // 将基准数交换至两子数组的分界线
            return i; // 返回基准数的索引
        }

        /**
         * 快速排序.
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
            int pivotIndex = partition(arr, startIndex, endIndex);
            sort(arr, startIndex, pivotIndex - 1);
            sort(arr, pivotIndex + 1, endIndex);
        }

        /**
         * 单边扫描切分.
         *
         * @param arr
         * @param startIndex
         * @param endIndex
         *
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
         *
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

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
