package DataStru;

import java.util.Iterator;

/**
 * Created by maixiaohai on 17/2/3.
 */
public class Queue<Item> implements Iterable<Item> {
    private class Node {
        private Item item;
        private Node next;
    }
    private int N = 0;
    private Node root;
    private Node current;

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void enqueue(Item item) {
        Node oldcurrent = current;
        current = new Node();
        current.item = item;
        current.next = null;
        N++;
        if (root == null) {
            root = current;
        } else {
            oldcurrent.next = current;
        }
    }

    public Item dequeue() {
        Item item = root.item;
        root = root.next;
        if (isEmpty()) {
            root = null;
            current = null;
            return null;
        }
        N--;
        return item;
    }

    public Iterator<Item>  iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node current = root;
        public boolean hasNext() {return current.next != null;}
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {}
    }
}
