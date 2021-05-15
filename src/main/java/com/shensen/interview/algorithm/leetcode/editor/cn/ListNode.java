package com.shensen.interview.algorithm.leetcode.editor.cn;

public class ListNode<T> {

    public T val;
    public ListNode<T> next;

    public ListNode() {
    }

    public ListNode(T val) {
        this.val = val;
    }

    public ListNode(T val, ListNode<T> next) {
        this.val = val;
        this.next = next;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public void addNode(ListNode e) {
        if (this.next == null) {
            this.next = e;
        } else {
            this.next.addNode(e);
        }
    }
}