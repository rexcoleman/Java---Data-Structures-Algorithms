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
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This should be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to search for. You may assume data is never null.
     * @return true if the parameter is contained within the tree, false otherwise.
     */
    public boolean contains(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        return containsHelper(root, data);
    }

    private boolean containsHelper(BSTNode<T> node, T data) {
        if (node == null) {
            return false;
        }

        int compare = data.compareTo(node.getData());
        if (compare < 0) {
            return containsHelper(node.getLeft(), data);
        } else if (compare > 0) {
            return containsHelper(node.getRight(), data);
        } else {
            return true;
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
     * @param data The data to remove. You may assume that data is never null.
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the data is not in the tree.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        BSTNode<T> dummy = new BSTNode<>(null);
        root = removeHelper(root, data, dummy);
        size--;
        return dummy.getData();
    }
    
    private BSTNode<T> removeHelper(BSTNode<T> node, T data, BSTNode<T> dummy) {
        if (node == null) {
            throw new NoSuchElementException();
        }

        int compare = data.compareTo(node.getData());
        if (compare < 0) {
            node.setLeft(removeHelper(node.getLeft(), data, dummy));
        } else if (compare > 0) {
            node.setRight(removeHelper(node.getRight(), data, dummy));
        } else {
            // Store the data to return
            dummy.setData(node.getData());
            
            // Case 1: Leaf node
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            // Case 2: One child
            else if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            // Case 3: Two children
            else {
                BSTNode<T> successorDummy = new BSTNode<>(null);
                node.setRight(removeSuccessor(node.getRight(), successorDummy));
                node.setData(successorDummy.getData());
            }
        }
        return node;
    }

    private BSTNode<T> removeSuccessor(BSTNode<T> node, BSTNode<T> dummy) {
        if (node.getLeft() == null) {
            dummy.setData(node.getData());
            return node.getRight();
        }
        node.setLeft(removeSuccessor(node.getLeft(), dummy));
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
}