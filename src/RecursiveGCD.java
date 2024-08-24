// Find the GCD of 2 positive integers
import java.util.ArrayList;
import java.util.Scanner;

public class RecursiveGCD {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter first positive number: ");
        int a = input.nextInt();

        System.out.print("Enter second positive number: ");
        int b = input.nextInt();

        System.out.println("GCD of " + a + " and " + b + " is " + gcd(a, b));
    }



    public static int gcd(int a, int b) {
        int remainder = a % b;

        if (remainder == 0) { // Base case
            return b;
        } else {
            return gcd(b, remainder); // Recursive call
        }
    }
}
