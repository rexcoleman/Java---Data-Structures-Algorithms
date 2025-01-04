import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

     /**
     * Returns the data from the tree matching the given parameter.
     *
     * This should be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to search for. You may assume data is never null.
     * @return The data in the tree equal to the parameter.
     * @throws java.util.NoSuchElementException If the data is not in the tree.
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        BSTNode<T> node = getHelper(root, data);
        if (node == null) {
            throw new NoSuchElementException("Data not found in the tree");
        }
        return node.getData();
    }

    private BSTNode<T> getHelper(BSTNode<T> node, T data) {
        if (node == null) {
            return null;
        }
        
        int compareResult = data.compareTo(node.getData());
        if (compareResult < 0) {
            return getHelper(node.getLeft(), data);
        } else if (compareResult > 0) {
            return getHelper(node.getRight(), data);
        } else {
            return node;
        }
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the PREDECESSOR to
     * replace the data. You should use recursion to find and remove the
     * predecessor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove. You may assume that data is never null.
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the data is not in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        
        BSTNode<T> dummy = new BSTNode<>(null);
        root = removeHelper(root, data, dummy);
        
        if (dummy.getData() == null) {
            throw new NoSuchElementException("Data not found in the tree");
        }
        size--;
        return dummy.getData();
    }

    private BSTNode<T> removeHelper(BSTNode<T> node, T data, BSTNode<T> dummy) {
        if (node == null) {
            return null;
        }
        
        int compareResult = data.compareTo(node.getData());
        if (compareResult < 0) {
            node.setLeft(removeHelper(node.getLeft(), data, dummy));
        } else if (compareResult > 0) {
            node.setRight(removeHelper(node.getRight(), data, dummy));
        } else {
            dummy.setData(node.getData());
            
            // Case 1: Node is a leaf
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            // Case 2: Node has one child
            else if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            // Case 3: Node has two children
            else {
                BSTNode<T> dummy2 = new BSTNode<>(null);
                node.setLeft(removePredecessor(node.getLeft(), dummy2));
                node.setData(dummy2.getData());
            }
        }
        return node;
    }

    private BSTNode<T> removePredecessor(BSTNode<T> node, BSTNode<T> dummy) {
        if (node.getRight() == null) {
            dummy.setData(node.getData());
            return node.getLeft();
        }
        node.setRight(removePredecessor(node.getRight(), dummy));
        return node;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }




    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        
        // Test Case 0: get() tests
        try {
            System.out.println("=== Test Case 0: Get Method ===");
            // Setup a simple tree
            bst.root = new BSTNode<>(10);
            bst.root.setLeft(new BSTNode<>(5));
            bst.root.setRight(new BSTNode<>(15));
            bst.size = 3;
            
            // Test existing values
            System.out.println("Getting root value (10)...");
            int found = bst.get(10);
            System.out.println("Found: " + found);
            
            System.out.println("Getting leaf value (5)...");
            found = bst.get(5);
            System.out.println("Found: " + found);
            
            // Test non-existent value
            try {
                System.out.println("Getting non-existent value (7)...");
                bst.get(7);
                System.out.println("Failed: Should have thrown exception");
            } catch (NoSuchElementException e) {
                System.out.println("Passed: Correctly threw exception for missing value");
            }
            
            // Test on empty tree
            bst = new BST<>();
            try {
                System.out.println("\nTesting get() on empty tree...");
                bst.get(5);
                System.out.println("Failed: Should have thrown exception");
            } catch (NoSuchElementException e) {
                System.out.println("Passed: Correctly threw exception for empty tree");
            }
            
        } catch (Exception e) {
            System.out.println("Test Case 0 failed: " + e.getMessage());
        }
        
        // Test Case 1: Basic removal scenarios
        try {
            System.out.println("\n=== Test Case 1: Basic Removal ===");
            // Test leaf removal
            bst = new BST<>();
            bst.root = new BSTNode<>(10);
            bst.root.setLeft(new BSTNode<>(5));
            bst.root.setRight(new BSTNode<>(15));
            bst.size = 3;
            
            System.out.println("Removing 5 (leaf)...");
            int removed = bst.remove(5);
            System.out.println("Removed: " + removed + ", Size: " + bst.size);
            
            // Quick visual check of structure
            System.out.println("Root: " + bst.root.getData());
            System.out.println("Left: " + (bst.root.getLeft() == null ? "null" : bst.root.getLeft().getData()));
            System.out.println("Right: " + (bst.root.getRight() == null ? "null" : bst.root.getRight().getData()));
        } catch (Exception e) {
            System.out.println("Test Case 1 failed: " + e.getMessage());
        }
        
        // Test Case 2: Two-child removal
        try {
            System.out.println("\n=== Test Case 2: Two-Child Removal ===");
            bst = new BST<>();  // Reset tree
            bst.root = new BSTNode<>(10);
            bst.root.setLeft(new BSTNode<>(5));
            bst.root.setRight(new BSTNode<>(15));
            bst.root.getLeft().setLeft(new BSTNode<>(3));
            bst.root.getLeft().setRight(new BSTNode<>(7));
            bst.size = 5;
            
            System.out.println("Removing 5 (two children)...");
            int removed = bst.remove(5);
            System.out.println("Removed: " + removed + ", Size: " + bst.size);
            System.out.println("New left child of root: " + bst.root.getLeft().getData());
        } catch (Exception e) {
            System.out.println("Test Case 2 failed: " + e.getMessage());
        }
        
        // Test Case 3: Error cases
        try {
            System.out.println("\n=== Test Case 3: Error Cases ===");
            bst = new BST<>();  // Empty tree
            bst.remove(5);  // Should throw exception
            System.out.println("Failed: Should have thrown exception for empty tree");
        } catch (NoSuchElementException e) {
            System.out.println("Passed: Correctly threw exception for empty tree");
        }
     }

}

