package chapter1.section3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringJoiner;

public class Exercise40 {
    public static class Queue<T> implements Iterable<T> {
        private class Node {
            T item;
            Node next;
        }

        Queue() {
            uniqueItemsSet = new HashSet<>();
        }

        Node first;
        int size;
        Set<T> uniqueItemsSet;

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void enqueue(T item) {
            if (uniqueItemsSet.contains(item)) {
                remove(item);
            }

            Node newNode = new Node();
            newNode.item = item;
            newNode.next = first;
            first = newNode;

            uniqueItemsSet.add(item);
            size++;
        }

        public void remove(T item) {
            if (isEmpty()) {
                return;
            }

            if (first.item.equals(item)) {
                first = first.next;
                size--;
            } else {
                Node current;

                for (current = first; current.next != null; current = current.next) {
                    if (current.next.item.equals(item)) {
                        current.next = current.next.next;
                        size--;
                        break;
                    }
                }
            }
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
        Queue<Character> moveToFront = new Queue<>();

//        while (StdIn.hasNextChar()) {
//            moveToFront.enqueue(StdIn.readChar());
//        }

        // Test data
        moveToFront.enqueue('a');
        moveToFront.enqueue('b');
        moveToFront.enqueue('c');
        moveToFront.enqueue('d');
        moveToFront.enqueue('c');
        moveToFront.enqueue('d');
        moveToFront.enqueue('z');

        StringJoiner list = new StringJoiner(" ");

        for (char character : moveToFront) {
            list.add(String.valueOf(character));
        }

        StdOut.println("Characters: " + list.toString());
         StdOut.println("Expected: z d c b a");
    }
}
