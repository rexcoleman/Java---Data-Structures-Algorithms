import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

public class MinHeapTests {
    private MinHeap<Integer> heap;

    @Before
    public void setUp() {
        heap = new MinHeap<>();
    }

    @Test
    public void testInitialization() {
        assertEquals(0, heap.size());
        assertEquals(MinHeap.INITIAL_CAPACITY, heap.getBackingArray().length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNull() {
        heap.add(null);
    }

    @Test
    public void testAddSingleElement() {
        heap.add(5);
        assertEquals(1, heap.size());
        @SuppressWarnings("unchecked")
        Comparable<Integer>[] arr = (Comparable<Integer>[]) heap.getBackingArray();
        assertEquals(Integer.valueOf(5), arr[1]);
    }

    @Test
    public void testAddMultipleElements() {
        heap.add(5);
        heap.add(3);
        heap.add(7);
        
        assertEquals(3, heap.size());
        @SuppressWarnings("unchecked")
        Comparable<Integer>[] arr = (Comparable<Integer>[]) heap.getBackingArray();
        assertEquals(Integer.valueOf(3), arr[1]); // Root should be smallest
        assertTrue(arr[1].compareTo((Integer)arr[2]) <= 0); // Parent <= Children
        assertTrue(arr[1].compareTo((Integer)arr[3]) <= 0);
    }

    @Test
    public void testResize() {
        // Fill initial capacity (remember index 0 is unused)
        for (int i = 1; i <= MinHeap.INITIAL_CAPACITY - 1; i++) {
            heap.add(i);
        }
        
        // Add one more element to trigger resize
        heap.add(100);
        
        assertEquals(MinHeap.INITIAL_CAPACITY * 2, heap.getBackingArray().length);
        assertEquals(MinHeap.INITIAL_CAPACITY, heap.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveEmpty() {
        heap.remove();
    }

    @Test
    public void testRemoveSingleElement() {
        heap.add(5);
        Integer removed = heap.remove();
        
        assertEquals(Integer.valueOf(5), removed);
        assertEquals(0, heap.size());
        assertNull(heap.getBackingArray()[1]);
    }

    @Test
    public void testRemoveMultipleElements() {
        // Add elements in random order
        heap.add(5);
        heap.add(3);
        heap.add(7);
        heap.add(1);
        heap.add(4);
        
        // Remove elements - should come out in ascending order
        assertEquals(Integer.valueOf(1), heap.remove());
        assertEquals(Integer.valueOf(3), heap.remove());
        assertEquals(Integer.valueOf(4), heap.remove());
        assertEquals(Integer.valueOf(5), heap.remove());
        assertEquals(Integer.valueOf(7), heap.remove());
        
        assertEquals(0, heap.size());
    }

    @Test
    public void testHeapProperty() {
        // Add elements in descending order to test upheap
        heap.add(10);
        heap.add(9);
        heap.add(8);
        heap.add(7);
        heap.add(6);
        heap.add(5);
        heap.add(4);
        heap.add(3);
        heap.add(2);
        heap.add(1);
        
        // Verify heap property at each level
        @SuppressWarnings("unchecked")
        Comparable<Integer>[] arr = (Comparable<Integer>[]) heap.getBackingArray();
        for (int i = 1; i <= heap.size() / 2; i++) {
            int leftChild = 2 * i;
            int rightChild = 2 * i + 1;
            
            if (leftChild <= heap.size()) {
                assertTrue(arr[i].compareTo((Integer)arr[leftChild]) <= 0);
            }
            if (rightChild <= heap.size()) {
                assertTrue(arr[i].compareTo((Integer)arr[rightChild]) <= 0);
            }
        }
    }

    @Test
    public void testAddRemoveAlternating() {
        heap.add(3);
        assertEquals(Integer.valueOf(3), heap.remove());
        heap.add(1);
        heap.add(5);
        assertEquals(Integer.valueOf(1), heap.remove());
        heap.add(4);
        assertEquals(Integer.valueOf(4), heap.remove());
        assertEquals(Integer.valueOf(5), heap.remove());
        assertEquals(0, heap.size());
    }

    @Test
    public void testComplexSequence() {
        int[] values = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        for (int value : values) {
            heap.add(value);
        }

        // Should maintain heap property after each removal
        assertEquals(Integer.valueOf(1), heap.remove());
        assertEquals(Integer.valueOf(3), heap.remove());
        
        heap.add(2);
        assertEquals(Integer.valueOf(2), heap.remove());
        
        heap.add(5);
        heap.add(9);
        assertEquals(Integer.valueOf(4), heap.remove());
        assertEquals(Integer.valueOf(5), heap.remove());
    }
}