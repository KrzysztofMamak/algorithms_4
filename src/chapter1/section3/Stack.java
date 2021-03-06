package chapter1.section3;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
    private class Node {
        T item;
        Node next;
    }

    private Node first;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first;

        first = newNode;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow"); // Underflow???
        }

        T item = first.item;
        first = first.next;
        size--;

        return item;
    }

    public T peek() {
       if (isEmpty()) {
           return null;
       }

       return first.item;
    }

    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {
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
