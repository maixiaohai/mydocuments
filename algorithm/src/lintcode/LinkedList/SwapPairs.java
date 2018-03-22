package lintcode.LinkedList;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/23
 * @Description 两两交换链表中的节点，最好原地交换，而且不是简单的值的交换
 */
public class SwapPairs {
    public static class ListNode {
        int val;
        ListNode next;
        public ListNode(int value) {
            this.val = value;
            this.next = null;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pivot = dummy.next;
        ListNode tail = dummy;
        while (pivot != null) {
            if (pivot.next == null) {
                break;
            }
            ListNode tmp = pivot.next;
            pivot.next = pivot.next.next;
            tmp.next = null;
            tmp.next = pivot;
            tail.next = tmp;
            tail = tail.next.next;
            pivot = pivot.next;
        }
        return dummy.next;
    }
}
