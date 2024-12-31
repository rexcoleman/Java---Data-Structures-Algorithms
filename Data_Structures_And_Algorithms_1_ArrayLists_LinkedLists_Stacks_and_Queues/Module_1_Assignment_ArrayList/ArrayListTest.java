import java.util.NoSuchElementException;

public class ArrayListTest {
    public static void main(String[] args) {
        testBasicOperations();
        testEdgeCases();
        testResizing();
        testExceptions();
        
        System.out.println("All tests completed!");
    }

    private static void testBasicOperations() {
        System.out.println("\nTesting basic operations...");
        
        ArrayList<Integer> list = new ArrayList<>();
        
        // Test addToBack
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        assertCondition(list.size() == 3, "Size should be 3");
        
        // Test addToFront
        list.addToFront(0);
        assertCondition(list.size() == 4, "Size should be 4");
        
        // Test removeFromFront
        int removed = list.removeFromFront();
        assertCondition(removed == 0, "Removed element should be 0");
        assertCondition(list.size() == 3, "Size should be 3");
        removed = list.removeFromFront();
        assertCondition(removed == 1, "Removed element should be 1");
        
        // Test removeFromBack
        removed = list.removeFromBack();
        assertCondition(removed == 3, "Removed element should be 3");
        assertCondition(list.size() == 1, "Size should be 1");
        removed = list.removeFromBack();
        assertCondition(removed == 2, "Removed element should be 2");
        assertCondition(list.size() == 0, "Size should be 0");
        
        System.out.println("Basic operations tests passed!");
    }

    private static void testEdgeCases() {
        System.out.println("\nTesting edge cases...");
        
        ArrayList<String> list = new ArrayList<>();
        
        // Test empty list operations
        assertCondition(list.size() == 0, "Initial size should be 0");
        
        // Test single element operations
        list.addToBack("first");
        assertCondition(list.size() == 1, "Size should be 1");
        String removed = list.removeFromBack();
        assertCondition(removed.equals("first"), "Removed element should be 'first'");
        assertCondition(list.size() == 0, "Size should be 0");
        
        // Test alternating operations
        list.addToFront("A");
        list.addToBack("B");
        list.addToFront("C");
        assertCondition(list.size() == 3, "Size should be 3");
        removed = list.removeFromFront();
        assertCondition(removed.equals("C"), "First removed element should be 'C'");
        removed = list.removeFromBack();
        assertCondition(removed.equals("B"), "Last removed element should be 'B'");
        
        System.out.println("Edge cases tests passed!");
    }

    private static void testResizing() {
        System.out.println("\nTesting resizing behavior...");
        
        ArrayList<Integer> list = new ArrayList<>();
        
        // Fill beyond initial capacity
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY + 1; i++) {
            list.addToBack(i);
        }
        assertCondition(list.size() == ArrayList.INITIAL_CAPACITY + 1, 
            "Size should be INITIAL_CAPACITY + 1");
        
        // Test elements after resize
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY + 1; i++) {
            int removed = list.removeFromFront();
            assertCondition(removed == i, 
                "Element should match after resize: expected " + i + ", got " + removed);
        }
        
        System.out.println("Resizing tests passed!");
    }

    private static void testExceptions() {
        System.out.println("\nTesting exceptions...");
        
        ArrayList<String> list = new ArrayList<>();
        
        // Test null element exception
        try {
            list.addToFront(null);
            System.out.println("Failed: Should throw IllegalArgumentException for null element");
        } catch (IllegalArgumentException e) {
            System.out.println("Passed: Caught IllegalArgumentException for null element in addToFront");
        }
        
        try {
            list.addToBack(null);
            System.out.println("Failed: Should throw IllegalArgumentException for null element");
        } catch (IllegalArgumentException e) {
            System.out.println("Passed: Caught IllegalArgumentException for null element in addToBack");
        }
        
        // Test empty list exceptions
        try {
            list.removeFromFront();
            System.out.println("Failed: Should throw NoSuchElementException for empty list");
        } catch (NoSuchElementException e) {
            System.out.println("Passed: Caught NoSuchElementException in removeFromFront");
        }
        
        try {
            list.removeFromBack();
            System.out.println("Failed: Should throw NoSuchElementException for empty list");
        } catch (NoSuchElementException e) {
            System.out.println("Passed: Caught NoSuchElementException in removeFromBack");
        }
        
        System.out.println("Exception tests passed!");
    }

    private static void assertCondition(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("Test failed: " + message);
        }
    }
}