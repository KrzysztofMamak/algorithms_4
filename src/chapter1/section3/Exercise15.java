package chapter1.section3;

public class Exercise15 {
    private static void printItemCountingFromTheEnd(Queue<String> queue, int k) {
        int counter = 1;

        for (String value : queue) {
            if (counter == queue.size() - (k - 1)) {
                System.out.println(value);
                break;
            }
            counter++;
        }
    }

//    input: 3 "abc bcd cde def efg fgh ghi hij"; output: fgh
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        String input = args[1];

        String[] splitStrings = input.split(" ");

        Queue<String> queue = new Queue<>();

        for (String string : splitStrings) {
            queue.enqueue(string);
        }

        printItemCountingFromTheEnd(queue, k);
    }
}
