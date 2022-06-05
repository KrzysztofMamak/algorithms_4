package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

public class Exercise42 {
    public static class Stack<T> implements Iterable<T> {
        private class Node {
            Node previous;
            Node next;
            T item;
        }

        Node first;
        Node last;
        int size;

        Stack() {
        }

        Stack(Stack<T> s) {
            if (s == null) {
                throw new IllegalArgumentException("Stack must not be null");
            }

            Stack<T> tmpStack = new Stack<>();

            for (T item : s) {
                tmpStack.push(item);
            }

            for (T item : tmpStack) {
                push(item);
            }
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void push(T item) {
            if (item == null) {
                return;
            }

            Node newNode = new Node();
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

        public T pop() {
            if (isEmpty()) {
                return null;
            }

            if (first.next == null) {
                T item = last.item;
                first = null;
                last = null;
                size--;
                return item;
            } else {
                T item = last.item;
                last = last.previous;
                last.next = null;
                size--;
                return item;
            }

//            T item = last.item;
//            last = last.previous;
//            if (last != null) {
//                last.next = null;
//            }
//            size--;
//            return item;
        }

        @Override
        public Iterator<T> iterator() {
            return new StackIterator();
        }

        private class StackIterator implements Iterator<T> {
            Node current = last;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T item = current.item;
                current = current.previous;
                return item;
            }
        }
    }

    public static void main(String[] args) {
        Stack<Integer> originalStack = new Stack<>();
        originalStack.push(1);
        originalStack.push(2);
        originalStack.push(3);
        originalStack.push(4);

        Stack<Integer> stackCopy = new Stack<>(originalStack);

        stackCopy.push(5);
        stackCopy.push(6);

        originalStack.pop();
        originalStack.pop();

        stackCopy.pop();

        StringJoiner originalStackItems = new StringJoiner(" ");
        for (int item : originalStack) {
            originalStackItems.add(String.valueOf(item));
        }

        StdOut.println("Original Stack: " + originalStackItems.toString());
        StdOut.println("Expected: 2 1");

        StdOut.println();

        StringJoiner copyStackItems = new StringJoiner(" ");
        for (int item : stackCopy) {
            copyStackItems.add(String.valueOf(item));
        }

        StdOut.println("Stack Copy: " + copyStackItems.toString());
        StdOut.println("Expected: 5 4 3 2 1");
    }
}
