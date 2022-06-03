package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

public class Exercise24 {
    public static class LinkedListWithRemoveAfter<T> implements Iterable<T> {
        private class Node {
            T item;
            Node next;
        }

        public Node createNode(T item) {
            Node node = new Node();
            node.item = item;
            return node;
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

        public void removeAfter(Node node) {
            if (isEmpty() || node == null || node.next == null) {
                return;
            }

            // node.next = node.next.next; size--; return;

            Node current;

            for (current = first; current != null; current = current.next) {
                if (current.item.equals(node.item)) {
                    if (current.next != null) { // maybe remove
                        size--;
                    }
                    break;
                }
            }
        }

        @Override
        public Iterator<T> iterator() {
            return new LinkedListWithRemoveAfterIterator();
        }

        private class LinkedListWithRemoveAfterIterator implements Iterator<T> {
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
        LinkedListWithRemoveAfter<Integer> list = new LinkedListWithRemoveAfter<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        StdOut.println("Before removing node after node 0");

        StringJoiner listBeforeRemove = new StringJoiner(" ");
        for (int number : list) {
            listBeforeRemove.add(String.valueOf(number));
        }

        StdOut.println(listBeforeRemove.toString());
        StdOut.println("Expected: 0 1 2 3 4");

        LinkedListWithRemoveAfter<Integer>.Node nodeToBeDeleted = list.createNode(0);
        list.removeAfter(nodeToBeDeleted);

        StdOut.println("\nAfter removing node after node 0");

        StringJoiner listAfterRemove = new StringJoiner(" ");
        for (int number : list) {
            listAfterRemove.add(String.valueOf(number));
        }

        StdOut.println(listAfterRemove.toString());
        StdOut.println("Expected: 0 2 3 4");
    }
}
