package chapter1.section3;

import java.util.NoSuchElementException;

public class Exercise7 {
    static class StackWithPeek<T> {
        private class Node {
            T item;
            Node next;
        }

        private Node first;
        private int n;

        public boolean isEmpty() {
            return first == null;
        }

        public int size() {
            return n;
        }

        public void push(T item) {
            Node oldFirst = first;

            first = new Node();
            first.item = item;
            first.next = oldFirst;
            n++;
        }

        public T pop() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack underflow"); // underflow?
            }

            T item = first.item;
            first = first.next;
            n--;

            return item;
        }

        public T peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack underflow"); // underflow?
            }

            return first.item;
        }

        public static void main(String[] args) {
            StackWithPeek<String> stack = new StackWithPeek<>();

            stack.push("String 1");
            stack.push("String 2");
            stack.push("String 4");
            stack.push("String 8");

            System.out.println("Peek: " + stack.peek());
            System.out.println("Expected: String 8\n");

            System.out.println("Pop: " + stack.pop());
            System.out.println("Expected: String 8\n");
            System.out.println("Pop: " + stack.pop());
            System.out.println("Expected: String 4");
        }
    }
}
