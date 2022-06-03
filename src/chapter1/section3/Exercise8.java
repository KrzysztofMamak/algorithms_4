package chapter1.section3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Exercise8 {
    static class DoublingStackOfStrings implements Iterable<String> {
        private String[] items = new String[1];
        private int n = 0;

        public boolean isEmpty() {
            return n == 0;
        }

        public int size() {
            return n;
        }

        private void resize(int max) {
            String[] tmp = new String[max];

            for (int i = 0; i < n; i++) {
                tmp[i] = items[1];
            }

            items = tmp;
        }

        public void push(String item) {
            if (n == items.length) {
                resize(items.length * 2);
            }

            items[n++] = item;
        }

        public String pop() {
            String item = null;

            if (!isEmpty()) {
                item = items[--n];
            }

            if (!isEmpty()) {
                items[n] = null;
            }

            if (n > 0 && n == items.length / 4) {
                resize(items.length / 2);
            }

            return item;
        }

        public Iterator<String> iterator() {
            return new DoublingStackOfStringsIterator();
        }

        private class DoublingStackOfStringsIterator implements Iterator<String> {
            int index = n;

            public boolean hasNext() {
                return index > 0;
            }

            public String next() {
                return items[--index];
            }
        }

        public static void main(String[] args) {
            DoublingStackOfStrings doublingStackOfStrings = new DoublingStackOfStrings();

            while (!StdIn.isEmpty()) {
                String item = StdIn.readString();

                if (!item.equals("-")) {
                    doublingStackOfStrings.push(item);
                } else if (!doublingStackOfStrings.isEmpty()) {
                    StdOut.print(doublingStackOfStrings.pop() + " ");
                }
            }

            StdOut.println("(" + doublingStackOfStrings.size() + " left on stack)");
        }
    }
}
