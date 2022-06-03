package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

public class Exercise19 {
    public static class LinkedListWithRemovableLastNode<T> implements Iterable<T> {
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

        public void deleteLastNode() {
            if (!isEmpty()) {
                if (size == 1) {
                    first = null;
                } else {
                    Node current = first;
                    for (int i = 0; i < size - 2; i++) {
                        current = current.next;
                    }
                    current.next = null;
                }
                size--;
            }
        }

        @Override
        public Iterator<T> iterator() {
            return new LinkedListWithRemovableLastNodeIterator();
        }

        private class LinkedListWithRemovableLastNodeIterator implements Iterator<T> {
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
        LinkedListWithRemovableLastNode<Integer> list = new LinkedListWithRemovableLastNode<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);

        StdOut.println("Before removing last node");

        StringJoiner listBeforeLastNodeDeletion = new StringJoiner(" ");
        for (int number : list) {
            listBeforeLastNodeDeletion.add(String.valueOf(number));
        }

        StdOut.println(listBeforeLastNodeDeletion.toString());
        StdOut.println("Expected: 0 1 2 3");

        list.deleteLastNode();

        StdOut.println("\nAfter removing last node");

        StringJoiner listAfterLastNodeDeletion = new StringJoiner(" ");
        for (int number : list) {
            listAfterLastNodeDeletion.add(String.valueOf(number));
        }

        StdOut.println(listAfterLastNodeDeletion.toString());
        StdOut.println("Expected 0 1 2");
    }
}
