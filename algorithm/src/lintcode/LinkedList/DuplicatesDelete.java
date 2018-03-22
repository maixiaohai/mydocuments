package lintcode.LinkedList;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/5
 * @Description  给定一个排序链表，删除所有重复的元素只留下原链表中没有重复的元素。
 *               给出 1->2->3->3->4->4->5->null，返回 1->2->5->null
 *               给出 1->1->1->2->3->null，返回 2->3->null
 */
public class DuplicatesDelete {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        ListNode res = deleteDuplicates(head);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
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

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode res = dummy;
        int tmp = head.val;
        head = head.next;
        boolean flag = true;
        while (head != null) {
            if (tmp != head.val) {
                if (flag) {
                    dummy.next = new ListNode(tmp);
                    dummy = dummy.next;
                }
                tmp = head.val;
                flag = true;
            } else {
                flag = false;
            }
            head = head.next;
        }
        if (flag) {
            dummy.next = new ListNode(tmp);
        }
        return res.next;
    }
}
