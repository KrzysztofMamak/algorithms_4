package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

public class Exercise39 {
    public static class RingBuffer<T> implements Iterable<T> {
        final private T[] ringBuffer;
        private int size;
        private int first;
        private int last;

        final private Queue<T> producerAuxBuffer;
        private int dataCountToBeConsumed;

        @SuppressWarnings("unchecked")
        public RingBuffer(int capacity) {
            ringBuffer = (T[]) new Object[capacity];
            size = 0;
            first = -1;
            last = -1;

            producerAuxBuffer = new Queue<>();
            dataCountToBeConsumed = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void produce(T item) {
            if (dataCountToBeConsumed > 0) {
                //There is data to be consumed
                consumeData(item);
                dataCountToBeConsumed--;
            } else {
                if (isEmpty()) {
                    ringBuffer[size] = item;
                    first = 0;
                    last = 0;
                    size++;
                } else {
                    if (size < ringBuffer.length) {
                        if (last == ringBuffer.length - 1) {
                            last = 0; //wrap around
                        } else {
                            last++;
                        }
                        ringBuffer[last] = item;
                        size++;
                    } else {
                        //RingBuffer is full - add to auxiliary Producer Buffer
                        producerAuxBuffer.enqueue(item);
                    }
                }
            }
        }

        public T consume() {
            if (isEmpty()) {
                dataCountToBeConsumed++;
                return null;
            }

            T item = ringBuffer[first];
            ringBuffer[first] = null; //avoid loitering

            if (first == ringBuffer.length - 1) {
                first = 0; //wrap around
            } else {
                first++;
            }

            size--;

            if (!producerAuxBuffer.isEmpty()) {
                produce(producerAuxBuffer.dequeue());
            }

            return item;
        }

        private void consumeData(T item) {
            StdOut.print("Data consumed: " + item);
        }

        @Override
        public Iterator<T> iterator() {
            return new RingBufferIterator();
        }

        private class RingBufferIterator implements Iterator<T> {

            private int current = first;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                T item = ringBuffer[current];

                if (current == ringBuffer.length - 1) {
                    current = 0; //Wrap around
                } else {
                    current++;
                }

                count++;

                return item;
            }
        }
    }

    public static void main(String[] args) {
        RingBuffer<Integer> ringBuffer = new RingBuffer<>(4);
        ringBuffer.produce(0);
        ringBuffer.produce(1);
        ringBuffer.produce(2);
        ringBuffer.produce(3);
        ringBuffer.produce(4);
        ringBuffer.produce(5);

        Integer item1 = ringBuffer.consume();
        if (item1 != null) {
            StdOut.println("Consumed " + item1);
        }
        StdOut.println("Expected: 0\n");

        Integer item2 = ringBuffer.consume();
        if (item2 != null) {
            StdOut.println("Consumed " + item2);
        }
        StdOut.println("Expected: 1\n");

        ringBuffer.produce(6);
        ringBuffer.produce(7);

        StringJoiner ringBufferItems = new StringJoiner(" ");
        for (int item : ringBuffer) {
            ringBufferItems.add(String.valueOf(item));
        }

        StdOut.println("Main ring buffer items: " + ringBufferItems.toString());
        StdOut.println("Expected: 2 3 4 5");
    }
}
