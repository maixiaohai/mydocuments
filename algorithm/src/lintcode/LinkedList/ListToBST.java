package lintcode.LinkedList;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/1
 * @Description 给出一个所有元素以升序排序的单链表，将它转换成一棵高度平衡的二分查找树
 */
public class ListToBST {
    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        TreeNode res = sortedListToBST(head);
    }

    //另一种方法是把链表存在ArrayList里，进行递归
    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        int count = 0;
        ListNode mockHead = head;
        while (mockHead != null) {
            count++;
            mockHead = mockHead.next;
        }
        if (count == 1) {
            return new TreeNode(head.val);
        } else if (count == 2) {
            TreeNode res = new TreeNode(head.val);
            res.right = new TreeNode(head.next.val);
            return res;
        } else {
            int mid = count / 2;
            mockHead = head;
            int tmp = 0;
            while (tmp < mid) {
                mockHead = mockHead.next;
                tmp++;
            }
            TreeNode resNode = new TreeNode(mockHead.val);
            ListNode left = head;
            while (left.next != mockHead) {
                left = left.next;
            }
            left.next = null;
            resNode.left = sortedListToBST(head);
            resNode.right = sortedListToBST(mockHead.next);
            return resNode;
        }
    }
}
