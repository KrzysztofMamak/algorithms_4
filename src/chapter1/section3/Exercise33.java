package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

public class Exercise33 {
    public static class Deque<T> implements Iterable<T> {
        private class DoubleNode {
            T item;
            DoubleNode previous;
            DoubleNode next;
        }

        public Deque() {
            first = null;
            last = null;
            size = 0;
        }

        private DoubleNode first;
        private DoubleNode last;
        private int size;

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void pushLeft(T item) {
            DoubleNode newNode = new DoubleNode();
            newNode.item = item;

            if (isEmpty()) {
                first = newNode;
                last = newNode;
            } else {
                newNode.next = first;
                first.previous = newNode;
                first = newNode;
            }

            size++;
        }

        public void pushRight(T item) {
            DoubleNode newNode = new DoubleNode();
            newNode.item = item;

            if (isEmpty()) {
                first = newNode;
                last = newNode;
            } else {
                newNode.previous = last;
                last.next = newNode;
                last = newNode;
            }

            size++;
        }

        public T popLeft() {
            if (isEmpty()) {
                return null;
            }

            T item = first.item;

            if (first == last) {
                first = null;
                last = null;
            } else {
                first = first.next;
                first.previous = null;
            }

            size--;

            return item;
        }

        public T popRight() {
            if (isEmpty()) {
                return null;
            }

            T item = last.item;

            if (first == last) {
                first = null;
                last = null;
            } else {
                last = last.previous;
                last.next = null;
            }

            size--;

            return item;
        }

        @Override
        public Iterator<T> iterator() {
            return new DequeIterator();
        }

        private class DequeIterator implements Iterator<T> {
            DoubleNode current = first;

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

    public static class ResizingArrayDeque<T> implements Iterable<T> {
        private static final int DEFAULT_SIZE = 10;

        @SuppressWarnings("unchecked")
        public ResizingArrayDeque() {
            array = (T[]) new Object[DEFAULT_SIZE];
            begin = DEFAULT_SIZE / 2;
        }

        private T[] array;
        private int size;
        private int begin;

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void pushLeft(T item) {
            array[begin] = item;
            begin--;
            size++;

            if (begin < 0) {
                if (size > array.length / 2) {
                    resize(array.length * 2);
                } else {
                    resize(array.length);
                }
            }
        }

        public void pushRight(T item) {
            int endIndex = begin + size + 1;
            array[endIndex] = item;
            size++;

            if (endIndex < array.length - 1) {
                if (size > array.length / 2) {
                    resize(array.length * 2);
                } else {
                    resize(array.length);
                }
            }
        }

        public T popLeft() {
            if (isEmpty()) {
                return null;
            }

            T item = array[begin + 1];
            array[++begin] = null;
            size--;

            if (size == array.length / 4) {
                resize(array.length / 2);
            }

            return item;
        }

        public T popRight() {
            if (isEmpty()) {
                return null;
            }

            int endIndex = begin + size;
            T item = array[endIndex];
            array[endIndex] = null;
            size--;

            if (size == array.length / 4) {
                resize(array.length / 2);
            }

            return item;
        }

        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            int startPosition = (newSize / 2) - (size / 2);
            T[] newArray = (T[]) new Object[newSize];

            System.arraycopy(array, begin + 1, newArray, startPosition, size);

            array = newArray;
            begin = startPosition - 1;
        }

        @Override
        public Iterator<T> iterator() {
            return new ResizingArrayDequeIterator();
        }

        private class ResizingArrayDequeIterator implements Iterator<T> {
            int currentIndex = begin + 1;
            int endIndex = begin + size;

            @Override
            public boolean hasNext() {
                return currentIndex <= endIndex;
            }

            @Override
            public T next() {
                return array[currentIndex++];
            }
        }
    }

    public static void main(String[] args) {
        testDequePushLeft();
        testDequePushRight();
        testDequePopLeft();
        testDequePopRight();

        StdOut.print("\n\n");

        testResizingArrayDequePushLeft();
        testResizingArrayDequePushRight();
        testResizingArrayDequePopLeft();
        testResizingArrayDequePopRight();
    }

