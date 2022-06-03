package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

public class Exercise31 {
    public static class DoubleLinkedList<T> implements Iterable<T> {
        private class DoubleNode {
            public DoubleNode previous;
            public DoubleNode next;
            public T item;
        }

        private int size;
        private DoubleNode first;
        private DoubleNode last;

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void insertAtTheBeginning(T item) {
            DoubleNode newNode = new DoubleNode();
            newNode.item = item;
            newNode.next = first;

            if (first != null) {
                first.previous = newNode;
            }

            first = newNode;

            if (isEmpty()) {
                last = first;
            }

            size++;
        }

        public void insertAtTheEnd(T item) {
            DoubleNode newNode = new DoubleNode();
            newNode.item = item;
            newNode.previous = last;

            if (last != null) {
                last.next = newNode;
            }

            last = newNode;

            if (isEmpty()) {
                first = last;
            }

            size++;
        }

        public T removeFromTheBeginning() {
            if (isEmpty()) {
                return null;
            }

            T item = first.item;;

            if (first.next == null) {
                last = null;
            } else {
                first.next.previous = null;
            }

            first = first.next;

            size--;

            return item;
        }

        public T removeFromTheEnd() {
            if (isEmpty()) {
                return null;
            }

            T item = last.item;

            if (last.previous == null) {
                first = null;
            } else {
                last.previous.next = null;
            }

            last = last.previous;

            size--;

            return item;
        }

        public void insertBefore(T item, T newItem) {
            if (isEmpty()) {
                return;
            }

            DoubleNode current;

            for (current = first; current != null; current = current.next) {
                if (current.item.equals(item)) {
                    break;
                }
            }

            if (current == null) {
                return;
            }

            DoubleNode newNode = new DoubleNode();
            newNode.item = newItem;

            DoubleNode previousNode = current.previous;
            current.previous = newNode;
            newNode.next = current;
            newNode.previous = previousNode;

            if (newNode.previous == null) {
                first = newNode;
            } else {
//                newNode.previous.next = newNode;
                previousNode.next = newNode;
            }

            size++;
        }

        public void insertAfter(T item, T newItem) {
            if (isEmpty()) {
                return;
            }

            if (first == last && first.item.equals(item)) {
                DoubleNode newNode = new DoubleNode();
                newNode.item = item;
                newNode.previous = first;
                last = newNode;
                first.next = last;
                size++;
                return;
            }

            DoubleNode current;

            for (current = first; current != null; current = current.next) {
                if (current.item.equals(item)) {
                    break;
                }
            }

            if (current != null) {
                DoubleNode newNode = new DoubleNode();
                newNode.item = newItem;

                DoubleNode oldNextNode = current.next;

                newNode.previous = current;
                newNode.next = oldNextNode;
                current.next = newNode;

                if (oldNextNode != null) {
                    oldNextNode.previous = newNode;
                } else {
                    last = newNode;
                }

                size++;
            }
        }

//        TODO -> fix
        public T removeByIndex(int index) {
            if (index < 0 || isEmpty() || size <= index + 1) {
                return null;
            }

            if (first == last) {
                size--;
                T item = first.item;
                first = null;
                last = null;
                return item;
            }

            boolean startFromTheBeginning = index < size() / 2;

            int tmpIndex = startFromTheBeginning ? 0 : size();

            DoubleNode currentNode = startFromTheBeginning ? first : last;

            while (currentNode != null) {
                if (tmpIndex == index) {
                    break;
                }

                if (startFromTheBeginning) {
                    currentNode = currentNode.next;
                    tmpIndex++;
                } else {
                    currentNode = currentNode.previous;
                    tmpIndex--;
                }
            }

            T item = currentNode.item;

            DoubleNode previousNode = currentNode.previous;
            DoubleNode nextNode = currentNode.next;

            if (previousNode != null) {
                previousNode.next = nextNode;
            }
            if (nextNode != null) {
                nextNode.previous = previousNode;
            }
            if (currentNode == first) {
                first = nextNode;
            }
            if (currentNode == last) {
                last = previousNode;
            }

            size--;

            return item;
        }

        public T removeByKey(T key) {
            if (isEmpty()) {
                return null;
            }

            if (first == last) {
                if (first.item.equals(key)) {
                    T item = first.item;
                    first = null;
                    last = null;
                    size--;
                    return item;
                }
                return null;
            }

            DoubleNode current;
            T item = null;

            for (current = first; current != null; current = current.next) {
                if (current.item.equals(key)) {
                    item = current.item;

                    if (current.previous == null) {
                        first = current.next;
                    }

                    if (current.next == null) {
                        last = current.previous;
                    }

                    if (current.previous != null) {
                        current.previous.next = current.next;
                    }

                    if (current.next != null) {
                        current.next.previous = current.previous;
                    }

                    size--;

                    break;
                }
            }

            return item;
        }

//        TODO -> create static methods in DoubleNode

        @Override
        public Iterator<T> iterator() {
            return new DoubleLinkedListIterator();
        }

        private class DoubleLinkedListIterator implements Iterator<T> {
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

    public static void main(String[] args) {
        DoubleLinkedList<Integer> doublyLinkedList = new DoubleLinkedList<>();
        doublyLinkedList.insertAtTheBeginning(10);
        doublyLinkedList.insertAtTheBeginning(30);
        doublyLinkedList.insertAtTheEnd(999);

        StringJoiner doublyLinkedListItems = new StringJoiner(" ");
        for (int item : doublyLinkedList) {
            doublyLinkedListItems.add(String.valueOf(item));
        }

        StdOut.println("Double linked list items after initial insert: " + doublyLinkedListItems.toString());
        StdOut.println("Expected: 30 10 999");

        doublyLinkedList.insertBefore(9999, 998);
        doublyLinkedList.insertBefore(999, 997);

        doublyLinkedListItems = new StringJoiner(" ");
        for (int item : doublyLinkedList) {
            doublyLinkedListItems.add(String.valueOf(item));
        }

        StdOut.println("\nDouble linked list items after insert before 999: " + doublyLinkedListItems.toString());
        StdOut.println("Expected: 30 10 997 999");

        doublyLinkedList.insertAfter(10, 11);

        doublyLinkedListItems = new StringJoiner(" ");
        for (int item : doublyLinkedList) {
            doublyLinkedListItems.add(String.valueOf(item));
        }

        StdOut.println("\nDouble linked list items after insert after 10: " + doublyLinkedListItems.toString());
        StdOut.println("Expected: 30 10 11 997 999");

        doublyLinkedList.removeFromTheBeginning();

        doublyLinkedListItems = new StringJoiner(" ");
        for (int item : doublyLinkedList) {
            doublyLinkedListItems.add(String.valueOf(item));
        }

        StdOut.println("\nDouble linked list items after first item deletion: " + doublyLinkedListItems.toString());
        StdOut.println("Expected: 10 11 997 999");

        doublyLinkedList.removeFromTheEnd();

        doublyLinkedListItems = new StringJoiner(" ");
        for (int item : doublyLinkedList) {
            doublyLinkedListItems.add(String.valueOf(item));
        }

        StdOut.println("\nDouble linked list items after last item deletion: " + doublyLinkedListItems.toString());
        StdOut.println("Expected: 10 11 997");

        doublyLinkedList.removeByIndex(1);

        doublyLinkedListItems = new StringJoiner(" ");
        for (int item : doublyLinkedList) {
            doublyLinkedListItems.add(String.valueOf(item));
        }

        StdOut.println("\nDouble linked list items after second item deletion: " + doublyLinkedListItems.toString());
        StdOut.println("Expected: 10 997");
    }
}
