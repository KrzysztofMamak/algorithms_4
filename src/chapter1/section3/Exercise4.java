package chapter1.section3;

import java.util.Scanner;
import java.util.Stack;

public class Exercise4 {
    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        boolean result = true;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == ')') {
                if (!(stack.pop() == '(')) {
                    result = false;
                    break;
                }
            } else if (c == ']') {
                if (!(stack.pop() == '[')) {
                    result = false;
                    break;
                }
            } else if (c == '}') {
                if (!(stack.pop() == '{')) {
                    result = false;
                    break;
                }
            } else {
                stack.push(c);
            }
        }
        System.out.println(result);
        scanner.close();
    }
}
