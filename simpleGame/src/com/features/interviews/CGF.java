package com.features.interviews;

import java.util.Arrays;

public class CGF {
    public static boolean areBracketsBalanced(String s)
    {
        int i = -1;
        char[] stack = new char[s.length()];
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[')
                stack[++i] = ch;
            else {
                if (i >= 0
                        && ((stack[i] == '(' && ch == ')')
                        || (stack[i] == '{' && ch == '}')
                        || (stack[i] == '[' && ch == ']')))
                    i--;
                else
                    return false;
            }
        }
        return i == -1;
    }

    public static void main(String[] args)
    {
        String expr = "([)]";

        // Function call
        if (areBracketsBalanced(expr))
            System.out.println("Balanced");
        else
            System.out.println("Not Balanced");
    }

    public static String solve(int clawPos, int[] boxes, boolean boxInClaw) {
        // Write your code here
        // To debug: System.err.println("Debug messages...");
        int totalBoxes = Arrays.stream(boxes).reduce(Integer::sum).getAsInt();
        int targetPos = 0;

        if (boxInClaw) {

            int[] targetStack = generateStack(boxes.length, totalBoxes + 1);

            // Move to place
            for (int i = 0; i < boxes.length; i++) {
                if (boxes[i] < targetStack[i]) {
                    targetPos = i;
                    break;
                }
            }

            if (clawPos == targetPos) return "PLACE";
            else if (clawPos < targetPos) return "RIGHT";
            else return "LEFT";

        } else {

            int[] targetStack = generateStack(boxes.length, totalBoxes);

            // Move to pick
            for (int i = 0; i < boxes.length; i++) {
                if (boxes[i] > targetStack[i]) {
                    targetPos = i;
                    break;
                }
            }

            if (clawPos == targetPos) return "PICK";
            else if (clawPos < targetPos) return "RIGHT";
            else return "LEFT";
        }

    }

    private  static int[] generateStack(int length, int totalBoxes) {
        int boxPerStack = totalBoxes / length;
        int[] newStack = new int[length];
        Arrays.fill(newStack, boxPerStack);

        int remainder = totalBoxes % length;
        if (remainder != 0) {
            for (int i = 0; i < remainder; i++) {
                newStack[i]++;
            }
        }
        return newStack;
    }
}
