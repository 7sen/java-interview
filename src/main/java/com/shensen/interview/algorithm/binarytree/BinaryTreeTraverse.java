package com.shensen.interview.algorithm.binarytree;

/**
 * <p>
 * 二叉树遍历.
 * 前序遍历：根左右。先打印，再遍历左子树，再遍历右子树；
 * 中序遍历：左根右。先遍历左子树，再打印，再遍历右子树；
 * 后序遍历：左右根。先遍历左子树，再遍历右子树，再打印；
 * 层序遍历：根左右。左右子树从上到下依次打印；
 * </p>
 *
 * @author Alwyn
 * @date 2019-07-05 11:59
 */
public class BinaryTreeTraverse {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree root = tree.init();
        System.err.print("前序：");
        tree.firstOrderTraversal(root);
        System.err.print("\n中序：");
        tree.inOrderTraversal(root);
        System.err.print("\n后序：");
        tree.postOrderTraversal(root);
        System.err.print("\n层序：");
        tree.sequenceTraversal(root);

        BinaryTree.insert(root, "N");
        System.err.print("\n插入后：");
        tree.sequenceTraversal(root);
        System.err.println();
    }
}

