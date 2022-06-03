package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

public class Exercise26 {
    public static class LinkedListWithRemoveMatchingKeys<T> implements Iterable<T> {
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

                for (current = first; current.next != null; current = current.next) ;

                Node newNode = new Node();
                newNode.item = item;
                current.next = newNode;
            }

            size++;
        }

        public void remove(T key) {
            if (isEmpty() || key == null) {
                return;
            }

            while (first != null && first.item.equals(key)) {
                first = first.next;
                size--;
            }

            Node current;

            for (current = first; current != null; current = current.next) {
                Node next = current.next;

                while (next != null && next.item.equals(key)) {
                    next = next.next;
                    size--;
                }

                current.next = next;
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
        LinkedListWithRemoveMatchingKeys<String> linkedList = new LinkedListWithRemoveMatchingKeys<>();
        linkedList.add("Mark");
        linkedList.add("Bill");
        linkedList.add("Elon");
        linkedList.add("Rene");
        linkedList.add("Mark");
        linkedList.add("Mark");
        linkedList.add("Mark");
        linkedList.add("Elon");

        StdOut.println("Before removing Mark");

        StringJoiner listBeforeRemove = new StringJoiner(" ");
        for (String name : linkedList) {
            listBeforeRemove.add(name);
        }

        StdOut.println(listBeforeRemove.toString());
        StdOut.println("Expected: Mark Bill Elon Rene Mark Mark Mark Elon");

        String itemToBeRemoved = "Mark";
        linkedList.remove(itemToBeRemoved);

        StdOut.println("\nAfter removing Mark");

        StringJoiner listAfterRemove = new StringJoiner(" ");
        for (String name : linkedList) {
            listAfterRemove.add(name);
        }

        StdOut.println(listAfterRemove.toString());
        StdOut.println("Expected: Bill Elon Rene Elon");
    }
}
