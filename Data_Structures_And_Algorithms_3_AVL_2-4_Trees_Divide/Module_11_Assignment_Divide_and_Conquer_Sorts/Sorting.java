import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your implementation of various divide & conquer sorting algorithms.
 */

public class Sorting {

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(n log n)
     * And a best case running time of: O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: You may need to create a helper method that merges two arrays
     * back into the original T[] array. If two data are equal when merging,
     * think about which subarray you should pull from first.
     *
     * You may assume that the passed in array and comparator are both valid
     * and will not be null.
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
}