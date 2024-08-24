package Assignments.Assignment1;

import java.util.Arrays;
import java.util.Scanner;

// Name: Colin Pittman
// Class: CS 3305/Section 4
// Term: Fall 2024
// Instructor: Umama Tasnim
public class AverageGradeColinPittman {
    private static double average;
    private static int[] gradesArray;
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean tryingAgain = true;
        while (tryingAgain) {
            gradesArray = new int[promptUserForInteger("\nEnter class size: ")];
            promptUserForGrades(gradesArray);
            average = computeAverage(gradesArray);
            printResults();
            tryingAgain = promptUserToTryAgain();
        }
    }

    /**
     * Recursively computes the average by traversing the array and reducing it size until the array is empty.
     * Average is computed recursively as the sum of ((average of the first n-1 elements) * (n-1)) and
     * the nth element, then dividing the sum by n.
     * @param array array of grades to traverse.
     * @return average of each integer in the array grades.
     */
    private static Double computeAverage(int[] array) {
        //Empty array, should not occur, undefined, throw exception
        if(array.length == 0) {
           throw new ArithmeticException();
        }
        //base case, only 1 element in array returns its value
        if(array.length == 1) {
            return array[0] + 0.0;
        }
        return ((computeAverage(Arrays.copyOfRange(array,0,array.length - 1)) * (array.length - 1)) + array[array.length - 1]) / array.length;
    }

    /**
     * Displays the class size, grades, and average.
     */
    private static void printResults() {
        System.out.print("\nClass size: " + gradesArray.length
                + "\nEntered grades: " + Arrays.toString(gradesArray)
                + "\nClass average: ");
        System.out.printf("%.2f", average);
    }


    /**
     * Method to prompt the user to try again and ensure the user enters either Y or N or else they will be re-prompted.
     *
     * @return false if user types "N", true if user types "Y".
     */
    private static boolean promptUserToTryAgain() {
        String userTryAgainChoice = "default";
        while (!(userTryAgainChoice.equalsIgnoreCase("Y") || userTryAgainChoice.equalsIgnoreCase("N"))) {
            System.out.print("\nTry again (Y/N): ");
            userTryAgainChoice = sc.nextLine();
            if (userTryAgainChoice.equalsIgnoreCase("N")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prompts the user to enter a grade for each element in the given array and saves
     * the user input in corresponding index of the array (index 0 is grade 1).
     */
    private static void promptUserForGrades(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = promptUserForInteger("Enter grade " + (i + 1) + ": ");
        }
    }

    /**
     * Displays the given prompt message and then gets a valid integer from the user.
     * @param prompt message to be displayed to the user before waiting for an integer input.
     * @return the integer entered by the user.
     */
    private static int promptUserForInteger(String prompt) {
        int input = 0;
        while (true) {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception E) {
                System.out.println("Invalid response, please try again.");
            }
    }
        return input;
    }
}
