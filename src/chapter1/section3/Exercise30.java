package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

public class Exercise30 {
    public static class LinkedList<T> implements Iterable<T> {
        public class Node {
            T item;
            Node next;
        }

        Node first;
        int size = 0;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void add(T item) {
            Node newNode = new Node();
            newNode.item = item;

            if (isEmpty()) {
                first = newNode;
            } else {
                Node current;

                for (current = first; current.next != null; current = current.next);

                current.next = newNode;
            }

            size++;
        }

        public Node reverseIterated1() {
            Node reverse = null;

            while (first != null) {
                Node second = first.next;
                first.next = reverse;
                reverse = first;
                first = second;
            }

            first = reverse;

            return reverse;
        }

        public Node reverseIterated2() {
            if (isEmpty()) {
                return null;
            }

            if (size == 1) {
                return first;
            }

            Node old = first;
            Node current = first.next;
            Node newNode = first.next.next;

            first.next = null;
            first = current;
            current.next = old;

            while (newNode != null) {
                old = current;
                current = newNode;

                newNode = newNode.next;

                current.next = old;
                first = current;
            }

            return first;
        }

        public Node reverseRecursive() {
            first = reverseRecursiveImpl(first);
            return first;
        }

        private Node reverseRecursiveImpl(Node first) {
            if (first == null) {
                return null;
            }

            if (first.next == null) {
                return first;
            }

            Node second = first.next;
            Node rest = reverseRecursiveImpl(second);
            second.next = first;
            first.next = null;

            return rest;
        }

        @Override
        public Iterator<T> iterator() {
            return new LinkedListIterator();
        }

        private class LinkedListIterator implements Iterator<T> {
            Node current = first;

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

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);

        linkedList.reverseIterated1();

        StringJoiner listItems = new StringJoiner(" ");
        for (int item : linkedList) {
            listItems.add(String.valueOf(item));
        }

        StdOut.println("Reverse list items: " + listItems.toString());
        StdOut.println("Expected: 4 3 2 1");
    }
}
