package ChapterExcersices;

import java.math.BigInteger;
import java.util.Scanner;

public class Ch18_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number to computer the factorial");
        BigInteger userInput = new BigInteger(sc.nextLine());
        System.out.println(factorial(userInput));
    }
    public static BigInteger factorial(BigInteger input) {
        if(input.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        }
        else {
            return input.multiply(factorial(input.subtract(BigInteger.ONE)));
        }
    }
}
