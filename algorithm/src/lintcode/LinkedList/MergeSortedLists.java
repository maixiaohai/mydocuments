package lintcode.LinkedList;

import java.util.*;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/2/21
 * @Description 使用归并算法的实现，时间复杂度NKlogK
 * 相关题目：合并K个排序链表、合并两个排序链表、合并两个排序数组
 */
public class MergeSortedLists {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<Integer>();
    }

    ListNode removeNthFromEnd(ListNode head, int n) {
        // write your code here
        if (head == null || n == 0) {
            return head;
        }
        int count = 1;
        ListNode root = head;
        while (head.next != null) {
            head = head.next;
            count++;
        }
        ListNode res = root;
        if (count == n) {
            return root.next;
        } else {
            while ((count - 1) > n) {
                count--;
                root = root.next;
            }
            ListNode tmp = root.next.next;
            root.next = tmp;
        }
        return res;
    }

    ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode p1 = head;
        if(n<=0 || p1==null)
            return p1;
        // 加上头结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        head = dummy;
        ListNode slow = head.next;
        ListNode fast = head.next;
        while(n>=1){
            fast = fast.next;
            n--;
        }
        if(fast==null) // 说明删除的是第一个非头结点
            return head.next.next;
        while(fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next; // slow.next指向删除结点
        return head.next;
    }

    public  class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode l = null;
        if (l1 == null ) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val > l2.val) {
            l = l2;
            l.next = merge2Lists(l1, l2.next);
        } else {
            l = l1;
            l.next = merge2Lists(l1.next, l2);
        }
        return l;
    }

    public ListNode mergeKLists(List<ListNode> lists) {
        if (null == lists || lists.size() == 0) return null;
        if (lists.size() == 1) return lists.get(0);
        int size = lists.size() - 1;
        int mid = (size - 1) / 2;
        ListNode l1 = mergeKLists(lists.subList(0, mid + 1));
        ListNode l2 = mergeKLists(lists.subList(mid + 1, size));
        return merge2Lists(l1, l2);
    }

    // replace all Blank with %20,and return the new length
    public static int replaceBlank(char[] string, int length) {
        int numSpace = 0;
        for (int i = 0; i < length; i++) {
            if (string[i] == ' ') {
                numSpace++;
            }
        }
        int newLength = length + numSpace * 2;
        int index = newLength;
        for (int i = length - 1; i >= 0; i--) {
            if (string[i] == ' ') {
                string[--index] = '0';
                string[--index] = '2';
                string[--index] = '%';
            } else {
                string[--index] = string[i];
            }
        }
        return newLength;
    }

    public void mergeSortedArray(int[] a, int m, int[] b, int n) {
        for (int i = m; i < n; i++) {
            a[i] = b[i - m];
        }
        sort(a);
    }

    private void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && (a[j] < a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    private void exch(int[] a, int i, int j) {
        int tmp = a[j];
        a[j] = a[i];
        a[i] = tmp;
    }
}
