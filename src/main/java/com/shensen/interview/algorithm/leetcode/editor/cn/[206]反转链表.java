//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1696 👎 0


package com.shensen.interview.algorithm.leetcode.editor.cn;

class ReverseLinkedList {

    public static void main(String[] args) {
        Solution solution = new ReverseLinkedList().new Solution();
        ListNode head = new ListNode(1);
        ListNode A = new ListNode(2);
        head.next = A;
        ListNode B = new ListNode(3);
        A.next = B;
        ListNode C = new ListNode(4);
        B.next = C;
        ListNode D = new ListNode(5);
        C.next = D;
        head = solution.reverseListTwo(head);
        while (null != head) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    class Solution {

        // 递归拆解
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode newHead = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return newHead;
        }

        // Iterative 解法
        public ListNode reverseListTwo(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;

            while(curr != null) {
                ListNode nxt = curr.next;
                curr.next = prev; // 翻转箭头
                prev = curr; //三人行
                curr = nxt; //三人行
            }

            return prev;
        }
    }

}