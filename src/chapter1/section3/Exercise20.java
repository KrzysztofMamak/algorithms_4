package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

public class Exercise20 {
    public static class LinkedListWithByIndexRemoval<T> implements Iterable<T> {
        private class Node {
            T item;
            Node next;
        }

        private int size;
        private Node first;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void add(T item) {
            if (isEmpty()) {
                first = new Node();
                first.item = item;
            } else {
                Node current;
                for (current = first; current.next != null; current = current.next);

                Node newNode = new Node();
                newNode.item = item;
                current.next = newNode;
            }
            size++;
        }

        public void removeByIndex(int k) {
            if (k > size || isEmpty()) {
                return;
            }

            if (k == 1) {
                first = null;
            } else {
                Node current = first;

                for (int i = 1; i < (k - 1); i++) {
                    current = current.next;
                }
//                Or
//                int count = 1;
//
//                for (current = first; current != null; current = current.next) {
//                    if (count == k - 1 && current.next != null) {
//                        current.next = current.next.next;
//                        break;
//                    }
//                    count++;
//                }

                current.next = current.next.next;
            }

            size--;
        }

        @Override
        public Iterator<T> iterator() {
            return new LinkedListWithByIndexRemovalIterator();
        }

        private class LinkedListWithByIndexRemovalIterator implements Iterator<T> {
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
        LinkedListWithByIndexRemoval<Integer> list = new LinkedListWithByIndexRemoval<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);

        StdOut.println("Before removing second node");

        StringJoiner listBeforeRemoval = new StringJoiner(" ");
        for (int number : list) {
            listBeforeRemoval.add(String.valueOf(number));
        }

        StdOut.println(listBeforeRemoval.toString());
        StdOut.println("Expected: 0 1 2 3");

        list.removeByIndex(2);

        StdOut.println("\nAfter removing second node");
        StringJoiner listAfterRemoval = new StringJoiner(" ");
        for (int number : list) {
            listAfterRemoval.add(String.valueOf(number));
        }

        StdOut.println(listAfterRemoval.toString());
        StdOut.println("Expected: 0 2 3");
    }
}
