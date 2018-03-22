package lintcode.BinaryTree;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/18
 * @Description 1 .根据中序遍历和后序遍历树构造二叉树 2. 根据前序遍历和中序遍历构造二叉树
 */
public class BuildTree {
    public static void main(String[] args) {
        int[] inorder = new int[]{1,3,2,5,4};
        int[] postorder = new int[]{1,3,2,4,5};
        TreeNode res = solution1(inorder, postorder);
        System.out.println(res.val);
        System.out.println(res.left.val);
        System.out.println(res.right.val);
        System.out.println(res.left.left.val);
        System.out.println(res.left.left.left.val);
    }

    // Definition of TreeNode:
    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public static TreeNode solution1(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0) {
            return null;
        }
        int length = inorder.length;
        return createTree1(inorder, postorder, 0, inorder.length - 1, 0, inorder.length - 1);
    }

    private static TreeNode createTree1(int[] inorder, int[] postorder, int instart,
                                int inend, int poststart, int postend) {
        if (instart > inend || poststart > postend) {
            return null;
        }
        //System.out.println("postend : " + postend);
        TreeNode node = new TreeNode(postorder[postend]);
        int pos = -1;
        for (int i = instart; i <= inend; i++) {
            if (inorder[i] == postorder[postend]) {
                pos = i;
                break;
            }
        }
        //System.out.println("pos : " + pos);
        node.left = createTree1(inorder, postorder, instart, pos - 1, poststart, postend - inend + pos - 1);
        node.right = createTree1(inorder, postorder, pos + 1, inend, postend - (inend - pos), postend - 1);
        return node;
    }
}
