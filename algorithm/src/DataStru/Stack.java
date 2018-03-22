package DataStru;

import java.util.Iterator;

/**
 * Created by maixiaohai on 17/2/2.
 */
public class Stack<Item> implements Iterable<Item> {
    private class Node {
        private Item item;
        private Node next;
    }
    private Node current;
    private int N;

    public void push(Item item) {
        Node oldcurrent = current;
        current = new Node();
        current.item = item;
        current.next = oldcurrent;
        N++;
    }

    public Item pop() {
        Item item = current.item;
        current = current.next;
        N--;
        return item;
    }

    public int size() {return N;}

    public boolean isEmpty() {return N == 0;}

    public Iterator<Item> iterator() {
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator<Item> {
        private Node current = this.current;
        public boolean hasNext() {return current.next != null;}
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {}
    }
}
