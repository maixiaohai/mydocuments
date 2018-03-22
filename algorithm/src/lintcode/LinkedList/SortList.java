package lintcode.LinkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/4
 * @Description  在 O(n log n) 时间复杂度和常数级的空间复杂度下给链表排序。
 *               给出 1->3->2->null，给它排序变成 1->2->3->null.
 */

public class SortList {
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
        head.next = new ListNode(3);
        head.next.next = new ListNode(2);
        ListNode res = sortList(head);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mock = head;
        int count = 0;
        List<Integer> list = new ArrayList<Integer>();
        while (mock != null) {
            count++;
            list.add(mock.val);
            mock = mock.next;
        }
        quickSort(list, 0, count - 1);
        ListNode res = head;
        for (int i = 0; i < count; i++) {
            head.val = list.get(i);
            head = head.next;
        }
        return res;
    }

    private static void quickSort(List<Integer> list, int start, int end) {
        if (start >= end) {
            return;
        }
        int partition = partition(list, start, end);
        quickSort(list, start, partition - 1);
        quickSort(list, partition + 1, end);
    }

    private static int partition(List<Integer> list, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int partition = list.get(lo);
        while (true) {
            while (list.get(++i) < partition) {
                if (i == hi) {
                    break;
                }
            }
            while (list.get(--j) >= partition) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(list, i, j);
        }
        exch(list, lo, j);
        return j;
    }

    private static void exch(List<Integer> list, int i, int j) {
        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
