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
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add. You may assume that data will always be valid.
     */
    public void add(T data) {
        if (data == null) {
            return;
        }

        // Check if array needs to be resized (remember index 0 is unused)
        if (size + 1 >= backingArray.length) {
            T[] newArray = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                newArray[i] = backingArray[i];
            }
            backingArray = newArray;
        }

        // Add element to the next available position
        backingArray[size + 1] = data;
        size++;

        // Upheap (bubble up) the new element
        int currentIndex = size;
        while (currentIndex > 1) {
            int parentIndex = currentIndex / 2;
            if (backingArray[currentIndex].compareTo(backingArray[parentIndex]) > 0) {
                // Swap with parent if current element is larger
                T temp = backingArray[currentIndex];
                backingArray[currentIndex] = backingArray[parentIndex];
                backingArray[parentIndex] = temp;
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
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