package chapter1.section3;

// TODO -> maybe queue, maybe add order

import edu.princeton.cs.algs4.StdOut;

public class Exercise11 {
    private static int evaluatePostfix(String postfixExpression) {
        Stack<Integer> operands = new Stack<>();

        String[] values = postfixExpression.split("\\s");

        for (String value : values) {
            if (value.equals("+")
                    || value.equals("-")
                    || value.equals("*")
                    || value.equals("/")) {
                int operand2 = operands.pop();
                int operand1 = operands.pop();
                int result = 0;

                if (value.equals("+")) {
                    result = operand1 + operand2;
                } else if (value.equals("-")) {
                    result = operand1 - operand2;
                } else if (value.equals("*")) {
                    result = operand1 * operand2;
                } else if (value.equals("/")) {
                    result = operand1 / operand2;
                }
                operands.push(result);
            } else {
                operands.push(Integer.parseInt(value));
            }
        }

        return operands.pop();
    }

    public static void main(String[] args) {
        String postfixExpression = args[0];
        StdOut.println("Result: " + evaluatePostfix(postfixExpression));
        StdOut.println("Expected: -12");
    }
}
