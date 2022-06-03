package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Exercise27 {
    // TODO -> check <T extends Comparable>
    public static class LinkedList<T extends Comparable<T>> implements Iterable<T> {
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

        //        public T max() {
        public Integer max() {
            if (isEmpty()) {
                return null;
            }

            if (size == 1) {
                return (Integer) first.item;
            }

//            T maxValue = first.item;
//
//            for (T item : this) {
//                if (maxValue == null || maxValue < item) {
//                    maxValue = item;
//                }
//            }

            int maxValue = (Integer) first.item;

            Node current;

            for (current = first.next; current != null; current = current.next) {
                int currentValue = (Integer) current.item;

                if (currentValue > maxValue) {
                    maxValue = currentValue;
                }
            }

            return maxValue;
        }

        //        TODO -> verify :)))
        @Override
        public Iterator<T> iterator() {
            if (cachedIterator != null) {
                return cachedIterator;
            }
            cachedIterator = new LinkedListIterator();
            return cachedIterator;
        }

        private LinkedListIterator cachedIterator;

        private class LinkedListIterator implements Iterator<T> {
            Node current = first;

            @Override
            public boolean hasNext() {
                final boolean result = current != null;
                current = first;
                return result;
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
