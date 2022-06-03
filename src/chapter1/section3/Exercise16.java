package chapter1.section3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Date;

public class Exercise16 {
    private static Date[] readDates(String inputFilePath) {
        In in = new In(inputFilePath);
        Queue<Date> queue = new Queue<>();

        while (!in.isEmpty()) {
            queue.enqueue(new Date(in.readString()));
        }

        Date[] result = new Date[queue.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = queue.dequeue();
        }

        return result;
    }

    public static void main(String[] args) {
        String inputFilePath = args[0];
        Date[] dates = readDates(inputFilePath);

        for (Date date : dates) {
            StdOut.println(date);
        }
    }
}
