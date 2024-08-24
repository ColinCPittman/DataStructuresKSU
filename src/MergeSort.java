public class MergeSort {

    public static void merge(int[] array, int left, int middle, int right) {
        int sizeLeft = middle - left + 1;
        int sizeRight = right - middle;

        int[] leftArray = new int[sizeLeft];
        int[] rightArray = new int[sizeRight];

        // Copy data to temporary arrays leftArray and rightArray
        for (int i = 0; i < sizeLeft; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < sizeRight; j++) {
            rightArray[j] = array[middle + 1 + j];
        }

        int indexLeft = 0;
        int indexRight = 0;
        int indexMerged = left;

        // Merge the temporary arrays back into array
        while (indexLeft < sizeLeft && indexRight < sizeRight) {
            if (leftArray[indexLeft] <= rightArray[indexRight]) {
                array[indexMerged] = leftArray[indexLeft];
                indexLeft++;
            } else {
                array[indexMerged] = rightArray[indexRight];
                indexRight++;
            }
            indexMerged++;
        }

        // Copy the remaining elements of leftArray, if any
        while (indexLeft < sizeLeft) {
            array[indexMerged] = leftArray[indexLeft];
            indexLeft++;
            indexMerged++;
        }

        // Copy the remaining elements of rightArray, if any
        while (indexRight < sizeRight) {
            array[indexMerged] = rightArray[indexRight];
            indexRight++;
            indexMerged++;
        }
    }

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6, 7};
        System.out.println("Given Array:");
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();

        mergeSort(array, 0, array.length - 1);

        System.out.println("\nSorted array:");
        for (int element : array) {
            System.out.print(element + " ");
        }
    }
}
