package Assignments.Assignment1;

import java.util.Scanner;

// Name: Colin Pittman
// Class: CS 3305/Section 4
// Term: Fall 2024
// Instructor: Umama Tasnim

public class PrintReverseColinPittman {
    private static String enteredString;
    private static String userTryAgainChoice;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean tryingAgain = true;
        while (tryingAgain) {
            System.out.print("\nEntered String: ");
            enteredString = sc.nextLine();
            System.out.print("Reversed String: ");
            printCharsReverse(enteredString);
            tryingAgain = promptUserToTryAgain();
        }
    }

    /**
     * Prints the last character of the string, then recursively calls itself using the input string with the last character excluded.
     * Method terminates when the string is empty.
     *
     * @param input string to be reversed.
     */
    public static void printCharsReverse(String input) {
        //base case, string has no chars
        if (input.length() == 0) {
            return;
        }

        System.out.print(input.charAt(input.length() - 1));
        printCharsReverse(input.substring(0, input.length() - 1));
    }

    /**
     * Method to prompt the user to try again and ensure the user enters either Y or N or else they will be re-prompted.
     *
     * @return false if user types "N", true if user types "Y".
     */
    private static boolean promptUserToTryAgain() {
        userTryAgainChoice = "default";
        while (!(userTryAgainChoice.equalsIgnoreCase("Y") || userTryAgainChoice.equalsIgnoreCase("N"))) {
            System.out.print("\nTry again (Y/N): ");
            userTryAgainChoice = sc.nextLine();
            if (userTryAgainChoice.equalsIgnoreCase("N")) {
                return false;
            }
        }
        return true;
    }


}
