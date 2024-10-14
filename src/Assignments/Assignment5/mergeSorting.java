package Assignments.Assignment5;

import java.util.Arrays;

public class mergeSorting {
    public static void main(String[] args) {
        int[] arr1 = {120, 11, 1345, 50, 6};
        System.out.printf("\nBefore: %-32s", Arrays.toString(arr1));
        mergeSort(arr1);
        System.out.printf(" After: %-30s%n", Arrays.toString(arr1));

        int[] arr2 = {};
        System.out.printf("Before: %-32s", Arrays.toString(arr2));
        mergeSort(arr2);
        System.out.printf(" After: %-30s%n", Arrays.toString(arr2));

        int[] arr3 = {10};
        System.out.printf("Before: %-32s", Arrays.toString(arr3));
        mergeSort(arr3);
        System.out.printf(" After: %-30s%n", Arrays.toString(arr3));

        int[] arr4 = {-10, 11, -1, -3, 199, -20, -5};
        System.out.printf("Before: %-32s", Arrays.toString(arr4));
        mergeSort(arr4);
        System.out.printf(" After: %-30s%n", Arrays.toString(arr4));
    }

    /**
     * Sorts the array by recursively splitting array in halves, then elements are
     * merged back into the array in ascending order.
     * @param array to be merged sorted.
     */
    public static void mergeSort(int[] array) {
        if (array.length > 1) {
            int midpoint = array.length / 2;

            int[] leftHalf = Arrays.copyOfRange(array, 0, midpoint);
            mergeSort(leftHalf);

            int[] rightHalf = Arrays.copyOfRange(array, midpoint, array.length);
            mergeSort(rightHalf);

            merge(leftHalf, rightHalf, array);
        }
    }

    /**
     * Traverses arrayA and arrayB and inserts their elements into mergedArray in ascending order.
     *
     * @param arrayA first array to be merged
     * @param arrayB second array to be merged.
     * @param mergedArray array to have the elements merged into.
     */
    private static void merge(int[] arrayA, int[] arrayB, int[] mergedArray) {
        int arrayAPointer = 0;
        int arrayBPointer = 0;
        int mergedArrayPointer = 0;

        // traverse each array, insert and increment the pointer of the lowest values found between the two arrays.
        // stop when one of the pointers reaches the end of the list.
        while(arrayAPointer < arrayA.length && arrayBPointer < arrayB.length) {
            if(arrayA[arrayAPointer] <= arrayB[arrayBPointer]) {
                mergedArray[mergedArrayPointer++] = arrayA[arrayAPointer++];
            } else{
                mergedArray[mergedArrayPointer++] = arrayB[arrayBPointer++];
            }
        }

        //traverse the remainder of the two remaining arrays to populate the remaining elements of the array whose pointer has
        //not yet reached the end of the list.
        while(arrayAPointer < arrayA.length) {
            mergedArray[mergedArrayPointer++] = arrayA[arrayAPointer++];
        }
        while (arrayBPointer < arrayB.length){
            mergedArray[mergedArrayPointer++] = arrayB[arrayBPointer++];
        }

    }
}


