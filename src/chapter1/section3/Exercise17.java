package chapter1.section3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

import java.util.Date;

public class Exercise17 {
    private static Transaction[] readTransactions(String inputFilePath) {
        In in = new In(inputFilePath);
        Queue<Transaction> queue = new Queue<>();

        while (!in.isEmpty()) {
            queue.enqueue(new Transaction(in.readLine())); // maybe readString?
        }

        Transaction[] result = new Transaction[queue.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = queue.dequeue();
        }

        return result;
    }

    public static void main(String[] args) {
        String inputFilePath = args[0];
        Transaction[] transactions = readTransactions(inputFilePath);

        for (Transaction transaction : transactions) {
            StdOut.println(transaction);
        }
    }
}
