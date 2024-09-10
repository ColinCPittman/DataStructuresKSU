package Assignments.Assignment3;

import java.util.ArrayList;
import java.util.EmptyStackException;

// Name: Colin Pittman
// Class: CS 3305/Section 4
// Term: Fall 2024
// Instructor: Umama Tasnim
public class isBalanced {
    private static class Stack {
        // Stack methods (push, pop, isEmpty, peek) will go here
        ArrayList<String> list;
        int size;

        public Stack() {
            list = new ArrayList<String>();
            size = 0;
        }

        public void push(String string) {
            list.add(string);
            size++;
        }

        public String pop() {
            if (size == 0) {
                throw new EmptyStackException();
            }
            String temp = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            size--;
            return temp;
        }

        public String peek() {
            if (size == 0) {
                throw new EmptyStackException();
            }
            return list.get(list.size() - 1);
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    public static boolean isBalanced(String expression) {
        Stack newStack = new Stack();
        Stack comparisonStack = new Stack();
        for (int i = 0; i < expression.length(); i++) {
            newStack.push(Character.toString(expression.charAt(i)));
        }
        int midpoint = expression.length() / 2;

        for (int i = 0; i < midpoint; i++) {
            comparisonStack.push(newStack.pop());
        }
        //this is to ignore the middle element if it is odd numbers.
        if (expression.length() % 2 == 1) {
            newStack.pop();
        }
        //both stacks will now have the same size, so this comparison never runs into an empty stack exception.

        while (!newStack.isEmpty()) {
                //I originally coded a function to check for palindromes for practice, but this assignment really only needs to check [], {} and (). I adapted it to check for both.
                String newStackString = newStack.pop();
                String comparisonStackString = comparisonStack.pop();
            if (!newStackString.equalsIgnoreCase(comparisonStackString)) {
                if (newStackString.equals("{")) {
                    if (!comparisonStackString.equals("}")) {
                        return false;
                    }
                } else if (newStackString.equals("[")) {
                    if (!comparisonStackString.equals("]")) {
                        return false;
                    }
                } else if (newStackString.equals("(")) {
                    if (!comparisonStackString.equals(")")) {
                        return false;
                    }
                }
            }


        }

        return true;
    }

    public static void main(String[] args) {
        // Test cases will go here
        System.out.println(isBalanced("(())"));
        System.out.println(isBalanced("{{[]}}"));
        System.out.println(isBalanced("()[]{}"));
        System.out.println(isBalanced("({[})"));
    }
}
