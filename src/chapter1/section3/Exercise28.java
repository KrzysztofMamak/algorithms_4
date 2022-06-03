package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Exercise28 {
    public static class LinkedList<T> implements Iterable<T> {
        class Node {
            Node next;
            T item;
        }

        Node createNode(T item) {
            Node node = new Node();
            node.item = item;
            return node;
        }

        private int size = 0;
        private Node first;

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

                for (current = first; current.next != null; current = current.next) ;

                current.next = newNode;
            }

            size++;
        }

        public void remove(T item) {
            if (isEmpty() || item == null) {
                return;
            }

            while (first != null && first.item.equals(item)) {
                first = first.next;
                size--;
            }

            Node current;

            for (current = first; current != null; current = current.next) {
                Node next = current.next;

                while (next != null && next.item.equals(item)) {
                    next = next.next;
                    size--;
                }

                current.next = next;
            }
        }

        public Integer max() {
            if (isEmpty()) {
                return null;
            }

            int currentMaxValue = (Integer) first.item;

            return getMax(first.next, currentMaxValue);
        }

        private int getMax(Node node, int currentMaxValue) {
            if (node == null) {
                return currentMaxValue;
            }

            int currentValue = (Integer) node.item;

            if (currentValue > currentMaxValue) {
                currentMaxValue = currentValue;
            }

            return getMax(node.next, currentMaxValue);
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
        linkedList.add(3);
        linkedList.add(91);
        linkedList.add(2);
        linkedList.add(9);

        int maxValue = linkedList.max();
        StdOut.println("Max value: " + maxValue);
        StdOut.println("Expected: 91");
    }
}