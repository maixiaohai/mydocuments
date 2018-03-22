package lintcode.LinkedList;

import java.util.Stack;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/5
 * @Description  翻转链表中第m个节点到第n个节点的部分 注意事项 ：m，n满足1 ≤ m ≤ n ≤ 链表长度
 *               给出链表1->2->3->4->5->null， m = 2 和n = 4，返回1->4->3->2->5->null
 */
public class ReverseListNode2 {
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
        head.next.next.next.next = new ListNode(5);
        int m = 2;
        int n = 4;
        ListNode res = reverse(head, m, n);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode reverse(ListNode head, int start, int end) {
        if (head == null || head.next == null) {
            return head;
        }
        int count = 1;
        Stack<Integer> stack = new Stack<Integer>();
        ListNode dummy = head;
        while (dummy != null) {
            if (count >= start && count <= end) {
                stack.push(dummy.val);
            }
            count++;
            dummy = dummy.next;
        }
        count = 1;
        ListNode res = head;
        while (head != null) {
            if (count >= start && count <= end) {
                head.val = stack.pop();
            }
            count++;
            head = head.next;
        }
        return res;
    }
}
