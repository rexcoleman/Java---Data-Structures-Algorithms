import java.util.NoSuchElementException;

public class ArrayQueueTests {
    public static void main(String[] args) {
        // Track number of tests passed
        int testsPassed = 0;
        int totalTests = 0;

        // Test initialization
        ArrayQueue<String> queue = new ArrayQueue<>();
        totalTests++;
        if (queue.size() == 0 && queue.getBackingArray().length == ArrayQueue.INITIAL_CAPACITY) {
            System.out.println("✓ Initialization test passed");
            testsPassed++;
        } else {
            System.out.println("✗ Initialization test failed");
        }

        // Test enqueue null
        totalTests++;
        try {
            queue.enqueue(null);
            System.out.println("✗ Enqueue null test failed");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Enqueue null test passed");
            testsPassed++;
        }

        // Test basic enqueue/dequeue
        queue = new ArrayQueue<>();
        totalTests++;
        try {
            queue.enqueue("A");
            queue.enqueue("B");
            queue.enqueue("C");
            boolean testPassed = queue.size() == 3 &&
                               queue.dequeue().equals("A") &&
                               queue.dequeue().equals("B") &&
                               queue.dequeue().equals("C") &&
                               queue.size() == 0;
            if (testPassed) {
                System.out.println("✓ Basic enqueue/dequeue test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Basic enqueue/dequeue test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Basic enqueue/dequeue test failed with exception: " + e.getMessage());
        }

        // Test resize
        queue = new ArrayQueue<>();
        totalTests++;
        try {
            for (int i = 0; i < ArrayQueue.INITIAL_CAPACITY; i++) {
                queue.enqueue("Item" + i);
            }
            queue.enqueue("TriggerResize");
            boolean testPassed = queue.getBackingArray().length == ArrayQueue.INITIAL_CAPACITY * 2 &&
                               queue.size() == ArrayQueue.INITIAL_CAPACITY + 1;
            if (testPassed) {
                System.out.println("✓ Resize test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Resize test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Resize test failed with exception: " + e.getMessage());
        }

        // Test dequeue empty
        queue = new ArrayQueue<>();
        totalTests++;
        try {
            queue.dequeue();
            System.out.println("✗ Dequeue empty test failed");
        } catch (NoSuchElementException e) {
            System.out.println("✓ Dequeue empty test passed");
            testsPassed++;
        }

        // Test circular behavior
        queue = new ArrayQueue<>();
        totalTests++;
        try {
            // Add initial items
            for (int i = 0; i < 5; i++) {
                queue.enqueue("Item" + i);
            }
            // Remove some
            queue.dequeue();
            queue.dequeue();
            // Add more to wrap around
            queue.enqueue("NewItem1");
            queue.enqueue("NewItem2");
            
            String[] expected = {"Item2", "Item3", "Item4", "NewItem1", "NewItem2"};
            boolean testPassed = true;
            for (String exp : expected) {
                if (!queue.dequeue().equals(exp)) {
                    testPassed = false;
                    break;
                }
            }
            if (testPassed) {
                System.out.println("✓ Circular behavior test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Circular behavior test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Circular behavior test failed with exception: " + e.getMessage());
        }

        // Print final results
        System.out.println("\nTest Results: " + testsPassed + "/" + totalTests + " tests passed");
    }
}