package Assignments.Assignment3;

import java.util.ArrayList;
import java.util.EmptyStackException;

// Name: Colin Pittman
// Class: CS 3305/Section 4
// Term: Fall 2024
// Instructor: Umama Tasnim
public class isBalanced {
    /**
     * Simple ArrayList implementation of a stack. Utilizing the ArrayList functions to add
     * and remove elements to the end of the list.
     */
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

    /**
     * Assesses if the expression contains proper use of parentheses, brackets, or curly braces.
     * That is, a closing symbol of }, ), or ] must always match the most recent corresponding opening symbol of {, (, or [
     * encountered in the string when read left to right, and each opening symbol requires a closing symbol.
     * @param expression String value to be assessed for parenthetical balance.
     * @return true if the expression properly opens and closes parentheses, brackets, or braces.
     */
    public static boolean isBalanced(String expression) {
        Stack openingSymbolsStack = new Stack();
        //Iterating over the characters in the string left to right, storing opening symbols in a stack.
        //Any closing symbols encountered require there to be a matching opening symbol at the top of the stack, else it is unbalanced.
        //This method ignores characters that aren't (), {}, and [] so it works for equations with variables and operators too.
        for (int i = 0; i < expression.length(); i++) {

            String currentChar = Character.toString(expression.charAt(i));
            boolean currentCharIsOpeningSymbol = currentChar.equals("(") || currentChar.equals("[") || currentChar.equals("{");
            boolean currentCharIsClosingSymbol = currentChar.equals(")") || currentChar.equals("]") || currentChar.equals("}");

            if (currentCharIsOpeningSymbol) {
                openingSymbolsStack.push(currentChar);

            } else if(currentCharIsClosingSymbol) {

                if(openingSymbolsStack.isEmpty()) {
                    return false;
                }

                switch (openingSymbolsStack.pop()) {
                    case "(" -> {
                        if (!currentChar.equals(")")) {
                            return false;
                        }
                    }
                    case "[" -> {
                        if (!currentChar.equals("]")) {
                            return false;
                        }
                    }
                    case "{" -> {
                        if (!currentChar.equals("}")) {
                            return false;
                        }
                    }
                }

            }
        }
            return openingSymbolsStack.isEmpty();
    }


    public static void main(String[] args) {
        System.out.println(String.format("%-7s ->\t Function output: %s", "(())", isBalanced("(())")));
        System.out.println(String.format("%-7s ->\t Function output: %s", "{{[]}}", isBalanced("{{[]}}")));
        System.out.println(String.format("%-7s ->\t Function output: %s", "()[]{}", isBalanced("()[]{}")));
        System.out.println(String.format("%-7s ->\t Function output: %s", "({[})", isBalanced("({[})")));
        System.out.println(String.format("%-7s ->\t Function output: %s", "}()[]", isBalanced("}()[]")));
        System.out.println(String.format("%-7s ->\t Function output: %s", "([]){}", isBalanced("([]){}")));
        System.out.println(String.format("%-7s ->\t Function output: %s", "([]){}[", isBalanced("([]){}[")));
        System.out.println(String.format("%-7s ->\t Function output: %s", "[[]", isBalanced("[[]")));
        System.out.println(String.format("%-7s ->\t Function output: %s", "[]]", isBalanced("[]]")));
        System.out.println(String.format("%-7s ->\t Function output: %s", "{}}", isBalanced("{}}")));
        System.out.println(String.format("%-7s ->\t Function output: %s", "())", isBalanced("())")));
        System.out.println(String.format("%-7s ->\t Function output: %s", "[(])", isBalanced("[(])")));
        System.out.println(String.format("%-7s ->\t Function output: %s", "[](){}", isBalanced("[](){}")));
        System.out.println(String.format("%-7s ->\t Function output: %s", "5*(8-4)", isBalanced("5*(8-4)")));
        System.out.println(String.format("%-7s ->\t Function output: %s", "5((8-4)", isBalanced("5((8-4)")));
        System.out.println(String.format("%-7s ->\t Function output: %s", "Empty", isBalanced("")));
    }
}
