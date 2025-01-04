/**
 * Your implementation of a MaxHeap.
 */
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MaxHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MaxHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Removes and returns the max item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * You may assume that the heap is not empty.
     *
     * @return The data that was removed.
     */
    public T remove() {
        // Since we can assume heap is not empty per the instructions,
        // we don't need the size == 0 check
        
        // Store the max element to return later
        T maxElement = backingArray[1];  // Root is at index 1, not 0
        
        // Move last element to root position and null out its previous position
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        
        // Down-heap (sift down) the new root to maintain heap property
        int currentIdx = 1;  // Start from root at index 1
        while (2 * currentIdx <= size) {  // While there is at least a left child
            int leftIdx = 2 * currentIdx;
            int rightIdx = 2 * currentIdx + 1;
            int maxIdx = leftIdx;  // Assume left child is larger
            
            // Check if right child exists and is larger than left child
            if (rightIdx <= size && backingArray[rightIdx].compareTo(backingArray[leftIdx]) > 0) {
                maxIdx = rightIdx;
            }
            
            // If current node is smaller than the largest child, swap
            if (backingArray[currentIdx].compareTo(backingArray[maxIdx]) < 0) {
                T temp = backingArray[currentIdx];
                backingArray[currentIdx] = backingArray[maxIdx];
                backingArray[maxIdx] = temp;
                currentIdx = maxIdx;  // Move down to the child we swapped with
            } else {
                break;  // Heap property is satisfied
            }
        }
        
        return maxElement;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}