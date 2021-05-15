package com.shensen.interview.algorithm;

import com.shensen.interview.algorithm.leetcode.editor.cn.ListNode;
import org.apache.commons.lang3.StringUtils;

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

        System.err.println();

        ListNode<String> node = null;
        String lineStr = "", letterStr = "";
        for (int i = 0; i <= arr.length - 1; i++) {
            if (arr[i] != '-') {
                letterStr += arr[i];
                if (i < arr.length - 2 && arr[i + 1] != '-') continue;
            } else {
                lineStr += arr[i];
                // 下一位非'-'终止
                if (i < arr.length - 2 && arr[i + 1] == '-') continue;
            }
            if (StringUtils.isNoneBlank(lineStr)) {
                if (null == node) {
                    node = new ListNode<>(lineStr);
                } else {
                    node.addNode(new ListNode<>(lineStr));
                }
                lineStr = "";
                continue;
            }

            if (StringUtils.isNoneBlank(letterStr)) {
                node.addNode(new ListNode<>(letterStr));
                letterStr = "";
                continue;
            }
        }

        node = reverseList(node);
        while (null != node) {
            System.out.print(node.val);
            node = node.next;
        }
    }

    private static void reversePrint(String str) {
        char[] arr = str.toCharArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            System.err.print(arr[i]);
        }
    }

    public static ListNode reverseList(ListNode<String> head) {
        ListNode prev = null;
        ListNode curr = head;
        while (null != curr) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
