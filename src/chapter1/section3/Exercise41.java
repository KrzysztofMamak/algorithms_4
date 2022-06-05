package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

public class Exercise41 {
    public static class Queue<T> implements Iterable<T> {
        private class Node {
            T item;
            Node next;
        }

        Queue() {}

        Queue(Queue<T> q) {
            if (q == null) {
                throw new IllegalArgumentException("Queue must not be null");
            }

            for (T item : q) {
                enqueue(item);
            }
        }

        int size;
        Node first;

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void enqueue(T item) {
            if (item == null) {
                return;
            }

            Node newNode = new Node();
            newNode.item = item;

            if (isEmpty()) {
                first = newNode;
            } else {
                Node current = first;

                while (current.next != null) {
                    current = current.next;
                }

                current.next = newNode;
            }

            size++;
        }

        public T dequeue() {
            if (isEmpty()) {
                return null;
            }

            T item = first.item;
            first = first.next;

            return item;
        }

        @Override
        public Iterator<T> iterator() {
            return new QueueIterator();
        }

        private class QueueIterator implements Iterator<T> {
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
        Queue<Integer> originalQueue = new Queue<>();
        originalQueue.enqueue(1);
        originalQueue.enqueue(2);
        originalQueue.enqueue(3);
        originalQueue.enqueue(4);

        Queue<Integer> queueCopy = new Queue<>(originalQueue);
        queueCopy.enqueue(5);
        queueCopy.enqueue(99);

        originalQueue.dequeue();

        queueCopy.dequeue();
        queueCopy.dequeue();

        StringJoiner originalQueueItems = new StringJoiner(" ");
        for (int item : originalQueue) {
            originalQueueItems.add(String.valueOf(item));
        }

        StdOut.println("Original Queue: " + originalQueueItems.toString());
        StdOut.println("Expected: 2 3 4");

        StdOut.println();

        StringJoiner copyQueueItems = new StringJoiner(" ");
        for (int item : queueCopy) {
            copyQueueItems.add(String.valueOf(item));
        }

        StdOut.println("Queue Copy: " + copyQueueItems.toString());
        StdOut.println("Expected: 3 4 5 99");
    }
}