    private static void testDequePushLeft() {
        StdOut.println("Test Push Left");

        Deque<String> deque = new Deque<>();
        deque.pushLeft("a");
        deque.pushLeft("b");
        deque.pushLeft("c");

        StringJoiner dequeItems = new StringJoiner(" ");
        for (String item : deque) {
            dequeItems.add(item);
        }

        StdOut.println("Deque items: " + dequeItems.toString());
        StdOut.println("Expected: c b a");
    }

    private static void testDequePushRight() {
        StdOut.println("\nTest Push Right");

        Deque<String> deque = new Deque<>();
        deque.pushRight("a");
        deque.pushRight("b");
        deque.pushRight("c");

        StringJoiner dequeItems = new StringJoiner(" ");
        for (String item : deque) {
            dequeItems.add(item);
        }

        StdOut.println("Deque items: " + dequeItems.toString());
        StdOut.println("Expected: a b c");
    }

    private static void testDequePopLeft() {
        StdOut.println("\nTest Pop Left");

        Deque<String> deque = new Deque<>();
        deque.pushRight("a");
        deque.pushRight("b");
        deque.pushRight("c");

        deque.popLeft();
        deque.popLeft();

        StringJoiner dequeItems = new StringJoiner(" ");
        for (String item : deque) {
            dequeItems.add(item);
        }

        StdOut.println("Deque items: " + dequeItems.toString());
        StdOut.println("Expected: c");
    }

    private static void testDequePopRight() {
        StdOut.println("\nTest Pop Right");

        Deque<String> deque = new Deque<>();
        deque.pushRight("a");
        deque.pushRight("b");
        deque.pushRight("c");

        deque.popRight();
        deque.popRight();

        StringJoiner dequeItems = new StringJoiner(" ");
        for (String item : deque) {
            dequeItems.add(item);
        }

        StdOut.println("Deque items: " + dequeItems.toString());
        StdOut.println("Expected: a");
    }

    private static void testResizingArrayDequePushLeft() {
        StdOut.println("Test Push Left");

        ResizingArrayDeque<String> deque = new ResizingArrayDeque<>();
        deque.pushLeft("a");
        deque.pushLeft("b");
        deque.pushLeft("c");

        StringJoiner dequeItems = new StringJoiner(" ");
        for (String item : deque) {
            dequeItems.add(item);
        }

        StdOut.println("Deque items: " + dequeItems.toString());
        StdOut.println("Expected: c b a");
    }

    private static void testResizingArrayDequePushRight() {
        StdOut.println("\nTest Push Right");

        ResizingArrayDeque<String> deque = new ResizingArrayDeque<>();
        deque.pushRight("a");
        deque.pushRight("b");
        deque.pushRight("c");

        StringJoiner dequeItems = new StringJoiner(" ");
        for (String item : deque) {
            dequeItems.add(item);
        }

        StdOut.println("Deque items: " + dequeItems.toString());
        StdOut.println("Expected: a b c");
    }

    private static void testResizingArrayDequePopLeft() {
        StdOut.println("\nTest Pop Left");

        ResizingArrayDeque<String> deque = new ResizingArrayDeque<>();
        deque.pushRight("a");
        deque.pushRight("b");
        deque.pushRight("c");

        deque.popLeft();
        deque.popLeft();

        StringJoiner dequeItems = new StringJoiner(" ");
        for (String item : deque) {
            dequeItems.add(item);
        }

        StdOut.println("Deque items: " + dequeItems.toString());
        StdOut.println("Expected: c");
    }

    private static void testResizingArrayDequePopRight() {
        StdOut.println("\nTest Pop Right");

        ResizingArrayDeque<String> deque = new ResizingArrayDeque<>();
        deque.pushRight("a");
        deque.pushRight("b");
        deque.pushRight("c");

        deque.popRight();
        deque.popRight();

        StringJoiner dequeItems = new StringJoiner(" ");
        for (String item : deque) {
            dequeItems.add(item);
        }

        StdOut.println("Deque items: " + dequeItems.toString());
        StdOut.println("Expected: a");
    }
}
