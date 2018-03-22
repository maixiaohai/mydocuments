package lintcode.BinaryTree;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/17
 * @Description  给出一棵二叉树，寻找一条路径使其路径和最大，路径可以在任一节点中开始和结束（路径和为两个节点之间所在路径上的节点权值之和)
 *               从单个节点出发考虑，有四种情况 1.往左走2.往右走3.不走4.从左往右走 但当考虑到有父节点时，第4种情况不会发生，所以需要计算
 *               一个当前节点内的最大值，以及加上父节点的最大值
 */


public class maxPathSum {
    // Definition of TreeNode:
    public class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }

    private int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = helper(node.left);
        int right = helper(node.right);
        //连接父节点的最大路径是1、2、3这三种情况的最大值
        int withParents = Math.max(Math.max(node.val + left, node.val + right), node.val);
        //当前节点的最大路径是一、二、三、四这四种情况的最大值
        int curNode = Math.max(withParents, node.val + left + right);
        max = Math.max(curNode, max);
        return withParents;
    }
}
