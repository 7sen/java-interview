package com.shensen.interview.algorithm.leetcode;

import com.shensen.interview.algorithm.leetcode.editor.cn.ListNode;

/**
 * <p>
 * 题目：两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * </p>
 * <pre>
 *  示例：
 *  输入：l1 = [2,4,3], l2 = [5,6,4]
 *  输出：[7,0,8]
 *  解释：342 + 465 = 807.
 * </pre>
 *
 * @author Alwyn
 * @date 2021-02-28 21:47
 */
public class TwoAdd {

    public static ListNode addTwo(ListNode<Integer> l1, ListNode<Integer> l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4));
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5, new ListNode(6));
        l2.next.next = new ListNode(4);

        ListNode result = addTwo(l1, l2);
        if (null != result) {
            System.out.print(result.val);
        }
        while (result.next != null) {
            System.out.print(" -> ");
            System.out.print(result.next.val);
            result = result.next;
        }
    }
}