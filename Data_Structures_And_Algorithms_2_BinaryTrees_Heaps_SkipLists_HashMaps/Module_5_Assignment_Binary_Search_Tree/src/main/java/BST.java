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
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to BST");
        }
        root = addHelper(root, data);
    }

    private BSTNode<T> addHelper(BSTNode<T> curr, T data) {
        if (curr == null) {
            size++;
            return new BSTNode<>(data);
        }

        int compareResult = data.compareTo(curr.getData());
        if (compareResult < 0) {
            curr.setLeft(addHelper(curr.getLeft(), data));
        } else if (compareResult > 0) {
            curr.setRight(addHelper(curr.getRight(), data));
        }
        // If compareResult == 0, data is already in tree, so do nothing

        return curr;
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
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data from BST");
        }

        // Create holder array for removed data
        BSTNode<T>[] removed = new BSTNode[1];
        root = removeHelper(root, data, removed);

        if (removed[0] == null) {
            throw new NoSuchElementException("Data not found in BST");
        }

        size--;
        return removed[0].getData();
    }

    private BSTNode<T> removeHelper(BSTNode<T> curr, T data, BSTNode<T>[] removed) {
        if (curr == null) {
            return null;
        }

        int compareResult = data.compareTo(curr.getData());
        if (compareResult < 0) {
            curr.setLeft(removeHelper(curr.getLeft(), data, removed));
        } else if (compareResult > 0) {
            curr.setRight(removeHelper(curr.getRight(), data, removed));
        } else {
            // Store the data to be removed
            removed[0] = new BSTNode<>(curr.getData());

            // Case 1: Leaf node
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } 
            // Case 2: One child
            else if (curr.getLeft() == null) {
                return curr.getRight();
            } else if (curr.getRight() == null) {
                return curr.getLeft();
            }
            // Case 3: Two children
            else {
                BSTNode<T>[] successorHolder = new BSTNode[1];
                curr.setRight(removeSuccessor(curr.getRight(), successorHolder));
                curr.setData(successorHolder[0].getData());
                return curr;  // Make sure to return the modified node
            }
        }
        return curr;
    }

    private BSTNode<T> removeSuccessor(BSTNode<T> curr, BSTNode<T>[] successor) {
        if (curr.getLeft() == null) {
            successor[0] = curr;
            return curr.getRight();
        }
        curr.setLeft(removeSuccessor(curr.getLeft(), successor));
        return curr;
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
}