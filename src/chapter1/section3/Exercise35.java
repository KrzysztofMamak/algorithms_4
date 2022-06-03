package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise35 {
    public static class RandomQueue<T> {
        @SuppressWarnings("unchecked")
        RandomQueue() {
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

            if (size >= 0) System.arraycopy(array, 0, tmp, 0, size);

            array = tmp;
        }
    }

    public static void main(String[] args) {
        RandomQueue<Card> cards = getCards();

        Card[][] playersCards = new Card[2][13];

        for (int i = 0; i < playersCards[0].length; i++) {
            playersCards[0][i] = cards.dequeue();
            playersCards[1][i] = cards.dequeue();
        }

        for (int i = 0; i < playersCards.length; i++) {
            StdOut.println("Player " + i + 1 + " cards:");
            for (Card card : playersCards[i]) {
                StdOut.println(card);
            }
            StdOut.println();
        }

        Card sample = cards.sample();
        StdOut.println("Size before sample: " + cards.size + " Expected: 26");
        StdOut.println("Random item: " + sample);
        StdOut.println("Size after sample: " + cards.size + " Expected: 26");
    }

    private static RandomQueue<Card> getCards() {
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        RandomQueue<Card> cards = new RandomQueue<>();

        for (String suit : suits) {
            cards.enqueue(new Card("A", suit));
            cards.enqueue(new Card("2", suit));
            cards.enqueue(new Card("3", suit));
            cards.enqueue(new Card("4", suit));
            cards.enqueue(new Card("5", suit));
            cards.enqueue(new Card("6", suit));
            cards.enqueue(new Card("7", suit));
            cards.enqueue(new Card("8", suit));
            cards.enqueue(new Card("9", suit));
            cards.enqueue(new Card("10", suit));
            cards.enqueue(new Card("J", suit));
            cards.enqueue(new Card("Q", suit));
            cards.enqueue(new Card("K", suit));
        }

        return cards;
    }

    private static class Card {
        Card(String suit, String value) {
            this.suit = suit;
            this.value = value;
        }

        final String suit;
        final String value;

        @Override
        public String toString() {
            return "Card(" + suit + ", " + value + ")";
        }
    }
}
