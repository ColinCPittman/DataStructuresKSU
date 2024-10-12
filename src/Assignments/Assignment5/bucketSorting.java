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

    private static int[] bucketSort(int[] arr) {

        // Return if array is only 1 or empty.
        if (arr.length < 2) return arr;


        // Determine max based on absolute values
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Math.abs(arr[i]) > max) {
                max = Math.abs(arr[i]);
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
        for (int bucketNum = 0, factorOfTen = 1; bucketNum < digitsInMax; bucketNum++, factorOfTen *= 10) {

            for (int j = arr.length - 1; j >= 0; j--) {

                // I will only consider the absolute value of the number and handle negatives when updating the array after each pass.
                int landingBucket = (Math.abs(arr[j]) / factorOfTen) % 10;
                buckets[landingBucket].add(arr[j]);
            }

            // Next section is to update the array with the new values after this pass.
            // I realized in testing that this needed to account for positive and negative values.
            // Negative numbers are be mixed with positive numbers within buckets.
            // So the approach here is just to repopulate the negative values first, then positive.

            int updateIndex = 0; // counter used to insert the new values into the original array

            // When considering negative numbers, larger buckets actually indicate smaller numbers.
            // So here I'll flip the algorithm shown in class and pop values from the largest bucket first.

            //negative numbers
            for (int k = buckets.length - 1; k >= 0; k--) { // loop from bucket 9 to 0

                if (!buckets[k].isEmpty()) {


                    for (int l = buckets[k].size() - 1; l >= 0; l--) { // loop down/backward through the list starting from the end.

                        if (buckets[k].get(l) < 0) { // only place negative numbers found

                            arr[updateIndex++] = buckets[k].get(l);
                        }
                    }
                }
            }

            // positive numbers
            for (int k = 0; k < buckets.length; k++) { //loop from bucket 0 to 9

                if (!buckets[k].isEmpty()) {

                    for (int l = buckets[k].size() - 1; l >= 0; l--) { // loop down/backward through the list starting from the end

                        if (buckets[k].get(l) >= 0) { // only place positive numbers found

                            arr[updateIndex++] = buckets[k].get(l);
                        }
                    }
                }
            }

            //clear buckets for next pass
            for (ArrayList<Integer> bucket : buckets) {
                bucket.clear();
            }

        }
        return arr;
    }
}