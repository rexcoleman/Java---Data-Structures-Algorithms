import java.util.Comparator;

/**
 * Your implementation of various iterative sorting algorithms.
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * NOTE: You should implement bubble sort with the last swap optimization.
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (arr.length <= 1) {
            return;
        }
        
        // Keep track of the last index where a swap occurred
        int lastSwapped = arr.length - 1;
        
        // Continue until no swaps are needed (array is sorted)
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            int newLastSwapped = 0;
            
            // Only need to check up to the last swapped position
            // since elements after that are already in order
            for (int i = 0; i < lastSwapped; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    // Swap elements
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                    newLastSwapped = i;
                }
            }
            // Update the last swapped position
            lastSwapped = newLastSwapped;
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n^2)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (arr.length <= 1) {
            return;
        }
        
        for (int i = 0; i < arr.length - 1; i++) {
            // Find index of minimum element in unsorted portion
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            
            // Swap with first element of unsorted portion if needed
            if (minIndex != i) {
                T temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (arr.length <= 1) {
            return;
        }
        
        for (int i = 1; i < arr.length; i++) {
            T current = arr[i];
            int j = i - 1;
            
            // Shift elements right until we find current's correct position
            while (j >= 0 && comparator.compare(arr[j], current) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            
            // Place current in its correct position
            arr[j + 1] = current;
        }
    }
}