package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

public class Exercise9 {
    private static String getInfixExpression(String input) {
        Stack<String> operands = new Stack<>();
        Stack<String> operators = new Stack<>();

        String[] inputValues = input.split("\\s");

        for (String value : inputValues) {
            if (value.equals("(")) {

            } else if (value.equals("+")
                    || value.equals("-")
                    || value.equals("*")
                    || value.equals("/")) {
                operators.push(value);
            } else if (value.equals(")")) {
                String operator = operators.pop();
                String value2 = operands.pop();
                String value1 = operands.pop();

                String subExpression = "( " + value1 + " " + operator + " " + value2 + " )";
                operands.push(subExpression);
            } else {
                operands.push(value);
            }
        }

        return operands.pop();
    }

    public static void main(String[] args) {
        String input = args[0];
        StdOut.println(getInfixExpression(input));
    }
}
