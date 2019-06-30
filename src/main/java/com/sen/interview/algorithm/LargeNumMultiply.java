package com.sen.interview.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 题目：Java实现大算相乘运算.
 * </p>
 *
 * @author Leo
 * @date 2019-06-29 23:22
 */
public class LargeNumMultiply {

    public static void main(String[] args) {
        // 732 * 16
        int num = 16;
        int[] ints = new int[10];
        ints[ints.length - 1] = 2;
        ints[ints.length - 2] = 3;
        ints[ints.length - 3] = 7;

        ints = largeNumMultiply(ints, num);
        Arrays.stream(ints).forEach(t -> System.err.print(t));
        System.out.print("\n-------------------------------\n");
        int index = 0;
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] != 0) {
                index = i;
                break;
            }
        }
        for (int i = index; i < ints.length; i++) {
            System.err.print(ints[i]);
        }
    }

    private static int[] largeNumMultiply(int[] ints, int num) {
        for (int i = 0; i < ints.length; i++) {
            ints[i] *= num;
        }

        for (int i = ints.length - 1; i > 0; i--) {
            ints[i - 1] += ints[i] / 10;
            ints[i] = ints[i] % 10;
        }
        return ints;
    }

}
