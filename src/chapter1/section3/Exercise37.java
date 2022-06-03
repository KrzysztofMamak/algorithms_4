package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;

public class Exercise37 {
    public static void josephusFlaviusProblem(int N, int M) {
        Queue<Integer> queue = new Queue<>();

        for (int i = 0; i < M; i++) {
            queue.enqueue(i);
        }

        StdOut.println();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N - 1; j++) {
                queue.enqueue(queue.dequeue());
            }
            StdOut.print(queue.dequeue() + " ");
        }
    }

    public static void main(String[] args) {
        int numberOfPeople = Integer.parseInt(args[0]);
        int personOrder = Integer.parseInt(args[1]);

        StdOut.println("Order in which people are eliminated:");
        josephusFlaviusProblem(personOrder, numberOfPeople);
    }
}
