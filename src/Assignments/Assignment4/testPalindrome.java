package Assignments.Assignment4;

// Name: Colin Pittman
// Class: CS 3305/Section 4
// Term: Fall
// Instructor: Umama Tasnim
// Assignment: 4

import java.util.LinkedList;
import java.util.Deque;
import java.util.Scanner;

public class testPalindrome {


    public static void main(String[] args) {
        while (true) {
            System.out.println("\n" + menu);
            int choice = promptUserForInteger("Enter option number: ");
            System.out.println("\nYou Selected: Option " + choice);
            switch (choice) {
                case testCharPalindrome:
                    System.out.print("Judgement: " + (isPalindromeByChar(promptUserForString("You entered: ")) ? "Palindrome" : "Not Palindrome"));
                    break;

                case testWordPalindrome:
                    System.out.print("Judgement: " + (isPalindromeByWord(promptUserForString("You entered: ")) ? "Palindrome" : "Not Palindrome"));
                    break;

                case exit:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    /**
     * Looks at the characters in a string and determines if it is a palindrome.
     *
     * @param input string to be assessed for palindrome.
     * @return true if the words are symmetric
     */
    public static boolean isPalindromeByChar(String input) {
        Deque<Character> charQueue = new LinkedList<>();

        //push add characters to the queue
        for (int i = 0; i < input.length(); i++) {
            if (Character.isLetter(input.charAt(i))) {
                charQueue.add(Character.toLowerCase(input.charAt(i)));
            }
        }
        //Check first and last chars, ignoring case, and shave them off if they match, else return false.
        while (charQueue.size() > 1) {

            if (!charQueue.getFirst().equals(charQueue.getLast())) {
                return false;
            } else {
                charQueue.removeFirst();
                charQueue.removeLast();
            }
        }
        return true;
    }

    /**
     * Looks at the input string and determines if the words are symmetric.
     *
     * @param input string to be assessed for word symmetry
     * @return true if the words are symmetric
     */
    public static boolean isPalindromeByWord(String input) {
        Deque<String> wordQueue = new LinkedList<>();

        //split by the words, ignores symbols but includes numbers in a word if they aren't separated by spaces
        String[] words = input.split(" ");
        for (int i = 0; i < words.length; i++) {
            wordQueue.add(words[i].replaceAll("[^a-zA-Z0-9]", "")); //remove non-alphabet characters except numbers. still need to learn regex, had to google this one
        }
        while (wordQueue.size() > 1) {

            if (!wordQueue.getFirst().equalsIgnoreCase(wordQueue.getLast())) {
                return false;
            } else {
                wordQueue.removeFirst();
                wordQueue.removeLast();
            }
        }
        return true;
    }

    /**
     * Displays the given prompt message and then gets a valid integer from the user.
     *
     * @param prompt The message to display to the user.
     * @return The integer entered by the user.
     */
    public static int promptUserForInteger(String prompt) {
        int input = 0;
        while (true) {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a valid integer.");
            }
        }
        return input;
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

    //these options are kept hidden below because they only serve to make the code more readable
    public static final String menu = """     
            
            -----MAIN MENU-----
            1. Test character-by-character palindrome
            2. Test word-by-word palindrome
            3. Exit program
            """;

    // Final integers representing menu options for better readability
    static final int testCharPalindrome = 1;
    static final int testWordPalindrome = 2;
    static final int exit = 3;
    static final Scanner scanner = new Scanner(System.in);

}
