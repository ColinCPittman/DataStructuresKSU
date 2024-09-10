package Assignments.Assignment3;
// Name: Colin Pittman
// Class: CS 3305/Section 4
// Term: Fall 2024
// Instructor: Umama Tasnim

import java.util.Scanner;
import java.util.Stack;

public class testStack {
    public static final String menu = """
            
            -----MAIN MENU-----
            1. Test function topToBottom with integer stack
            2. Test function bottomToTop with double stack
            3. Test function flipStack with string stack
            4. Test function searchStack with integer stack
            5. Exit program
            """;
    static final Scanner scanner = new Scanner(System.in);
    static final int testTopToBottom = 1;
    static final int testBottomToTop = 2;
    static final int testFlipStack = 3;
    static final int testSearchStack = 4;
    static final int exit = 5;

    public static void main(String[] args) {

        Stack<Integer> integerStack = new Stack<>();
        Stack<Double> doubleStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        String inputString;
        while (true) {
            integerStack.clear();
            doubleStack.clear();
            stringStack.clear();
            System.out.println("\n" + menu);
            int choice = promptUserForInteger("Enter your choice: ");

            switch (choice) {
                case testTopToBottom:
                    while (true) {
                        inputString = promptUserForString("Testing function topToBottom:\n" +
                                "Enter integers to push onto the stack (space-separated):");

                        if (hasOnlyIntegers(inputString.split(" "))) {

                            for (String string : inputString.split(" ")) {
                                integerStack.push(Integer.parseInt(string));
                            }

                            break;
                        } else {
                            System.out.println("Invalid input, try again.");
                        }
                    }

                    System.out.print("Stack content: ");
                    printStack(integerStack);

                    System.out.print("\nFunction output: ");
                    topToBottom(integerStack);

                    break;

                case testBottomToTop:
                    while (true) {
                        inputString = promptUserForString("Testing function bottomToTop:\n" +
                                "Enter doubles to push onto the stack (space-separated):");

                        if (hasOnlyDoubles(inputString.split(" "))) {
                            for (String string : inputString.split(" ")) {
                                doubleStack.push(Double.parseDouble(string));
                            }
                            break;
                        } else {
                            System.out.println("Invalid input, try again.");
                        }
                    }


                    System.out.print("Stack content: ");
                    printStack(doubleStack);
                    System.out.print("\nFunction output: ");
                    bottomToTop(doubleStack);

                    break;

                case testFlipStack:
                    inputString = promptUserForString("Testing function flipStack:\n" +
                            "Enter strings to push onto the stack (space-separated):");
                    for (String string : inputString.split(" ")) {
                        stringStack.push(string);
                    }

                    System.out.print("Stack content before calling flipStack: ");
                    printStack(stringStack);
                    System.out.print("\nStack content after calling flipStack: ");
                    stringStack = flipStack(stringStack);
                    printStack(stringStack);

                    break;

                case testSearchStack:
                    while (true) {
                        inputString = promptUserForString("Testing function searchStack:\n" +
                                "Enter integers to push onto the stack (space-separated):");

                        if (hasOnlyIntegers(inputString.split(" "))) {
                            for (String string : inputString.split(" ")) {
                                integerStack.push(Integer.parseInt(string));
                            }
                            break;
                        } else {
                            System.out.println("Invalid input, try again.");
                        }
                    }

                    System.out.print("Stack content: ");
                    printStack(integerStack);
                    int targetInput = promptUserForInteger("\nEnter target value to search for: ");
                    System.out.print("Function output: ");
                    System.out.println(searchStack(integerStack, targetInput));
                    break;

                case exit:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static <E> void printStack(Stack<E> stack) {
        Stack<E> temp = new Stack<>();
        E element;
        while (!stack.isEmpty()) {
            element = stack.pop();
            System.out.print(element + " ");
            temp.push(element);
        }
        while (!temp.isEmpty()) {

            stack.push(temp.pop());
        }
    }

    public static void topToBottom(Stack<Integer> stack) {
        Stack<Integer> temp = new Stack<>();
        int element;
        while (!stack.isEmpty()) {
            element = stack.pop();
            System.out.print(element + " ");
            temp.push(element);
        }
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    public static void bottomToTop(Stack<Double> stack) {

        Stack<Double> temp = new Stack<>();
        Double element;
        while (!stack.isEmpty()) {

            temp.push(stack.pop());
        }
        while (!temp.isEmpty()) {
            element = temp.pop();
            System.out.print(element + " ");
            stack.push(element);
        }
    }

    public static Stack<String> flipStack(Stack<String> stack) {
        // Method implementation will go here
        Stack<String> temp = new Stack<>();
        String element;
        while (!stack.isEmpty()) {

            temp.push(stack.pop());
        }
        stack = temp;
        return stack;
    }

    public static boolean hasOnlyIntegers(String[] inputArray) {
        for (String string : inputArray) {
            try {
                int value = Integer.parseInt(string);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasOnlyDoubles(String[] inputArray) {
        for (String string : inputArray) {
            try {
                double value = Double.parseDouble(string);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public static boolean searchStack(Stack<Integer> stack, int target) {
        Boolean result = false;
        Stack<Integer> temp = new Stack<Integer>();
        int element;
        while (!stack.isEmpty()) {
            element = stack.pop();
            if (element == target) {
                result = true;
            }
            temp.push(element);
        }
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
        return result;
    }

    /**
     * Prompts the user with the provided string then gets a string input from the user.
     *
     * @param prompt message to be displayed to the user before waiting for a string input.
     * @return the string entered by the user.
     */
    public static String promptUserForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Displays the given prompt message and then gets a valid integer from the user.
     *
     * @param prompt message to be displayed to the user before waiting for an integer input.
     * @return the integer entered by the user.
     */
    private static int promptUserForInteger(String prompt) {
        int input = 0;
        while (true) {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception E) {
                System.out.println("Invalid input, please try again.");
            }
        }
        return input;
    }
}