package com.shensen.interview.algorithm;

/**
 * <p>
 * 题目：给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 给定二叉树 [3,9,20,null,null,15,7]，返回true；
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]，返回false；
 *
 * 平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1
 * </p>
 *
 * @author Alwyn
 * @date 2019-07-17 15:38
 */
public class JudgeAVL {

    public static void main(String[] args) {
        BinaryTree E = new BinaryTree("7", null, null);
        BinaryTree D = new BinaryTree("15", null, null);
        BinaryTree C = new BinaryTree("20", D, E);
        BinaryTree B = new BinaryTree("9", null, null);
        BinaryTree A = new BinaryTree("3", B, C);
        AvlInteface solutionA = new SolutionA();
        System.out.println(solutionA.isBalanced(A));

        BinaryTree G1 = new BinaryTree("4", null, null);
        BinaryTree F1 = new BinaryTree("4", null, null);
        BinaryTree E1 = new BinaryTree("3", F1, G1);
        BinaryTree D1 = new BinaryTree("3", null, null);
        BinaryTree C1 = new BinaryTree("2", null, null);
        BinaryTree B1 = new BinaryTree("2", D1, E1);
        BinaryTree A1 = new BinaryTree("1", B1, C1);
        AvlInteface solutionB = new SolutionB();
        System.out.println(solutionB.isBalanced(A1));
    }

    interface AvlInteface {

        boolean isBalanced(BinaryTree root);

        int depth(BinaryTree root);
    }

    /**
     * 从底至顶（提前阻断法）
     */
    static class SolutionA implements AvlInteface {

        @Override
        public boolean isBalanced(BinaryTree root) {
            return depth(root) != -1;
        }

        @Override
        public int depth(BinaryTree root) {
            if (root == null) return 0;
            int left = depth(root.getLeft());
            if (left == -1) return -1;
            int right = depth(root.getRight());
            if (right == -1) return -1;
            return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
        }
    }

    /**
     * 从顶至底（暴力法）
     */
    static class SolutionB implements AvlInteface {

        @Override
        public boolean isBalanced(BinaryTree root) {
            if (root == null) return true;
            return Math.abs(depth(root.getLeft()) - depth(root.getRight())) <= 1 && isBalanced(root.getLeft())
                    && isBalanced(root.getRight());
        }

        @Override
        public int depth(BinaryTree root) {
            if (root == null) return 0;
            return Math.max(depth(root.getLeft()), depth(root.getRight())) + 1;
        }
    }
}
