package lintcode.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/22
 * @Description  给一棵二叉树，找出从根节点到叶子节点的所有路径。
 */
public class BinaryTreePaths {

    public static class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> res= new ArrayList<String>();
        if (root == null) {
            return res;
        }
        StringBuilder sb = new StringBuilder();
        search(res, root, sb);
        return res;
    }

    private static void search(List<String> res, TreeNode node, StringBuilder sb) {
        sb = new StringBuilder(sb);
        sb.append(node.val);
        if (node.left == null && node.right == null) {
            res.add(sb.toString());
        } else {
            sb.append("->");
            if (node.left != null) {
                search(res, node.left, sb);
            }
            if (node.right != null) {
                search(res, node.right, sb);
            }
        }
    }
}
