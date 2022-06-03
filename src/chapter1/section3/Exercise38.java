package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

public class Exercise38 {
    public static class ArrayGeneralizedQueue<T> implements Iterable<T> {
        int size;
        T[] array;

        @SuppressWarnings("unchecked")
        ArrayGeneralizedQueue() {
            size = 0;
            array = (T[]) new Object[1];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void insert(T item) {
            if (size == array.length) {
                resize(size * 2);
            }

            array[size++] = item;
        }

        public T delete(int k) {
            if (isEmpty() || k > size || k < 0) {
                return null;
            }

            T item = array[k];

            size--;

            moveItems(k);

            if (size == array.length / 4) {
                resize(size / 2);
            }

            return item;
        }

        private void moveItems(int index) {
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
        }

        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            T[] arrayCopy = (T[]) new Object[newSize];

            if (size >= 0) System.arraycopy(array, 0, arrayCopy, 0, size);

            array = arrayCopy;
        }

        @Override
        public Iterator<T> iterator() {
            return new GeneralizedQueueIterator();
        }

        private class GeneralizedQueueIterator implements Iterator<T> {
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
               return array[currentIndex++];
            }
        }
    }

    public static class LinkedListGeneralizedQueue<T> implements Iterable<T> {
        private class Node {
            T item;
            Node next;
            Node previous;
        }

        int size;
        Node first;
        Node last;

        LinkedListGeneralizedQueue() {
            size = 0;
            first = null;
            last = null;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void insert(T item) {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.previous = oldLast;

            if (last.previous != null) {
                last.previous.next = last;
            } else {
                first = last;
            }

            size++;
        }

        public T delete(int k) {
            if (isEmpty() || k > size - 1 || k < 0) {
                return null;
            }

            if (size == 1) {
                T item = first.item;
                first = null;
                last = null;
                size--;
                return item;
            }

            Node current;
            boolean startFromBeginning = k < (size / 2);
            int count = startFromBeginning ? 0 : size - 1;

            if (startFromBeginning) {
                for (current = first; count < k; current = current.next) {
                    count++;
                }
            } else {
                for (current = last; count > k; current = current.previous) {
                    count--;
                }
            }

            T item = current.item;

            if (current.previous != null) {
                current.previous.next = current.next;
            } else {
                first = current.next;
            }

            if (current.next != null) {
                current.next.previous = current.previous;
            } else {
                last = current.previous;
            }

            size--;

            return item;
        }

        @Override
        public Iterator<T> iterator() {
            return new GeneralizedQueueIterator();
        }

        private class GeneralizedQueueIterator implements Iterator<T> {
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
        ArrayGeneralizedQueue<Integer> arrayGeneralizedQueue = new ArrayGeneralizedQueue<>();
        arrayGeneralizedQueue.insert(0);
        arrayGeneralizedQueue.insert(1);
        arrayGeneralizedQueue.insert(2);
        arrayGeneralizedQueue.insert(3);
        arrayGeneralizedQueue.insert(4);

        arrayGeneralizedQueue.delete(1);
        arrayGeneralizedQueue.delete(3);

        StringJoiner arrayGeneralizedQueueItems = new StringJoiner(" ");
        for (int item : arrayGeneralizedQueue) {
            arrayGeneralizedQueueItems.add(String.valueOf(item));
        }

        StdOut.println("Generalized queue items: " + arrayGeneralizedQueueItems.toString());
        StdOut.println("Expected: 0 2 3");

        LinkedListGeneralizedQueue<Integer> linkedListGeneralizedQueue = new LinkedListGeneralizedQueue<>();
        linkedListGeneralizedQueue.insert(0);
        linkedListGeneralizedQueue.insert(1);
        linkedListGeneralizedQueue.insert(2);
        linkedListGeneralizedQueue.insert(3);
        linkedListGeneralizedQueue.insert(4);

        linkedListGeneralizedQueue.delete(0);
        linkedListGeneralizedQueue.delete(3);
        linkedListGeneralizedQueue.insert(99);

        StringJoiner linkedListGeneralizedQueueItems = new StringJoiner(" ");
        for (int item : linkedListGeneralizedQueue) {
            linkedListGeneralizedQueueItems.add(String.valueOf(item));
        }

        StdOut.println("Generalized queue items: " + linkedListGeneralizedQueueItems.toString());
        StdOut.println("Expected: 1 2 3 99");
    }
}
