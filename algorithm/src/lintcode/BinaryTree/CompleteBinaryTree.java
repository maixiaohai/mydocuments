package lintcode.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/22
 * @Description  Check a binary tree is completed or not.
 *               A complete binary tree is not binary tree that every level is
 *               completed filled except the deepest level.
 *               In the deepest level, all nodes must be as left as possible.
 */
public class CompleteBinaryTree {
    public class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    boolean isCompleteTreeNode(TreeNode root){
        if(root == null) {
            return false;
        }
        boolean flag = false;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                } else {
                    flag = true;
                }
                if (cur.left != null && flag) {
                    return false;
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                } else {
                    flag = true;
                }
                if (cur.right != null && flag) {
                    return false;
                }
            }
        }
        return true;
    }
}
