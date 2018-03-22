package lintcode.LinkedList;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/8
 * @Description  给定一个单链表L: L0→L1→…→Ln-1→Ln, 重新排列后为：L0→Ln→L1→Ln-1→L2→Ln-2→…
 *               必须在不改变节点值的情况下进行原地操作。
 *               给出链表 1->2->3->4->null，重新排列后为1->4->2->3->null。
 *               思路：先找到链表的中间节点，然后将后半部分提出来，把后半部分反转，
 *               然后将前半部分与反转了的后半部分合并起来，合并的时候注意交叉合并就可以了
 */
public class ReorderList {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int value) {
            this.val = value;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b  = new ListNode(2);
        a.next = b;
        ListNode c = new ListNode(3);
        b.next = c;
        c.next = new ListNode(4);
        reorderList(a);
        while (a != null) {
            System.out.println(a.val);
            a = a.next;
        }
    }

    public static void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode dummy = head;
        int count = 0;
        while (dummy != null) {
            count++;
            dummy = dummy.next;
        }
        dummy = head;
        int tmp = 1;
        ListNode mid = null;
        while (dummy != null) {
            if (tmp == (count / 2)) {
                mid = dummy;
                break;
            }
            dummy = dummy.next;
            tmp++;
        }
        ListNode tail = reverse(dummy.next);
        mid.next = null;
        merge(head, tail);
    }
    private static void printListNode(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "=>");
            node = node.next;
        }
        System.out.println();
    }

    private static void merge(ListNode head, ListNode tail) {
        int index = 0;
        ListNode dummy = new ListNode(0);
        while (tail != null) {
//            System.out.println("head");
//            printListNode(head);
//            System.out.println("dummy");
//            printListNode(dummy);
            if (index % 2 == 0) {
                dummy.next = head;
                head = head.next;
            } else {
                dummy.next = tail;
                tail = tail.next;
            }
            dummy = dummy.next;
            index++;
        }
        if (head != null) {
            dummy.next = head.next;
        }
    }

    private static ListNode reverse(ListNode list) {
        ListNode res = null;
        while (list != null) {
            ListNode tmp = list.next;
            list.next = res;
            res = list;
            list = tmp;
        }
        return res;
    }
}
