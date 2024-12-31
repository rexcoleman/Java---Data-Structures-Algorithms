import java.util.NoSuchElementException;

public class SinglyLinkedListTests {
    private static void assertTrue(boolean condition) {
        if (!condition) throw new AssertionError();
    }
    
    private static void assertEquals(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        testAddToFront();
        testAddToBack();
        testRemoveFromFront();
        testRemoveFromBack();
        System.out.println("All tests passed!");
    }

    private static void testAddToFront() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        
        // Test adding to empty list
        list.addToFront(1);
        assertTrue(list.size() == 1);
        assertTrue(list.getHead().getData().equals(1));
        assertTrue(list.getTail().getData().equals(1));
        
        // Test adding multiple elements
        list.addToFront(2);
        list.addToFront(3);
        assertTrue(list.size() == 3);
        assertTrue(list.getHead().getData().equals(3));
        assertTrue(list.getTail().getData().equals(1));
        
        // Test null input
        try {
            list.addToFront(null);
            throw new AssertionError("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    private static void testAddToBack() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        
        // Test adding to empty list
        list.addToBack(1);
        assertTrue(list.size() == 1);
        assertTrue(list.getHead().getData().equals(1));
        assertTrue(list.getTail().getData().equals(1));
        
        // Test adding multiple elements
        list.addToBack(2);
        list.addToBack(3);
        assertTrue(list.size() == 3);
        assertTrue(list.getHead().getData().equals(1));
        assertTrue(list.getTail().getData().equals(3));
    }

    private static void testRemoveFromFront() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        
        // Test removing from empty list
        try {
            list.removeFromFront();
            throw new AssertionError("Should have thrown NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }
        
        // Test removing single element
        list.addToFront(1);
        Integer removed = list.removeFromFront();
        assertTrue(removed.equals(1));
        assertTrue(list.size() == 0);
        assertTrue(list.getHead() == null);
        assertTrue(list.getTail() == null);
    }

    private static void testRemoveFromBack() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        
        // Test removing from empty list
        try {
            list.removeFromBack();
            throw new AssertionError("Should have thrown NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }
        
        // Test removing multiple elements
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        Integer removed = list.removeFromBack();
        assertTrue(removed.equals(3));
        assertTrue(list.size() == 2);
        assertTrue(list.getHead().getData().equals(1));
        assertTrue(list.getTail().getData().equals(2));
    }
}