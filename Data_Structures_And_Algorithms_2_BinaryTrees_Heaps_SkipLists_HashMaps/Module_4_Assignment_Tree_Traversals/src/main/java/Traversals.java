import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Traversals<T extends Comparable<? super T>> {
    
    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     */
    public List<T> preorder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    /**
     * Helper method for preorder traversal.
     * Order: Current node, Left subtree, Right subtree (CLR)
     */
    private void preorderHelper(TreeNode<T> node, List<T> result) {
        if (node == null) {
            return;
        }
        result.add(node.getData());           // C: Process Current node
        preorderHelper(node.getLeft(), result);  // L: Recurse Left
        preorderHelper(node.getRight(), result); // R: Recurse Right
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     */
    public List<T> inorder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    /**
     * Helper method for inorder traversal.
     * Order: Left subtree, Current node, Right subtree (LCR)
     */
    private void inorderHelper(TreeNode<T> node, List<T> result) {
        if (node == null) {
            return;
        }
        inorderHelper(node.getLeft(), result);   // L: Recurse Left
        result.add(node.getData());            // C: Process Current node
        inorderHelper(node.getRight(), result);  // R: Recurse Right
    }

    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     */
    public List<T> postorder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    /**
     * Helper method for postorder traversal.
     * Order: Left subtree, Right subtree, Current node (LRC)
     */
    private void postorderHelper(TreeNode<T> node, List<T> result) {
        if (node == null) {
            return;
        }
        postorderHelper(node.getLeft(), result);  // L: Recurse Left
        postorderHelper(node.getRight(), result); // R: Recurse Right
        result.add(node.getData());            // C: Process Current node
    }
}