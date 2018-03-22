package lintcode.LinkedList;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/1
 * @Description  请写一个程序，找到两个单链表最开始的交叉节点。
 *               注意: 如果两个链表没有交叉，返回null。在返回结果后，两个链表仍须保持原有的结构。可假定整个链表结构中没有循环。
 */
public class IntersectionNode {
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
        l1.next.next.next = new ListNode(8);

        ListNode l2 = new ListNode(2);
        l2.next = l1.next.next;
        ListNode res = getIntersectionNode(l1, l2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode countListNodeA = headA;
        int countA = 1;
        while (countListNodeA.next != null) {
            countA++;
            countListNodeA = countListNodeA.next;
        }
        ListNode countListNodeB = headB;
        int countB = 1;
        while (countListNodeB.next != null) {
            countB++;
            countListNodeB = countListNodeB.next;
        }
        if (countListNodeA != countListNodeB) {
            System.out.println(countListNodeA.val);
            System.out.println(countListNodeB.val);
            System.out.println("could not be happend in this case");
            return null;
        }
        if (countA > countB) {
            for (int i = 0; i < (countA - countB); i++) {
                headA = headA.next;
            }
        } else {
            for (int i = 0; i < (countB - countA); i++) {
                headB = headB.next;
            }
        }
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }
}
