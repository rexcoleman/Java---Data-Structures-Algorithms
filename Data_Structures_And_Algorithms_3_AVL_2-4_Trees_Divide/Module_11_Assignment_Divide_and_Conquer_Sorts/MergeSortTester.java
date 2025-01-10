import java.util.Arrays;
import java.util.Comparator;

public class MergeSortTester {
    static class CountingComparator implements Comparator<Integer> {
        private int compareCount = 0;
        
        @Override
        public int compare(Integer a, Integer b) {
            compareCount++;
            return a.compareTo(b);
        }
        
        public int getCount() {
            return compareCount;
        }
        
        public void resetCount() {
            compareCount = 0;
        }
    }
    
    public static void main(String[] args) {
        CountingComparator comparator = new CountingComparator();
        
        // Test Case 1: Already sorted array
        System.out.println("Test Case 1: Already sorted array");
        Integer[] test1 = {1, 2, 3, 4};
        testSort(test1, comparator);
        
        // Test Case 2: Reverse sorted array
        System.out.println("\nTest Case 2: Reverse sorted array");
        Integer[] test2 = {4, 3, 2, 1};
        testSort(test2, comparator);
        
        // Test Case 3: Array with duplicates
        System.out.println("\nTest Case 3: Array with duplicates");
        Integer[] test3 = {3, 1, 3, 1};
        testSort(test3, comparator);
        
        // Test Case 4: Small array (2 elements)
        System.out.println("\nTest Case 4: Small array");
        Integer[] test4 = {2, 1};
        testSort(test4, comparator);
        
        // Test Case 5: Odd number of elements
        System.out.println("\nTest Case 5: Odd number of elements");
        Integer[] test5 = {3, 1, 4, 1, 5};
        testSort(test5, comparator);

        // Test Case 5: 7 elements
        System.out.println("\nTest Case 6: 7 elements");
        Integer[] test6 = {4, 3, 1, 5, 2, 6, 7};
        testSort(test6, comparator);


    }
    
    private static void testSort(Integer[] arr, CountingComparator comparator) {
        System.out.println("Original array: " + Arrays.toString(arr));
        comparator.resetCount();
        
        Sorting.mergeSort(arr, comparator);
        
        System.out.println("Sorted array: " + Arrays.toString(arr));
        System.out.println("Number of comparisons: " + comparator.getCount());
        
        // Verify if array is actually sorted
        boolean isSorted = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                isSorted = false;
                break;
            }
        }
        System.out.println("Is sorted correctly: " + isSorted);
    }
}