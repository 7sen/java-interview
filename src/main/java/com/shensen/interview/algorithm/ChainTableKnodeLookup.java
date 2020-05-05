package com.shensen.interview.algorithm;

import lombok.Getter;

/**
 * <p>
 * 题目：输入一个链表，输出该链表中倒数第k个结点。
 * </p>
 *
 * @author Alwyn
 * @date 2019-07-05 15:34
 */
public class ChainTableKnodeLookup {

    public static void main(String[] args) {
        Node<Integer> F = new Node<>(6, null);
        Node<Integer> E = new Node<>(5, F);
        Node<Integer> D = new Node<>(4, E);
        Node<Integer> C = new Node<>(3, D);
        Node<Integer> B = new Node<>(2, C);
        Node<Integer> A = new Node<>(1, B);

        System.out.println(lookupKnode1(A, 3).getItem());
        System.out.println(lookupKnode2(A, 3).getItem());
    }

    /**
     * 方法1：
     * 设置两个结点，利用双引用遍历，让结点1先遍历k次，走在节点2的前面，其中要注意结点1为null时返回;
     * 然后再在结点1不为空时，先走结点1，再走结点2，直至结点1为空；最后返回结点2;
     *
     * @param head
     * @param k
     * @return
     */
    public static <T> Node<T> lookupKnode1(Node<T> head, int k) {
        if (k < 1) {
            throw new RuntimeException("不支持位置查找!");
        }
        Node n1 = head;
        Node n2 = head;
        for (int i = 0; i < k; i++) {
            if (n1 == null) {
                return null;
            }
            n1 = n1.next;
        }
        while (n1 != null) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n2;
    }

    /**
     * 方法2：先求出链表的长度length，再利用 length-k 重新遍历，其中，当length < k时，那就没有这个值，需返回null.
     *
     * @param head
     * @param k
     * @return
     */
    public static <T> Node<T> lookupKnode2(Node<T> head, int k) {
        if (k < 1) {
            throw new RuntimeException("不支持位置查找!");
        }
        int length = 0;
        Node<T> node = head;
        while (null != node) {
            node = node.next;
            length++;
        }

        node = head;
        for (int i = 0; i < length - k; i++) {
            node = node.next;
        }

        return node;
    }

    @Getter
    private static class Node<E> {

        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
