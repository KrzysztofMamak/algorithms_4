package chapter1.section3;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    public class Node {
        public T item;
        public Node next;

        public Node(T item) {
            this.item = item;
        }
    }

    private int size;
    private Node first;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Node getFirstNode() {
        return first;
    }

    public T get(int index) {
        if (isEmpty()) {
            return null;
        }

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index must be between 0 and " + (size - 1));
        }

        Node current = first;
        int currentIndex = 0;

        while (currentIndex < index) {
            currentIndex++;
            current = current.next;
        }

        return current.item;
    }

    public void insert(T item) {
        if (first == null) {
            first = new Node(item);
        } else {
            Node current = first;

            while (current.next != null) {
                current = current.next;
            }

            current.next = new Node(item);
        }

        size++;
    }

    public void remove(int index) {
        if (isEmpty()) {
            return;
        }

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index must be between 0 and " + (size - 1));
        }

        if (index == 0) {
            first = first.next;
        } else {
            Node current = first;
            int currentIndex = 0;

            while (currentIndex < index - 1) {
                currentIndex++;
                current = current.next;
            }

            current.next = current.next.next;
        }

        size--;
    }

    public void remove(T item) {
        if (isEmpty()) {
            return;
        }

        if (item.equals(first.item)) {
            first = first.next;
            size--;
        } else {
            Node current = first;

            while (current.next != null && !current.next.item.equals(item)) {
                current = current.next;
            }

            if (current.next != null) {
                current.next = current.next.next;
                size--;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        Node currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            T item = currentNode.item;
            currentNode = currentNode.next;

            return item;
        }
    }
}
