package Assignments.Assignment5;

import java.util.ArrayList;
import java.util.Arrays;

public class bucketSorting {

    public static void main(String[] args) {

        int[] arr1 = {120, 11, 1345, 50, 6};
        System.out.printf("\nBefore: %-32s After: %-30s%n", Arrays.toString(arr1), Arrays.toString(bucketSort(arr1)));

        int[] arr2 = {};
       System.out.printf("Before: %-32s After: %-30s%n", Arrays.toString(arr2), Arrays.toString(bucketSort(arr2)));

        int[] arr3 = {10};
       System.out.printf("Before: %-32s After: %-30s%n", Arrays.toString(arr3), Arrays.toString(bucketSort(arr3)));

        int[] arr4 = {-10, 11, -1, -3, 199, -20, -5};
        System.out.printf("Before: %-32s After: %-30s%n", Arrays.toString(arr4), Arrays.toString(bucketSort(arr4)));
    }

    private static int[] bucketSort(int[] inputArray) {

        // Return if array is only 1 or empty.
        if (inputArray.length < 2) return inputArray;


        // Determine max based on absolute values
        int max = 0;
        for (int i = 0; i < inputArray.length; i++) {
            if (Math.abs(inputArray[i]) > max) {
                max = Math.abs(inputArray[i]);
            }
        }

        // Doing an ArrayList implementation of buckets to practice a different approach from what we covered in class.
        ArrayList<Integer>[] buckets = new ArrayList[10];

        // Initialize buckets next.
        for (int b = 0; b < buckets.length; b++) {
            buckets[b] = new ArrayList<>();
        }

        int digitsInMax = Integer.toString(max).length();

        // This approach is to divide by i*10, then mod 10 to isolate the value in the position.
        // This algorithm reads the digits right to left in each int in the array.
        for (int digitPos = 0, factorOfTen = 1; digitPos < digitsInMax; digitPos++, factorOfTen *= 10) {

            for (int intPointer = inputArray.length - 1; intPointer >= 0; intPointer--) {

                // I will only consider the absolute value of the number and handle negatives when updating the array after each pass.
                int landingBucket = (Math.abs(inputArray[intPointer]) / factorOfTen) % 10;
                buckets[landingBucket].add(inputArray[intPointer]);
            }

            // Next section is to update the array with the new values after this pass.
            // Negative numbers are mixed with positive numbers within buckets.
            // So the approach here is just to repopulate the negative values first, then positive.

            int updatePointer = 0; // will be used insert the new values into the original array and increment

            // When considering negative numbers, larger buckets actually indicate smaller numbers.
            // So to remain consistent with the sort order, we have to flip the algorithm and pop values from the 9 bucket first.

            //negative numbers
            for (int bucketPointer = buckets.length - 1; bucketPointer >= 0; bucketPointer--) { // loop from bucket 9 to 0

                if (!buckets[bucketPointer].isEmpty()) {


                    for (int intPointer = buckets[bucketPointer].size() - 1; intPointer >= 0; intPointer--) { // loop down/backward through the list starting from the end.

                        if (buckets[bucketPointer].get(intPointer) < 0) { // only place negative numbers found

                            inputArray[updatePointer++] = buckets[bucketPointer].get(intPointer);
                        }
                    }
                }
            }

            // positive numbers
            for (int bucketPointer = 0; bucketPointer < buckets.length; bucketPointer++) { //loop from bucket 0 to 9

                if (!buckets[bucketPointer].isEmpty()) {

                    for (int intPointer = buckets[bucketPointer].size() - 1; intPointer >= 0; intPointer--) { // loop down/backward through the list starting from the end

                        if (buckets[bucketPointer].get(intPointer) >= 0) { // only place non-negative numbers found

                            inputArray[updatePointer++] = buckets[bucketPointer].get(intPointer);
                        }
                    }
                }
            }

            //clear buckets for next pass
            for (ArrayList<Integer> bucket : buckets) {
                bucket.clear();
            }

        }
        return inputArray;
    }
}