package lintcode.LinkedList;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/23
 * @Description 翻转链表
 */
public class Reverse {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b  = new ListNode(2);
        a.next = b;
        ListNode c = new ListNode(3);
        b.next = c;
        showListNode(a);
        ListNode resNode = reverse(a);
        showListNode(a);
        showListNode(resNode);
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

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int value) {
            this.val = value;
            this.next = null;
        }
    }

    public static ListNode reverse(ListNode head) {
        //循环方法
//        ListNode pre = null;
//        while (head != null) {
//            ListNode tmp = head.next;
//            head.next = pre;
//            pre = head;
//            head = tmp;
//        }
//        return pre;
        //递归方法 1->2->3
        if (head.next == null) {
            return head;
        }
        ListNode res = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return res;
    }
}
