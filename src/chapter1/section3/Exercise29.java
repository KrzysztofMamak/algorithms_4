package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

public class Exercise29 {
    public static class CircularQueue<T> implements Iterable<T> {
        private class Node {
            T item;
            Node next;
        }

        Node last;
        int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void enqueue(T item) {
            Node node = new Node();
            node.item = item;

            if (isEmpty()) {
                last = node;
                last.next = last;
            } else {
                node.next = last.next;
                last.next = node;
                last = node;
            }

            size++;
        }

        public T dequeue() {
            if (isEmpty()) {
                return null;
            }

            T item;

            if (size == 1) {
                item = last.item;
                last = null;
            } else {
                item = last.next.item;
                last.next = last.next.next;
            }

            size--;
            return item;
        }

        @Override
        public Iterator<T> iterator() {
            return new CircularQueueIterator();
        }

        private class CircularQueueIterator implements Iterator<T> {
            private Node current;
            int count = 0;

            //            if (size == 0) it means that last == last.next equals true; maybe necessary
            public CircularQueueIterator() {
                if (last != null && size > 1) {
                    current = last.next;
                } else {
                    current = last;
                }
            }

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                count++;
                T item = current.item;
                current = current.next;

                return item;
            }
        }

        public static void main(String[] args) {
            CircularQueue<Integer> queue = new CircularQueue<>();
            queue.enqueue(1);
            queue.enqueue(2);
            queue.enqueue(3);
            queue.enqueue(4);

            StringJoiner queueItems = new StringJoiner(" ");
            for (int item : queue) {
                queueItems.add(String.valueOf(item));
            }

            StdOut.println("Queue items: " + queueItems.toString());
            StdOut.println("Expected: 1 2 3 4");
        }
    }
}
