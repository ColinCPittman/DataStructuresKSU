package Assignments.Assignment3;
// Name: Colin Pittman
// Class: CS 3305/Section 4
// Term: Fall 2024
// Instructor: Umama Tasnim

import java.util.Scanner;
import java.util.Stack;

public class testStack {
    public static void main(String[] args) {

        Stack<Integer> integerStack = new Stack<>();
        Stack<Double> doubleStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        String inputString;
        while (true) {
            System.out.println("\n" + menu);
            int choice = promptUserForInteger("Enter your choice: ");

            switch (choice) {
                case testTopToBottom:
                    while (true) {
                        inputString = promptUserForString("Testing function topToBottom:\n" +
                                "Enter integers to push onto the stack (space-separated):");
                        if (hasOnlyIntegers(inputString.split(" "))) {
                            break;
                        } else {
                            System.out.println("Invalid input, try again.");
                        }
                        //populating the ints to the stack.
                        for (String string : inputString.split(" ")) {
                            integerStack.push(Integer.parseInt(string));
                        }

                        System.out.print("Stack content: ");
                        printStack(integerStack);
                        System.out.println("\nFunction output: ");
                        topToBottom(integerStack);
                    }


                    break;

                case testBottomToTop:
                    while (true) {
                        inputString = promptUserForString("Testing function bottomToTop:\n" +
                                "Enter doubles to push onto the stack (space-separated):");
                        if (hasOnlyDoubles(inputString.split(" "))) {
                            break;
                        } else {
                            System.out.println("Invalid input, try again.");
                        }
                    }
                    break;

                case testFlipStack:
                    inputString = promptUserForString("Testing function flipStack:\n" +
                            "Enter strings to push onto the stack (space-separated):");

                    break;
                case testSearchStack:
                    while (true) {
                        inputString = promptUserForString("Testing function searchStack:\n" +
                                "Enter integers to push onto the stack (space-separated):");
                        if (hasOnlyIntegers(inputString.split(" "))) {
                            break;
                        } else {
                            System.out.println("Invalid input, try again.");
                        }
                    }
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
        while(!stack.isEmpty()) {
            element = stack.pop();
            System.out.print(element + " ");
            temp.push(element);
        }
        while(!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    public static void topToBottom(Stack<Integer> stack) {

    }

    public static void bottomToTop(Stack<Double> stack) {
        // Method implementation will go here
    }

    public static Stack<String> flipStack(Stack<String> stack) {
        // Method implementation will go here
        return stack; // Placeholder return statement
    }

    public static void populateStack(String content) {

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
        // Method implementation will go here
        return false; // Placeholder return statement
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