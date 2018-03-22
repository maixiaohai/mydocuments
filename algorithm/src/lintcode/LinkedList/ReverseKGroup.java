package lintcode.LinkedList;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/11/22
 * @Description 给定一个链表以及一个k,将这个链表从头指针开始每k个翻转一下。链表元素个数不是k的倍数，最后剩余的不用翻转。
 *              思路：定义一个thead头指针节点，每次连接翻转后的链表，然后再指到链表结尾。
 */
public class ReverseKGroup {
    public static void main(String[] args) {
        //给出链表 1->2->3->4->5 || k = 2, 返回 2->1->4->3->5 || k = 3, 返回 3->2->1->4->5
        ListNode head = new ListNode(1);
        ListNode b = new ListNode(2);
        head.next = b;
        ListNode c = new ListNode(3);
        b.next = c;
        ListNode d = new ListNode(4);
        c.next = d;
        d.next = new ListNode(5);
        ListNode res = reverseKGroup(head, 2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int value) {
            this.val = value;
            this.next = null;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode theadNode = new ListNode(0);  // theadNode存放每一段的开头
        theadNode.next = head;
        ListNode resNode = theadNode;  // resNode存放最终结果
        ListNode curNode = head; // 循环使用，head用来存放翻转
        int count = 0;
        while (curNode != null) {
            count++;
            if (count == k) {
                ListNode tmp = curNode;
                curNode = curNode.next;
                tmp.next = null; //切断head，也就是threadNode的后续
                head = reverse(head); //翻转

                //每次先把当前theadNode.next->head，然后
                theadNode.next = head;
                while (theadNode.next != null) {
                    theadNode = theadNode.next;
                }
                theadNode.next = curNode;
                //重新定义头节点
                head = curNode;
                count = 0;
                continue;
            }
            curNode = curNode.next;
        }
        return resNode.next;
    }

    private static ListNode reverse(ListNode head) {
        ListNode res = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = res;
            res = head;
            head = tmp;
        }
        return res;
    }
}
