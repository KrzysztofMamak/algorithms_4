package chapter1.section3;

//TODO -> indexOfLast is n, change it to n - 1 (real last index)

import edu.princeton.cs.algs4.StdOut;

public class Exercise14 {
    static class FixedSizeArrayQueueOfStrings {
        private String[] items;
        private int n;
        private int indexOfFirst;
        private int indexOfLast;

        public FixedSizeArrayQueueOfStrings(int capacity) {
            items = new String[capacity];
        }

        public boolean isEmpty() {
            return n == 0;
        }

        public int size() {
            return n;
        }

        public void enqueue(String item) {
            if (n != items.length) {
                if (indexOfLast == items.length) {
                    indexOfLast = 0;
                }

                items[indexOfLast++] = item;
                n++;
            }
        }

        public String dequeue() {
            if (isEmpty()) {
                throw new RuntimeException("Queue underflow"); // underflow?
            } else {
                String result = items[indexOfFirst];
                items[indexOfFirst] = null;
                indexOfFirst++;

                if (indexOfFirst == items.length) {
                    indexOfFirst = 0;
                }

                n--;
                return result;
            }
        }

        public static void main(String[] args) {
            FixedSizeArrayQueueOfStrings fixedSizeArrayQueueOfStrings = new FixedSizeArrayQueueOfStrings(3);

            fixedSizeArrayQueueOfStrings.enqueue("1");
            fixedSizeArrayQueueOfStrings.enqueue("2");
            fixedSizeArrayQueueOfStrings.enqueue("3");

            StdOut.println("Dequeue 1: " + fixedSizeArrayQueueOfStrings.dequeue());
            StdOut.println("Expected: 1\n");

            fixedSizeArrayQueueOfStrings.enqueue("4");
            StdOut.println("Dequeue 2: " + fixedSizeArrayQueueOfStrings.dequeue());
            StdOut.println("Expected: 2");
        }
    }

    static class ResizingArrayQueueOfStrings {
        private String[] items;
        private int n;
        private int indexOfFirst;
        private int indexOfLast;

        public ResizingArrayQueueOfStrings(int capacity) {
            items = new String[capacity];
        }

        public boolean isEmpty() {
            return n == 0;
        }

        public int size() {
            return n;
        }

        public void resize(int capacity) {
            String[] tmp = new String[capacity];

            for (int i = 0; i < n; i++) {
                tmp[i] = items[(indexOfFirst + i) % items.length];
            }

            items = tmp;
            indexOfFirst = 0;
            indexOfLast = n;
        }

        public void enqueue(String item) {
            if (n == items.length) {
                resize(items.length * 2);
            }

            if (indexOfLast == items.length) {
                indexOfLast = 0;
            }

            items[n++] = item;
            n++;
        }

        public String dequeue() {
            if (isEmpty()) {
                throw new RuntimeException("Queue underflow"); // underflow?
            } else {
                String result = items[indexOfFirst];
                items[indexOfFirst] = null;
                indexOfFirst++;

                if (indexOfFirst == items.length) {
                    indexOfFirst = 0;
                }

                if (n > 0 && n == items.length / 4) {
                    resize(items.length / 2);
                }

                return result;
            }
        }

        public static void main(String[] args) {
            ResizingArrayQueueOfStrings resizingArrayQueueOfStrings = new ResizingArrayQueueOfStrings(3);

            resizingArrayQueueOfStrings.enqueue("1");
            resizingArrayQueueOfStrings.enqueue("2");
            resizingArrayQueueOfStrings.enqueue("3");
            resizingArrayQueueOfStrings.enqueue("Full");

            StdOut.println("Dequeue 1: " + resizingArrayQueueOfStrings.dequeue());
            StdOut.println("Expected: 1\n");

            resizingArrayQueueOfStrings.enqueue("4");
            StdOut.println("Dequeue 2: " + resizingArrayQueueOfStrings.dequeue());
            StdOut.println("Expected: 2");
        }
    }
}
