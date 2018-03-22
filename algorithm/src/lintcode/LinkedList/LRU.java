package lintcode.LinkedList;

import java.util.HashMap;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/6
 * @Description  为最近最少使用（LRU）缓存策略设计一个数据结构，它应该支持以下操作：获取数据（get）和写入数据（set）。
 *               获取数据get(key)：如果缓存中存在key，则获取其数据值（通常是正数），否则返回-1。
 *               写入数据set(key, value)：如果key还没有在缓存中，则写入其数据值。当缓存达到上限，
 *               它应该在写入新数据之前删除最近最少使用的数据用来腾出空闲位置
 *               ====================================================================================
 *               思路 HashMap + 双向链表 ,但是需要考虑很多corner case ，基本都是关于first 和 last 为null的情况,解决方法：
 *               在初始化时，给first和last各一个占位node
 */
public class LRU {
    private final int N;
    private HashMap<Integer, Node> map;
    private Node first = null;
    private Node last = null;
    private class Node {
        int key;
        int value;
        Node pre;
        Node next;

        public Node(int key, int val, Node pre, Node next) {
            this.key = key;
            this.value = val;
            this.pre = pre;
            this.next = next;
        }
    }

    // @param capacity, an integer
    public LRU(int capacity) {
        N = capacity;
        map = new HashMap<Integer, Node>();
        first = new Node(-1, -1, null, null);
        last = new Node(-1, -1, first, null);
        first.next = last;
    }

    // @return an integer
    public int get(int key) {
        if (map.containsKey(key)) {
            Node target = map.get(key);
            moveToTail(false, target);
            return target.value;
        }
        return -1;
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        if (map.containsKey(key)) { //节点存在
            Node target = map.get(key);
            target.value = value;
            map.put(key, target);
            moveToTail(false, target);
        } else { //节点不存在
            if (map.size() == N) { //节点满了
                map.remove(first.next.key);
                first.next.next.pre = first;
                first.next = first.next.next;
                Node target = new Node(key, value, null, null);
                moveToTail(true, target);
                map.put(key, target);
            } else { // 节点没满
                Node target = new Node(key, value, null, null);
                moveToTail(true, target);
                map.put(key, target);
            }
        }
    }

    private void moveToTail(boolean isNew, Node target) {
        if (target == last.pre) {
            return;
        }
        if (!isNew) {
            // 修改旧节点的双向链表指针
            target.pre.next = target.next;
            target.next.pre = target.pre;
        }
        // 添加节点到链表尾部
        last.pre.next = target;
        target.pre = last.pre;
        target.next = last;
        last.pre = target;
    }

    public static void main(String[] args) {
        //2, [set(2,1),set(1,1),get(2),set(4,1),get(1),get(2)]
        LRU lru = new LRU(2);
        System.out.println("set 2=>1");
        lru.set(2, 1);
        System.out.println("set 1=>1");
        lru.set(1, 1);
        System.out.println("get 2");
        System.out.println(lru.get(2));
        System.out.println("set 4=>1");
        lru.set(4, 1);
        System.out.println("get 1");
        System.out.println(lru.get(1));
        System.out.println("get 2");
        System.out.println(lru.get(2));
    }
}
