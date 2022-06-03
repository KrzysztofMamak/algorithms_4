package chapter1.section3;

import java.util.Iterator;

public class DoubleLinkedList<T> implements Iterable<T> {
    public class DoubleNode {
        public T item;
        public DoubleNode previous;
        public DoubleNode next;
    }

    private int size;
    private DoubleNode first;
    private DoubleNode last;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public DoubleNode getFirstNode() {
        return first;
    }

    public DoubleNode getLastNode() {
        return last;
    }

    public T get(int index) {
        if (isEmpty()) {
            return null;
        }

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index must be between 0 and " + (size - 1));
        }

        DoubleNode current;

        if (index <= size / 2) {
            current = first;
            int count = 0;

            while (count != index) {
                current = current.next;
                count++;
            }
        } else {
            current = last;

            int count = size - 1;

            while (count != index) {
                current = current.previous;
                count--;
            }
        }

        return current.item;
    }

    public void insertAtTheBeginning(T item) {
        DoubleNode oldFirst = first;

        first = new DoubleNode();
        first.item = item;
        first.next = oldFirst;

        if (oldFirst != null) {
            oldFirst.previous = first;
        }

        if (isEmpty()) {
            last = first;
        }

        size++;
    }

    public DoubleNode insertAtTheBeginningAndGetNode(T item) {
        insertAtTheBeginning(item);
        return first;
    }

    public void insertAtTheEnd(T item) {
        DoubleNode oldLast = last;

        last = new DoubleNode();
        last.item = item;
        last.previous = oldLast;

        if (oldLast != null) {
            oldLast.next = last;
        }

        if (isEmpty()) {
            first = last;
        }

        size++;
    }

    public void insertBefore(T item, T beforeItem) {
        if (isEmpty()) {
            return;
        }

        DoubleNode currentNode;

        for (currentNode = first; currentNode != null; currentNode = currentNode.next) {
            if (currentNode.item.equals(beforeItem)) {
                break;
            }
        }

        if (currentNode != null) {
            DoubleNode newNode = new DoubleNode();
            newNode.item = item;

            DoubleNode previousNode = currentNode.previous;
            currentNode.previous = newNode;
            newNode.next = currentNode;
            newNode.previous = previousNode;

            if (newNode.previous == null) {
                first = newNode;
            } else {
                newNode.previous.next = newNode;
            }

            size++;
        }
    }

    public void insertAfter(T item, T afterItem) {
        if (isEmpty()) {
            return;
        }

        DoubleNode currentNode;

        for (currentNode = first; currentNode != null; currentNode = currentNode.next) {
            if (currentNode.item.equals(afterItem)) {
                break;
            }
        }

        if (currentNode != null) {
            DoubleNode newNode = new DoubleNode();
            newNode.item = item;

            DoubleNode nextNode = currentNode.next;
            currentNode.next = newNode;
            newNode.previous = currentNode;
            newNode.next = nextNode;

            if (newNode.next == null) {
                last = newNode;
            } else {
                newNode.next.previous = newNode;
            }

            size++;
        }
    }

    public T removeFromTheBeginning() {
        if (isEmpty()) {
            return null;
        }

        T item = first.item;

        if (first.next != null) {
            first.next.previous = null;
        } else {
            last = null;
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

        if (last.previous != null) {
            last.previous.next = null;
        } else {
            first = null;
        }

        last = last.previous;

        size--;

        return item;
    }

    public void removeItem(T item) {
        if (isEmpty()) {
            return;
        }

        DoubleNode currentNode = first;

        while (currentNode != null) {
            if (currentNode.item.equals(item)) {
                removeItemWithNode(currentNode);
                break;
            }

            currentNode = currentNode.next;
        }
    }

    public void removeItemWithNode(DoubleNode node) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }

        if (isEmpty()) {
            return;
        }

        DoubleNode previousNode = node.previous;
        DoubleNode nextNode = node.next;

        if (previousNode != null) {
            previousNode.next = nextNode;
        }

        if (nextNode != null) {
            nextNode.previous = previousNode;
        }

        if (node == first) {
            first = nextNode;
        }

        if (node == last) {
            last = previousNode;
        }

        size--;
    }

    public T removeItemByIndex(int nodeIndex) {
        if (isEmpty()) {
            return null;
        }

        if (nodeIndex < 0 || nodeIndex >= size) {
            throw new IllegalArgumentException("Index must be between 0 and " + (size - 1));
        }

        boolean startFromTheBeginning = nodeIndex <= size / 2;

        int index = startFromTheBeginning ? 0 : size - 1;

        DoubleNode currentNode = startFromTheBeginning ? first : last;

//        TODO -> check
        while (currentNode != null) {
            if (nodeIndex == index) {
                break;
            }

            if (startFromTheBeginning) {
                index++;
            } else {
                index--;
            }

            currentNode = startFromTheBeginning ? currentNode.next : currentNode.previous;
        }

        @SuppressWarnings("ConstantConditions") // If we got here, the node exists
        T item = currentNode.item;
        removeItemWithNode(currentNode);

        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new DoubleLinkedListIterator();
    }

    private class DoubleLinkedListIterator implements Iterator<T> {
        int index = 0;
        DoubleNode currentNode = first;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T item = currentNode.item;
            currentNode = currentNode.next;
            index++;

            return item;
        }
    }
}
