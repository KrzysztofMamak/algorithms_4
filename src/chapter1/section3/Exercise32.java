package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

public class Exercise32 {
    public static class Steque<T> implements Iterable<T> {
        class Node {
            T item;
            Node next;
        }

        Node first;
        int size;

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void push(T item) {
            Node newNode = new Node();
            newNode.item = item;

            if (isEmpty()) {
                first = newNode;
            } else {
                Node currentNode;

                for (currentNode = first; currentNode.next != null; currentNode = currentNode.next);

                currentNode.next = newNode;
            }

            size++;
        }

        public T pop() {
            if (isEmpty()) {
                return null;
            }

            Node currentNode;

            for (currentNode = first; currentNode.next.next != null; currentNode = currentNode.next);

            T item = currentNode.next.item;

            currentNode.next = null;

            size--;

            return item;
        }

        public void enqueue(T item) {
            Node newNode = new Node();
            newNode.item = item;

            if (!isEmpty()) {
                newNode.next = first;
            }

            first = newNode;

            size++;
        }

        @Override
        public Iterator<T> iterator() {
            return new StequeIterator();
        }

        private class StequeIterator implements Iterator<T> {
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
        Steque<Integer> steque = new Steque<>();
        steque.push(1);
        steque.push(2);
        steque.push(3);
        steque.pop();
        steque.enqueue(5);
        steque.enqueue(6);

        StringJoiner stequeItems = new StringJoiner(" ");
        for (int number : steque) {
            stequeItems.add(String.valueOf(number));
        }

        StdOut.println("Steque items: " + stequeItems.toString());
        StdOut.println("Expected: 6 5 1 2"); // TODO -> verify - 2, 1, 5, 6
    }
}
