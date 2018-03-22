package lintcode.LinkedList;

import java.util.Stack;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/31
 * @Description  假定用一个链表表示两个数，其中每个节点仅包含一个数字。假设这两个数的数字顺序排列，请设计一种方法将两个数相加，并将其结果表现为链表的形式
 *               给出 6->1->7 + 2->9->5。即，617 + 295。返回 9->1->2。即，912 。
 */
public class ListAdd {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(6);
        l1.next = new ListNode(1);
        l1.next.next = new ListNode(7);
        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(5);
        ListNode res = addLists2(l1, l2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode addLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode res = new ListNode(0);
        Stack<Integer> stack1 = getStackFromListNode(l1);
        Stack<Integer> stack2 = getStackFromListNode(l2);
        int flag = 0; // 进位
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int val = stack1.pop() + stack2.pop() + flag;
            flag = val / 10;
            val = val % 10;
            ListNode current = new ListNode(val);
            // 当前节点的next 指向之前的一个节点
            current.next = res.next;
            // 将当前节点存储起来
            res.next = current;
        }
        while (!stack1.isEmpty()) {
            int val = stack1.pop() + flag;
            flag = val / 10;
            val = val % 10;

            ListNode current = new ListNode(val);
            current.next = res.next;
            res.next = current;
        }
        while (!stack2.isEmpty()) {
            int val = stack2.pop() + flag;
            flag = val / 10;
            val = val % 10;

            ListNode current = new ListNode(val);
            current.next = res.next;
            res.next = current;
        }
        if (flag == 1) {
            ListNode head = new ListNode(1);
            head.next = res.next;
            res.next = head;
        }
        return res.next;
    }

    private static Stack<Integer> getStackFromListNode(ListNode l) {
        Stack<Integer> stack = new Stack<Integer>();
        while (l != null) {
            stack.push(l.val);
            l = l.next;
        }
        return stack;
    }
}
