package chapter1.section3;

import java.util.Iterator;

public class Queue<T> implements Iterable<T> {
    private class Node {
        T item;
        Node next;
    }

    private Node first;
    private Node last;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        Node oldLast = last;

        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }

        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow"); // underflow?
        }

        T item = first.item;
        first = first.next;

        size--;

        if (isEmpty()) {
            last = null;
        }

        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow"); // underflow?
        }

        return first.item;
    }

    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }
}
