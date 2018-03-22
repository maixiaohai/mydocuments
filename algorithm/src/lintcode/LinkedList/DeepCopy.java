package lintcode.LinkedList;

import java.util.HashMap;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/4/9
 * @Description 深度copy一个带有随机指针的链表
 *              重点：(1)创建一个HASHMAP，存储随机节点，空间O(n)；
 *                   (2)两次循环，第一次放旧节点与新节点的对应关系，同时生成next链；
 *                      第二次取对应关系，同时生成random链
 */
public class DeepCopy {
    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        HashMap<RandomListNode, RandomListNode> hashMap = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode dummy = new RandomListNode(0);
        dummy.next = head;
        RandomListNode dummyNew = new RandomListNode(0);
        RandomListNode nodeNew = dummyNew;
        while (head != null) {
            RandomListNode tmp = new RandomListNode(head.label);
            nodeNew.next = tmp;
            hashMap.put(head, tmp);
            nodeNew = nodeNew.next;
            head = head.next;
        }
        head = dummy.next;
        nodeNew = dummyNew.next;
        while (head != null) {
            RandomListNode tmp = hashMap.get(head.random);
            nodeNew.random = tmp;
            nodeNew = nodeNew.next;
            head = head.next;
        }
        return dummyNew.next;
    }
}
