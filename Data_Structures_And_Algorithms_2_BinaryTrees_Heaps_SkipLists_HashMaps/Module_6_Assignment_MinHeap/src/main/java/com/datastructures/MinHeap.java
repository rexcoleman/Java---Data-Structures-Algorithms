import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {
    public static final int INITIAL_CAPACITY = 13;
    private T[] backingArray;
    private int size;

    public MinHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error: data cannot be null");
        }

        // Check if array is full (remember index 0 is unused)
        if (size + 1 == backingArray.length) {
            T[] newArray = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                newArray[i] = backingArray[i];
            }
            backingArray = newArray;
        }

        // Add new element at the next available position
        size++;
        backingArray[size] = data;

        // Upheap the new element
        upheap(size);
    }

    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("Error: heap is empty");
        }

        // Store the minimum element to return later
        T removed = backingArray[1];

        // Move the last element to the root
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;

        // Downheap the new root if heap is not empty
        if (size > 0) {
            downheap(1);
        }

        return removed;
    }

    private void upheap(int index) {
        while (index > 1) {
            int parentIdx = index / 2;
            if (backingArray[index].compareTo(backingArray[parentIdx]) < 0) {
                // Swap with parent if current element is smaller
                T temp = backingArray[parentIdx];
                backingArray[parentIdx] = backingArray[index];
                backingArray[index] = temp;
                index = parentIdx;
            } else {
                break;
            }
        }
    }

    private void downheap(int index) {
        while (index <= size / 2) {
            int leftChild = 2 * index;
            int rightChild = 2 * index + 1;
            int smallest = leftChild;

            // Find the smallest among parent and its children
            if (rightChild <= size && 
                backingArray[rightChild].compareTo(backingArray[leftChild]) < 0) {
                smallest = rightChild;
            }

            if (backingArray[index].compareTo(backingArray[smallest]) > 0) {
                // Swap with smallest child if parent is larger
                T temp = backingArray[smallest];
                backingArray[smallest] = backingArray[index];
                backingArray[index] = temp;
                index = smallest;
            } else {
                break;
            }
        }
    }

    public T[] getBackingArray() {
        return backingArray;
    }

    public int size() {
        return size;
    }
}