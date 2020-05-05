package com.shensen.interview.algorithm;

/**
 * <p>
 * 题目：输入 '-----abc----def---' 输出 '---def----abc-----'
 * </p>
 *
 * @author Alwyn
 * @date 2019-07-05 23:37
 */
public class ReverseOutput {

    public static void main(String[] args) {
        String str = "-----abc----def---";
        char[] arr = str.toCharArray();

        char line = '-';
        boolean flag = false;
        String temp = "";

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != line) {
                flag = true;
                temp += arr[i];
                continue;
            }
            if (flag) {
                flag = false;
                reversePrint(temp);
                temp = "";
            }
            System.err.print(arr[i]);
        }

    }

    private static void reversePrint(String str) {
        char[] arr = str.toCharArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            System.err.print(arr[i]);
        }
    }
}
