import java.util.NoSuchElementException;

public class ArrayQueueTests {
   public static void main(String[] args) {
       // Track number of tests passed
       int testsPassed = 0;
       int totalTests = 0;

       System.out.println("Starting ArrayQueue tests...\n");

       // Test 1: Initialization
       totalTests++;
       System.out.println("Test 1: Initialization");
       try {
           ArrayQueue<String> queue = new ArrayQueue<>();
           // Only check size and not array internals
           if (queue.size() == 0) {
               testsPassed++;
               System.out.println("PASSED - Queue initialized with size 0");
           } else {
               System.out.println("FAILED - Queue not initialized with size 0");
           }
       } catch (Exception e) {
           System.out.println("FAILED - Exception during initialization: " + e.getMessage());
       }
       System.out.println();

       // Test 2: Enqueue null
       totalTests++;
       System.out.println("Test 2: Enqueue null");
       ArrayQueue<String> queue = new ArrayQueue<>();
       try {
           queue.enqueue(null);
           System.out.println("FAILED - Should throw IllegalArgumentException for null element");
       } catch (IllegalArgumentException e) {
           testsPassed++;
           System.out.println("PASSED - Correctly threw IllegalArgumentException for null element");
       }
       System.out.println();

       // Test 3: Basic enqueue/dequeue
       totalTests++;
       System.out.println("Test 3: Basic enqueue/dequeue");
       queue = new ArrayQueue<>();
       try {
           queue.enqueue("A");
           queue.enqueue("B");
           queue.enqueue("C");
           
           String first = queue.dequeue();
           String second = queue.dequeue();
           String third = queue.dequeue();
           
           if (first.equals("A") && second.equals("B") && third.equals("C") && queue.size() == 0) {
               testsPassed++;
               System.out.println("PASSED - Basic enqueue/dequeue works correctly");
           } else {
               System.out.println("FAILED - Basic enqueue/dequeue operations failed");
           }
       } catch (Exception e) {
           System.out.println("FAILED - Exception during basic operations: " + e.getMessage());
       }
       System.out.println();

       // Test 4: Dequeue empty queue
       totalTests++;
       System.out.println("Test 4: Dequeue empty queue");
       queue = new ArrayQueue<>();
       try {
           queue.dequeue();
           System.out.println("FAILED - Should throw NoSuchElementException for empty queue");
       } catch (NoSuchElementException e) {
           testsPassed++;
           System.out.println("PASSED - Correctly threw NoSuchElementException for empty queue");
       }
       System.out.println();

       // Test 5: Test resizing
       totalTests++;
       System.out.println("Test 5: Testing resize");
       queue = new ArrayQueue<>();
       try {
           // Fill beyond initial capacity
           for (int i = 0; i < ArrayQueue.INITIAL_CAPACITY + 1; i++) {
               queue.enqueue("Item" + i);
           }
           // Test by dequeueing all items
           boolean testPassed = true;
           for (int i = 0; i < ArrayQueue.INITIAL_CAPACITY + 1; i++) {
               String expected = "Item" + i;
               String actual = queue.dequeue();
               if (!expected.equals(actual)) {
                   testPassed = false;
                   System.out.println("FAILED - Expected " + expected + " but got " + actual);
                   break;
               }
           }
           if (testPassed) {
               testsPassed++;
               System.out.println("PASSED - Queue correctly handled resizing");
           }
       } catch (Exception e) {
           System.out.println("FAILED - Exception during resize test: " + e.getMessage());
       }
       System.out.println();

       // Test 6: Circular behavior
       totalTests++;
       System.out.println("Test 6: Testing circular behavior");
       queue = new ArrayQueue<>();
       try {
           // Fill partially
           for (int i = 0; i < 5; i++) {
               queue.enqueue("Item" + i);
           }
           // Remove some
           queue.dequeue();
           queue.dequeue();
           // Add more
           queue.enqueue("New1");
           queue.enqueue("New2");

           String[] expected = {"Item2", "Item3", "Item4", "New1", "New2"};
           boolean testPassed = true;
           for (String exp : expected) {
               String dequeued = queue.dequeue();
               if (!dequeued.equals(exp)) {
                   testPassed = false;
                   System.out.println("FAILED - Expected " + exp + " but got " + dequeued);
                   break;
               }
           }
           if (testPassed) {
               testsPassed++;
               System.out.println("PASSED - Circular behavior works correctly");
           }
       } catch (Exception e) {
           System.out.println("FAILED - Exception during circular behavior test: " + e.getMessage());
       }
       System.out.println();

       // Print final results
       System.out.println("Final Results:");
       System.out.println("Tests Passed: " + testsPassed + "/" + totalTests);
       if (testsPassed == totalTests) {
           System.out.println("ALL TESTS PASSED!");
       } else {
           System.out.println("Some tests failed.");
       }
   }
}