package lintcode.LinkedList;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/3/2
 * @Description
 */
public class ReverseListNode {
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b  = new ListNode(2);
        a.next = b;
        ListNode c = new ListNode(3);
        b.next = c;
        reverse(a);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private static void showListNode(ListNode head) {
        if (head == null) {
            System.out.println("null listnode");
            return;
        }
        for (ListNode x = head; x != null; x = x.next) {
            System.out.print(x.val + " ");
        }
        System.out.println();
    }
    public static ListNode reverse(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode p = head;
        ListNode next = null;
        ListNode pre = null;
        while (p != null) {
            next  = p.next;
            p.next = pre;
            pre = p; //翻转以后的链表
            showListNode(pre);
            p = next; // 指向原始链表的下一个
        }
        return pre;
    }
}
