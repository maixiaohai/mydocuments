package lintcode.LinkedList;

import java.util.Stack;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/5
 * @Description  设计一种方式检查一个链表是否为回文链表。1->2->1 就是一个回文链表。
 *               挑战: O(n)的时间和O(1)的额外空间。
 */
public class PalindromeList {
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
        //head.next.next = new ListNode(1);
        if (isPalindrome(head)) {
            System.out.println("is palindrome");
        } else {
            System.out.println("is not palindrome");
        }
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        Stack<Integer> stack = new Stack<Integer>();
        ListNode mock = head;
        while (mock != null) {
            stack.push(mock.val);
            mock = mock.next;
        }
        while (head != null) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }
}
