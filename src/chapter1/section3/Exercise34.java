package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.StringJoiner;

public class Exercise34 {
    public static class RandomBag<T> implements Iterable<T> {
        @SuppressWarnings("unchecked")
        public RandomBag() {
            array = (T[]) new Object[1];
            size = 0;
        }

        private T[] array;
        private int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void add(T item) {
            if (size == array.length) {
                resize(size * 2);
            }

            array[size++] = item;
        }

        @SuppressWarnings("unchecked")
        public void resize(int newSize) {
            T[] tmp = (T[]) new Object[newSize];

            if (size >= 0) System.arraycopy(array, 0, tmp, 0, size);

            array = tmp;
        }

        @Override
        public Iterator<T> iterator() {
            return new RandomBagIterator();
        }

        private class RandomBagIterator implements Iterator<T> {
            int index;
            T[] arrayCopy;

            @SuppressWarnings("unchecked")
            public RandomBagIterator() {
                index = 0;
                arrayCopy = (T[]) new Object[size];

                System.arraycopy(array, 0, arrayCopy, 0, size);

//                TODO -> check if sortArrayCopy is necessary
                sortArrayCopy();
            }

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return arrayCopy[index++];
            }

            private void sortArrayCopy() {
                for (int i = 0; i < size; i++) {
                    int randomIndex = StdRandom.uniform(0, size - 1);

                    T temp = arrayCopy[i];
                    arrayCopy[i] = arrayCopy[randomIndex];
                    arrayCopy[randomIndex] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        RandomBag<Integer> randomBag = new RandomBag<>();
        randomBag.add(1);
        randomBag.add(2);
        randomBag.add(3);
        randomBag.add(4);
        randomBag.add(5);
        randomBag.add(6);
        randomBag.add(7);
        randomBag.add(8);

        StdOut.print("Random bag items: ");

        StringJoiner randomBagItems = new StringJoiner(" ");
        for (int item : randomBag) {
            randomBagItems.add(String.valueOf(item));
        }

        StdOut.println(randomBagItems.toString());
    }
}
