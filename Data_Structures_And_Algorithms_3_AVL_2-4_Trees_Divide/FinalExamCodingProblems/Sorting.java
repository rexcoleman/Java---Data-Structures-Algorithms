import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your implementation of various divide & conquer sorting algorithms.
 */

public class Sorting {



    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(kn)
     * And a best case running time of: O(kn)
     *
     * Feel free to make an initial O(n) passthrough of the array to
     * determine k, the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * You may use an ArrayList or LinkedList if you wish, but it should only
     * be used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with merge sort. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    public static void lsdRadixSort(int[] arr) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (arr == null || arr.length <= 1) {
            return;
        }

        // Find the maximum magnitude number to determine number of digits
        // Handle Integer.MIN_VALUE case specially since Math.abs(Integer.MIN_VALUE) overflows
        int maxMagnitude = 0;
        boolean hasMinValue = false;
        
        for (int num : arr) {
            if (num == Integer.MIN_VALUE) {
                hasMinValue = true;
            } else {
                maxMagnitude = Math.max(maxMagnitude, Math.abs(num));
            }
        }
        
        // If we found Integer.MIN_VALUE, it has the largest magnitude
        if (hasMinValue) {
            maxMagnitude = Integer.MAX_VALUE;
        }

        // Create buckets (19 buckets for -9 to 9)
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }

        // Start with 1 to get least significant digit
        int exp = 1;

        // Continue until we process the most significant digit
        while (maxMagnitude / exp > 0) {
            // Place numbers in buckets based on current digit
            for (int num : arr) {
                int digit = (num / exp) % 10;  // Get current digit
                buckets[digit + 9].add(num);   // Map -9..9 to array index 0..18
            }

            // Collect numbers from buckets
            int index = 0;
            for (LinkedList<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    arr[index++] = bucket.removeFirst();
                }
            }

            // Move to next digit
            exp *= 10;
        }
    }

    /* Implement bubble sort.
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

    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
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
            if (midIndex != i) {
                T temp = arr[i];
                arr[i] = arr[midIndex];
                arr[minIndex] = temp;
            }
        }
    }


    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
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
