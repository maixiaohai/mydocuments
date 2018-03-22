package lintcode.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/15
 * @Description 给出一棵二叉树，层次遍历其节点值（逐层从左往右打印）
 */
public class LevelOrder {
    //{3,9,20,#,#,15,7} => [3],[9,20],[15,7]
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        TreeNode left = new TreeNode(15);
        TreeNode right = new TreeNode(7);
        root.right.left = left;
        root.right.right = right;
        levelOrder(root);
    }

    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public static void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i != (size - 1)) {
                    System.out.print(node.val + ",");
                } else {
                    System.out.print(node.val);
                }
            }
            System.out.println();
        }
    }
}
