import java.util.Comparator;

/**
 * Your implementation of Merge Sort.
 */
public class Sorting {

    /**
     * Implement merge sort.
     *
     * It should be: out-of-place stable not adaptive
     *
     * Have a worst case running time of: O(n log n) And a best case running time
     * of: O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: You may need to create a helper method that merges two arrays back into
     * the original T[] array. If two data are equal when merging, think about which
     * subarray you should pull from first.
     *
     * You may assume that the passed in array and comparator are both valid and
     * will not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        // Calculate midpoint
        int midIndex = arr.length / 2;
        
        // Create left and right arrays
        T[] left = (T[]) new Object[midIndex];
        T[] right = (T[]) new Object[arr.length - midIndex];
        
        // Fill left array
        for (int i = 0; i < midIndex; i++) {
            left[i] = arr[i];
        }
        
        // Fill right array
        for (int i = midIndex; i < arr.length; i++) {
            right[i - midIndex] = arr[i];
        }
        
        // Recursive calls
        mergeSort(left, comparator);
        mergeSort(right, comparator);
        
        // Merge step
        int i = 0;  // left array index
        int j = 0;  // right array index
        int k = 0;  // main array index
        
        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
    
        // Copy remaining elements of left array
        while (i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }
        
        // Copy remaining elements of right array
        while (j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }


        /**
     * Implement selection sort.
     *
     * It should be: in-place unstable not adaptive
     *
     * Have a worst case running time of: O(n^2) And a best case running time of:
     * O(n^2)
     *
     * You may assume that the passed in array and comparator are both valid and
     * will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        for (int n = arr.length - 1; n > 0; n--) {
            // Initialize maxIndex to first element in unsorted portion
            int maxIndex = n;

            // Find the maximum element in the unsorted portion
            for (int i = 0; i < n; i++) {
                if (comparator.compare(arr[i], arr[maxIndex]) > 0) {
                    maxIndex = i;
                }
            }

            // Only swap if necessary
            if (maxIndex != n) {
                T temp = arr[n];
                arr[n] = arr[maxIndex];
                arr[maxIndex] = temp;
            }
        }
    }
}