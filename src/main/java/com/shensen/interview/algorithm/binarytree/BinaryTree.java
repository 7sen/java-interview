package com.shensen.interview.algorithm.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BinaryTree {

    private String data;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree init() {
        BinaryTree K = new BinaryTree("K", null, null);
        BinaryTree J = new BinaryTree("J", null, null);
        BinaryTree I = new BinaryTree("I", null, null);
        BinaryTree H = new BinaryTree("H", null, null);
        BinaryTree G = new BinaryTree("G", null, null);
        BinaryTree F = new BinaryTree("F", null, K);
        BinaryTree E = new BinaryTree("E", null, J);
        BinaryTree D = new BinaryTree("D", H, I);
        BinaryTree C = new BinaryTree("C", F, G);
        BinaryTree B = new BinaryTree("B", D, E);
        BinaryTree A = new BinaryTree("A", B, C);
        return A;
    }

    public void printBinaryTree(BinaryTree binaryTree) {
        System.err.print("[" + binaryTree.getData() + "]");
    }

    /**
     * 先序遍历
     *
     * @param root
     */
    public void firstOrderTraversal(BinaryTree root) {
        printBinaryTree(root);
        if (root.getLeft() != null) {
            firstOrderTraversal(root.getLeft());
        }
        if (root.getRight() != null) {
            firstOrderTraversal(root.getRight());
        }
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public void inOrderTraversal(BinaryTree root) {
        if (root.getLeft() != null) {
            inOrderTraversal(root.getLeft());
        }
        printBinaryTree(root);
        if (root.getRight() != null) {
            inOrderTraversal(root.getRight());
        }
    }

    /**
     * 后序遍历
     *
     * @param root
     */
    public void postOrderTraversal(BinaryTree root) {
        if (root.getLeft() != null) {
            postOrderTraversal(root.getLeft());
        }
        if (root.getRight() != null) {
            postOrderTraversal(root.getRight());
        }
        printBinaryTree(root);
    }

    /**
     * 层序遍历
     *
     * @param root
     */
    public void sequenceTraversal(BinaryTree root) {
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTree tree = queue.poll();
            printBinaryTree(tree);
            if (tree.getLeft() != null) {
                queue.offer(tree.getLeft());
            }
            if (tree.getRight() != null) {
                queue.offer(tree.getRight());
            }
        }
    }
}
