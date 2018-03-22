package lintcode.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/3/11
 * @Description 给定一个二叉树，找出所有路径中各节点相加总和等于给定目标值的路径。
 *              一个有效的路径，指的是从根节点到叶节点的路径。
 *              关键点在于stack的使用
 */
public class BinaryTreePathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode tmp = new TreeNode(1);
        root.left = tmp;
        root.right = new TreeNode(4);
        tmp = new TreeNode(1);
        root.left.left = tmp;
        root.left.right = new TreeNode(3);
        tmp = new TreeNode(2);
        root.left.left.left = tmp;
        List<List<Integer>> res = binaryTreePathSum(root, 5);
        for (int i = 0; i < res.size(); i++) {
            for (int j = 0; j < res.get(i).size(); j++) {
                System.out.print(res.get(i).get(j));
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
    public static List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }
        Stack<Integer> stack = new Stack<Integer>();
        binaryTreePathSum(res, stack, root, 0, target);
        return res;
    }

    private static void binaryTreePathSum(List<List<Integer>> result, Stack<Integer> stack,
                                   TreeNode node, int sum, int target) {
        sum += node.val;
        stack.push(node.val);
        if (sum == target && node.left == null && node.right == null) {
            List<Integer> list = new ArrayList<Integer>(stack); //very important
            result.add(list);
            stack.pop();
            System.out.println("finish one line : " + stack.size());
        } else {
            if (node.left != null) {
                System.out.println("left");
                binaryTreePathSum(result, stack, node.left, sum, target);
            }
            if (node.right != null) {
                System.out.println("right");
                binaryTreePathSum(result, stack, node.right, sum, target);
            }
            stack.pop(); //very important
        }
    }
}
