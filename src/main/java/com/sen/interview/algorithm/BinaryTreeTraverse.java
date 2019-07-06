package com.sen.interview.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 二叉树遍历.
 * 前序遍历：根左右。先打印，再遍历左子树，再遍历右子树；
 * 中序遍历：左根右。先遍历左子树，再打印，再遍历右子树；
 * 后序遍历：左右根。先遍历左子树，再遍历右子树，再打印。
 * </p>
 *
 * @author Leo
 * @date 2019-07-05 11:59
 */
public class BinaryTreeTraverse {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree root = tree.init();
        System.out.print("前序：");
        tree.firstOrderTraversal(root);
        System.out.print("\n中序：");
        tree.inOrderTraversal(root);
        System.out.print("\n后序：");
        tree.postOrderTraversal(root);
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class BinaryTree {

    private String data;
    private BinaryTree leftTree;
    private BinaryTree rightTree;

    public BinaryTree init() {
        BinaryTree G = new BinaryTree("D", null, null);
        BinaryTree F = new BinaryTree("B", null, null);
        BinaryTree E = new BinaryTree("F", null, null);
        BinaryTree D = new BinaryTree("C", F, G);
        BinaryTree C = new BinaryTree("G", null, E);
        BinaryTree B = new BinaryTree("A", D, null);
        BinaryTree A = new BinaryTree("E", B, C);
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
        if (root.getLeftTree() != null) {
            firstOrderTraversal(root.getLeftTree());
        }
        if (root.getRightTree() != null) {
            firstOrderTraversal(root.getRightTree());
        }
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public void inOrderTraversal(BinaryTree root) {
        if (root.getLeftTree() != null) {
            inOrderTraversal(root.getLeftTree());
        }
        printBinaryTree(root);
        if (root.getRightTree() != null) {
            inOrderTraversal(root.getRightTree());
        }
    }

    /**
     * 后序遍历
     *
     * @param root
     */
    public void postOrderTraversal(BinaryTree root) {
        if (root.getLeftTree() != null) {
            postOrderTraversal(root.getLeftTree());
        }
        if (root.getRightTree() != null) {
            postOrderTraversal(root.getRightTree());
        }
        printBinaryTree(root);
    }
}