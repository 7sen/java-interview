package com.shensen.interview.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序实现
 *
 * @author Alwyn
 * @date 2021-04-02 14:41
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = new int[]{1, 9, 3, 5, 6, 8, 4, 7, 0};
        System.out.println(Arrays.toString(sort(array)));
    }

    public static int[] sort(int[] array) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(array, array.length);
        System.out.println(Arrays.toString(arr));

        int len = arr.length;
        // 构建堆
        buildHeap(arr, len);

        System.out.println(Arrays.toString(arr));

        for (int i = len - 1; i > 0; i--) {
            // 将堆顶元素与末位元素对换
            swap(arr, 0, i);
            // 隐藏堆尾元素
            len--;
            // 将堆顶元素下沉，将最大元素浮到堆顶来
            sink(arr, 0, len);
        }
        return arr;
    }

    private static void buildHeap(int[] arr, int len) {
        for (int i = len / 2; i >= 0; i--) {
            sink(arr, i, len);
        }
    }

    private static void sink(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            sink(arr, largest, len);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}