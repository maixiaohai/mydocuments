package DataStru;

import java.util.Iterator;

/**
 * Created by maixiaohai on 17/2/3.
 */
public class Bag<Item> implements Iterable<Item> {
    private int N = 0;
    private class Node {
        private Item item;
        private Node next;
    }
    private Node current;

    public void add(Item item) {
        Node oldcurrent = current;
        current = new Node();
        current.item = item;
        current.next = oldcurrent;
        N++;
    }

    public int size() {return N;}
    public boolean isEmpty() {return N == 0;}

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node current = this.current;
        public boolean hasNext() {return current.next != null;}
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {}
    }
}
