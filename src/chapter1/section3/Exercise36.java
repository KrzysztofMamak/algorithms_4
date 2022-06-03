package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class Exercise36 {
    public static class IterableRandomQueue<T> implements Iterable<T> {
        @SuppressWarnings("unchecked")
        IterableRandomQueue() {
            array = (T[]) new Object[1];
            size = 0;
        }

        T[] array;
        int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public void enqueue(T item) {
            if (size == array.length) {
                resize(size * 2);
            }

            array[size++] = item;
        }

        public T dequeue() {
            if (isEmpty()) {
                return null;
            }

            int randomIndex = StdRandom.uniform(0, size);

            T randomItem = array[randomIndex];

            array[randomIndex] = array[size - 1];
            array[--size] = null;

            if (size > 0 && size == array.length / 4) {
                resize(size * 2);
            }

            return randomItem;
        }

        public T sample() {
            if (isEmpty()) {
                return null;
            }

            int randomIndex = StdRandom.uniform(0, size);

            return array[randomIndex];
        }

        @SuppressWarnings("unchecked")
        private void resize(int newSize) {
            T[] tmp = (T[]) new Object[newSize];

            System.arraycopy(array, 0, tmp, 0, size);

            array = tmp;
        }

        @Override
        public Iterator<T> iterator() {
            return new IterableRandomQueueIterator();
        }

        private class IterableRandomQueueIterator implements Iterator<T> {
            int currentIndex;
            T[] arrayCopy;

            IterableRandomQueueIterator() {
                currentIndex = 0;
                arrayCopy = getArrayCopy();
                shuffleArrayCopy();
            }

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return arrayCopy[currentIndex++];
            }

            private void shuffleArrayCopy() {
                for (int i = 0; i < size; i++) {
                    int randomIndex = StdRandom.uniform(0, i + 1);

                    T temp = arrayCopy[i];
                    arrayCopy[i] = arrayCopy[randomIndex];
                    arrayCopy[randomIndex] = temp;
                }
            }

            @SuppressWarnings("unchecked")
            private T[] getArrayCopy() {
                T[] arrayCopy = (T[]) new Object[array.length];

                if (size >= 0) System.arraycopy(array, 0, arrayCopy, 0, size);

                return arrayCopy;
            }
        }
    }

    public static void main(String[] args) {
        IterableRandomQueue<Card> iterableRandomQueue = new IterableRandomQueue();
        IterableRandomQueue<Card> randomQueue = new IterableRandomQueue<>();
        fillQueueWithBridgeHandsCards(randomQueue);

        StdOut.println("Cards:\n");

        for (Card card : randomQueue) {
            StdOut.println(card);
        }
    }

    private static void fillQueueWithBridgeHandsCards(IterableRandomQueue<Card> iterableRandomQueue) {
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};

        for (String suit : suits) {
            iterableRandomQueue.enqueue(new Card("A", suit));
            iterableRandomQueue.enqueue(new Card("2", suit));
            iterableRandomQueue.enqueue(new Card("3", suit));
            iterableRandomQueue.enqueue(new Card("4", suit));
            iterableRandomQueue.enqueue(new Card("5", suit));
            iterableRandomQueue.enqueue(new Card("6", suit));
            iterableRandomQueue.enqueue(new Card("7", suit));
            iterableRandomQueue.enqueue(new Card("8", suit));
            iterableRandomQueue.enqueue(new Card("9", suit));
            iterableRandomQueue.enqueue(new Card("10", suit));
            iterableRandomQueue.enqueue(new Card("J", suit));
            iterableRandomQueue.enqueue(new Card("Q", suit));
            iterableRandomQueue.enqueue(new Card("K", suit));
        }
    }

    private static class Card {
        String value;
        String suit;

        public Card(String value, String suit) {
            this.value = value;
            this.suit = suit;
        }

        @Override
        public String toString() {
            return "Card(" + suit + ", " + value + ")";
        }
    }
}
