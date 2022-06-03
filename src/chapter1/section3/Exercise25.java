package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

public class Exercise25 {
    public static class LinkedListWithInsertAfter<T> implements Iterable<T> {
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

        public void insertAfter(Node target, Node newNode) {
            if (isEmpty() || target == null || newNode == null) {
                return;
            }

            Node current;

            for (current = first; current != null; current = current.next) {
                if (current.item.equals(target.item)) {
                    newNode.next = current.next;
                    current.next = newNode;
                    size++;
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
        LinkedListWithInsertAfter<Integer> linkedList = new LinkedListWithInsertAfter<>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);

        StdOut.println("Before inserting node 99 (after node 2)");

        StringJoiner listBeforeInsert = new StringJoiner(" ");
        for (int number : linkedList) {
            listBeforeInsert.add(String.valueOf(number));
        }

        StdOut.println(listBeforeInsert.toString());
        StdOut.println("Expected: 0 1 2 3 4");

        LinkedListWithInsertAfter<Integer>.Node nodeOfReference = linkedList.createNode(2);
        LinkedListWithInsertAfter<Integer>.Node nodeToBeInserted = linkedList.createNode(99);
        linkedList.insertAfter(nodeOfReference, nodeToBeInserted);

        StdOut.println("\nAfter inserting node 99 (after node 2)");

        StringJoiner listAfterInsert = new StringJoiner(" ");
        for (int number : linkedList) {
            listAfterInsert.add(String.valueOf(number));
        }

        StdOut.println(listAfterInsert.toString());
        StdOut.println("Expected: 0 1 2 99 3 4");
    }
}
