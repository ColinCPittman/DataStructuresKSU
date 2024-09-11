package Assignments.Assignment3;
// Name: Colin Pittman
// Class: CS 3305/Section 4
// Term: Fall 2024
// Instructor: Umama Tasnim

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class testStack {

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n" + menu);
            int choice = promptUserForInteger("Enter your choice: ");

            switch (choice) {
                case testTopToBottom:
                    handleCaseTestTopToBottom();
                    break;

                case testBottomToTop:
                    handleCaseTestBottomToTop();
                    break;

                case testFlipStack:
                    handleCaseTestFlipStack();
                    break;

                case testSearchStack:
                    handleCaseTestSearchStack();
                    break;

                case exit:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    /**
     * Handles all the logic required to prompt the user for input and display the result for this test.
     */
    private static void handleCaseTestSearchStack() {
        Stack<Integer> integerStack = new Stack<>();
        String inputString;
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
    }
    /**
     * Handles all the logic required to prompt the user for input and display the result for this test.
     */
    private static void handleCaseTestFlipStack() {
        Stack<String> stringStack = new Stack<>();
        String inputString;
        inputString = promptUserForString("Testing function flipStack:\n" +
                "Enter strings to push onto the stack (space-separated):");
        for (String string : inputString.split(" ")) {
            stringStack.push(string);
        }

        System.out.print("Stack content before calling flipStack: ");
        printStack(stringStack);
        System.out.print("\nStack content after calling flipStack: ");
        flipStack(stringStack);
        printStack(stringStack);
    }
    /**
     * Handles all the logic required to prompt the user for input and display the result for this test.
     */
    private static void handleCaseTestBottomToTop() {
        Stack<Double> doubleStack = new Stack<>();
        String inputString;
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
    }
    /**
     * Handles all the logic required to prompt the user for input and display the result for this test.
     */
    private static void handleCaseTestTopToBottom() {
        Stack<Integer> integerStack = new Stack<>();
        String inputString;
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
    }

    /**
     * This method is called to display the stack content for the user prior to displaying the result.
     * Values from the stack are printed top to bottom, with the top element being printed first.
     * This is default behavior of printing top to bottom is to imitate the examples provided
     * where it shows that the content is displayed to the user top to bottom
     * prior to showing the results of a function on the next line.
     *
     * @param stack the stack to be printed out.
     * @param <E>
     */
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

    /**
     * Values from the stack are printed top to bottom, with the top element being printed first.
     *
     * @param stack the stack to be printed out.
     */
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

    /**
     * Values from the stack are printed bottom to top, with the bottom element being printed first.
     * @param stack the stack to be printed out.
     */
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

    /**
     * Takes a stack of strings as a parameter and returns the same stack object with its content flipped (re-arranged).
     *
     * @param stack stack to be reversed.
     * @return the input stack with its contents flipped.
     */
    public static Stack<String> flipStack(Stack<String> stack) {

        String[] items = new String[stack.size()];

        //values are pushed into an array because the first element pushed back into the stack needs to be the first element that was popped off.
        //An array accomplishes this if we begin popping values into the array at index zero, followed by
        //pushing values back into the stack beginning from index zero of the array.
        for (int i = 0; i < items.length; i++) {
            items[i] = stack.pop();
        }
        for (int i = 0; i < items.length; i++) {
            stack.push(items[i]);
        }
        return stack;
    }

    /**
     * A simple method to verify an array string contains all characters that can be parsed into integers for validation.
     *
     * @param inputArray string array to check.
     * @return true if every string in the string array is parsable as an integer.
     */
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

    /**
     * A simple method to verify an array string contains all characters that can be parsed into doubles for validation.
     *
     * @param inputArray string array to check.
     * @return true if it every string is parsable as a double.
     */
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

    /**
     * Pops each element off the stack and checks if it matches our target, if it does it sets a flag.
     * Then continues popping until the end, and re-pushes it all back to restore
     *
     * @param stack stack to be searched for target
     * @param target value to search for in stack
     * @return true if the value is present in the stack.
     */
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
    //these variables below are tucked away at the bottom because they only exist to increase the readability of the main body.
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
}