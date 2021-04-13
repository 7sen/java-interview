package com.shensen.interview.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 堆排序实现
 *
 * @author Alwyn
 * @date 2021-04-02 14:41
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = new int[]{1, 9, 3, 5, 6, 8, 4, 7, 0};
        // 基于优先队列实现
        PriorityQueue<Integer> queue = new PriorityQueue<>(array.length, Comparator.naturalOrder());
        for (int arr : array) {
            queue.add(arr);
        }
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            list.add(queue.poll());
        }
        System.out.println(Arrays.toString(list.toArray()));

        System.out.println(Arrays.toString(sort(array)));
    }

    public static int[] sort(int[] arr) {
        int len = arr.length;
        buildHeap(arr, len);
        int i;
        for (i = len - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }
        return arr;
    }

    private static void buildHeap(int[] arr, int n) {
        for (int i = (n - 1) / 2; i >= 0; i--) {
            heapify(arr, i, n);
        }
    }

    private static void heapify(int[] arr, int i, int n) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;
        if (left < n && arr[left] > arr[max]) {
            max = left;
        }
        if (right < n && arr[right] > arr[max]) {
            max = right;
        }
        if (max != i) {
            swap(arr, i, max);
            heapify(arr, max, n);
        }
    }

    private static void swap(int[] arr, int i, int max) {
        int temp = arr[i];
        arr[i] = arr[max];
        arr[max] = temp;
    }
}