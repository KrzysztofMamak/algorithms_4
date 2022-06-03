package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

public class Exercise12 {
    private static Stack<String> copy(Stack<String> stack) {
        Stack<String> tmp = new Stack<>();
        Stack<String> result = new Stack<>();

        for (String value : stack) {
            tmp.push(value);
        }

        for (String value : tmp) {
            result.push(value);
        }

        return result;
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("First Item");
        stack.push("Second Item");
        stack.push("Third Item");

        Stack<String> copy = copy(stack);
        stack.pop();
        stack.pop();

        for (String s : copy) {
            StdOut.println(s);
        }

        StdOut.println("\nExpected: " +
                "\nThird Item\n" +
                "Second Item\n" +
                "First Item");
    }

}
