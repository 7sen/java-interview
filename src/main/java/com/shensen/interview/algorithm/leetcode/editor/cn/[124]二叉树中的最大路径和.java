//路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不
//一定经过根节点。 
//
// 路径和 是路径中各节点值的总和。 
//
// 给你一个二叉树的根节点 root ，返回其 最大路径和 。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3]
//输出：6
//解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6 
//
// 示例 2： 
//
// 
//输入：root = [-10,9,20,null,null,15,7]
//输出：42
//解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围是 [1, 3 * 104] 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 1009 👎 0


package com.shensen.interview.algorithm.leetcode.editor.cn;


class BinaryTreeMaximumPathSum {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode();
        treeNode.val = -10;
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        Solution solution = new BinaryTreeMaximumPathSum().new Solution();
        System.out.println(solution.maxPathSum(treeNode));
    }

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {

        private int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            // 更新max
            depth(root);
            //返回
            return max;
        }

        private int depth(TreeNode root) {
            if (root == null) return 0;
            // 当前节点的左孩子节点的最大子路径和（注意舍弃掉和小于0的路径）
            int left = Math.max(0, depth(root.left));
            // 当前节点的右孩子节点的最大子路径和
            int right = Math.max(0, depth(root.right));
            // 更新最大路径和（最大左路径和+最大右路径和+当前节点值）
            max = Math.max(max, root.val + left + right);
            //返回从当前节点出发的最大左/右路径和
            return root.val + Math.max(left, right);
        }
    }

}