package chapter1.section3;

import java.util.Iterator;

public class DoubleCircularLinkedList<T> implements Iterable<T> {
    public class DoubleNode {
        public T item;
        DoubleNode previous;
        DoubleNode next;
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

    public DoubleNode first() {
        return first;
    }

    public DoubleNode last() {
        return last;
    }

    public T get(int index) {
        if (isEmpty()) {
            return null;
        }

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index must be between 0 and " + (size - 1));
        }

        DoubleNode current = first;
        int count = 0;

        while (count != index) {
            current = current.next;
            count++;
        }

        return current.item;
    }

    public void insertNodeAtTheBeginning(DoubleNode node) {
        DoubleNode oldFirst = first;

        first = node;
        first.next = oldFirst;

        if (!isEmpty()) {
            first.previous = oldFirst.previous;
            oldFirst.previous = first;
        } else {
            last = first;
            first.previous = last;
        }

        last.next = first;
        size++;
    }

    public void insertNodeAtTheEnd(DoubleNode node) {
        DoubleNode oldLast = last;

        last = node;
        last.previous = oldLast;

        if (!isEmpty()) {
            last.next = oldLast.next;
            oldLast.next = last;
        } else {
            first = last;
            first.next = first;
        }

        first.previous = last;
        size++;
    }

    public void insertAtTheBeginning(T item) {
        DoubleNode oldFirst = first;

        first = new DoubleNode();
        first.item = item;
        first.next = oldFirst;

        if (!isEmpty()) {
            first.previous = oldFirst.previous;
            oldFirst.previous = first;
        } else {
            last = first;
            first.previous = last;
        }

        last.next = first;
        size++;
    }

    public void insertAtTheEnd(T item) {
        DoubleNode oldLast = last;

        last = new DoubleNode();
        last.item = item;
        last.previous = oldLast;

        if (!isEmpty()) {
            last.next = oldLast.next;
            oldLast.next = last;
        } else {
            first = last;
            last.next = first;
        }

        first.previous = last;
        size++;
    }

    public void insertBefore(T item, T beforeItem) {
        if (isEmpty()) {
            return;
        }

        DoubleNode currentNode;

        for (currentNode = first; currentNode != last; currentNode = currentNode.next) {
            if (currentNode.item.equals(beforeItem)) {
                break;
            }
        }

        if (currentNode == last && last.item != beforeItem) {
            return;
        }

        DoubleNode newNode = new DoubleNode();
        newNode.item = item;

        DoubleNode previousNode = currentNode.previous;
        currentNode.previous = newNode; // TODO -> unnecessary?
        newNode.next = currentNode;
        newNode.previous = previousNode;

        if (newNode.previous == last) {
            first = newNode;
        }

        newNode.previous.next = newNode; // TODO -> unnecessary?
        size++;
    }

    public void insertAfter(T item, T afterItem) {
        if (isEmpty()) {
            return;
        }

        DoubleNode currentNode;

        for (currentNode = first; currentNode != last; currentNode = currentNode.next) {
            if (currentNode.item.equals(afterItem)) {
                break;
            }
        }

        if (currentNode == last && last.item != afterItem) {
            return;
        }

        DoubleNode newNode = new DoubleNode();
        newNode.item = item;

        DoubleNode nextNode = currentNode.next;
        currentNode.next = newNode; // TODO -> unnecessary?
        newNode.previous = currentNode;
        newNode.next = nextNode;

        if (newNode.next == first) {
            last = newNode;
        }

        newNode.next.previous = newNode; // TODO -> unnecessary?
        size++;
    }

    public void insertListAtTheEnd(DoubleCircularLinkedList<T> doubleCircularLinkedList) {
        if (!doubleCircularLinkedList.isEmpty()) {
            doubleCircularLinkedList.first().previous = last;
            doubleCircularLinkedList.last().next = first;
        } // TODO -> else return?

        if (!isEmpty()) {
            last.next = doubleCircularLinkedList.first();
            first.previous = doubleCircularLinkedList.last();
        } else {
            first = doubleCircularLinkedList.first();
        }

        last = doubleCircularLinkedList.last();
        size += doubleCircularLinkedList.size;
    }

    public T removeFromTheBeginning() {
        if (isEmpty()) {
            return null;
        }

        T item = first.item;

        if (size > 1) {
            first.next.previous = first.previous;
            first.previous.next = first.next;
            first = first.next;
        } else {
            first = null;
            last = null;
        }

        size--;
        return item;
    }

    public T removeFromTheEnd() {
        if (isEmpty()) {
            return null;
        }

        T item = last.item;

        if (size > 1) {
            last.previous.next = last.next;
            last.next.previous = last.previous;
            last = last.previous;
        } else {
            first = null;
            last = null;
        }

        size--;
        return item;
    }

    public void removeItem(T item) {
        if (isEmpty()) {
            return;
        }

        DoubleNode currentNode = first;

        while (currentNode != last) {
            if (currentNode.item.equals(item)) {
                removeItemWithNode(currentNode);
                return;
            }

            currentNode = currentNode.next;
        }

        if (currentNode.item == item) {
            removeFromTheEnd();
        }
    }

    public void removeItemWithNode(DoubleNode node) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }

        if (isEmpty()) {
            return;
        }

        if (node == first && first == last) {
            first = null;
            last = null;
            return;
        }

        DoubleNode previousNode = node.previous;
        DoubleNode nextNode = node.next;

        previousNode.next = nextNode;
        nextNode.previous = previousNode;

        if (node == first) {
            first = nextNode;
        }

        if (node == last) {
            last = previousNode;
        }

        size--;
    }

    public T removeByIndex(int nodeIndex) {
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
        while (true) {
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

        T item = currentNode.item;
        removeItemWithNode(currentNode);

        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new DoubleCircularLinkedListIterator();
    }

    private class DoubleCircularLinkedListIterator implements Iterator<T> {
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
