package chapter1.section3;

import java.util.Iterator;

public class Bag<T> implements Iterable<T> {
    private class Node {
        T item;
        Node next;
    }

    private Node first;
    private int size;

    public int size() {
        return size();
    }

    public void add(T item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;

        size++;
    }

    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }
}
