//给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
//表节点，返回 反转后的链表 。
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], left = 2, right = 4
//输出：[1,4,3,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [5], left = 1, right = 1
//输出：[5]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目为 n 
// 1 <= n <= 500 
// -500 <= Node.val <= 500 
// 1 <= left <= right <= n 
// 
//
// 
//
// 进阶： 你可以使用一趟扫描完成反转吗？ 
// Related Topics 链表 
// 👍 872 👎 0


package com.shensen.interview.algorithm.leetcode.editor.cn;

class ReverseLinkedListIi {

    public static void main(String[] args) {
        Solution solution = new ReverseLinkedListIi().new Solution();
        //输入：head = [1,2,3,4,5], left = 2, right = 4
        ListNode head = new ListNode(1);
        ListNode A = new ListNode(2);
        head.next = A;
        ListNode B = new ListNode(3);
        A.next = B;
        ListNode C = new ListNode(4);
        B.next = C;
        ListNode D = new ListNode(5);
        C.next = D;

        //输出：[1,4,3,2,5]
        head = solution.reverseBetween(head, 1, 5);
        while (null != head) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Solution {

        public ListNode reverseBetween(ListNode head, int left, int right) {
            // 设置 dummyNode 节点
            ListNode dummyNode = new ListNode(-1);
            dummyNode.next = head;
            ListNode pre = dummyNode;
            for (int i = 0; i < left - 1; i++) {
                pre = pre.next;
            }
            ListNode cur = pre.next;
            ListNode next;
            for (int i = 0; i < right - left; i++) {
                next = cur.next;
                cur.next = next.next;
                next.next = pre.next;
                pre.next = next;
            }
            return dummyNode.next;
        }
    }

}