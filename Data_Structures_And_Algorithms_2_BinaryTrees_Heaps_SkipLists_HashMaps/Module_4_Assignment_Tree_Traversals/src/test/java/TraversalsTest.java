import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TraversalsTest {
    private TreeNode<Integer> root;
    private TreeNode<Integer> root2;
    private Traversals<Integer> traversals;
    private List<Integer> expected;

    @Before
    public void setUp() {
        traversals = new Traversals<>();
        expected = new ArrayList<>();
        
        // Create a basic binary search tree:
        //       4
        //      / \
        //     2   6
        //    / \ / \
        //   1  3 5  7
        root = new TreeNode<>(4);
        root.setLeft(new TreeNode<>(2));
        root.setRight(new TreeNode<>(6));
        root.getLeft().setLeft(new TreeNode<>(1));
        root.getLeft().setRight(new TreeNode<>(3));
        root.getRight().setLeft(new TreeNode<>(5));
        root.getRight().setRight(new TreeNode<>(7));

        // Create a skewed tree:
        //   1
        //    \
        //     2
        //      \
        //       3
        root2 = new TreeNode<>(1);
        root2.setRight(new TreeNode<>(2));
        root2.getRight().setRight(new TreeNode<>(3));
    }

    @Test
    public void testPreorderNormal() {
        List<Integer> expected = List.of(4, 2, 1, 3, 6, 5, 7);
        List<Integer> result = traversals.preorder(root);
        assertEquals(expected, result);
    }

    @Test
    public void testInorderNormal() {
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7);
        List<Integer> result = traversals.inorder(root);
        assertEquals(expected, result);
    }

    @Test
    public void testPostorderNormal() {
        List<Integer> expected = List.of(1, 3, 2, 5, 7, 6, 4);
        List<Integer> result = traversals.postorder(root);
        assertEquals(expected, result);
    }

    @Test
    public void testPreorderSkewed() {
        List<Integer> expected = List.of(1, 2, 3);
        List<Integer> result = traversals.preorder(root2);
        assertEquals(expected, result);
    }

    @Test
    public void testInorderSkewed() {
        List<Integer> expected = List.of(1, 2, 3);
        List<Integer> result = traversals.inorder(root2);
        assertEquals(expected, result);
    }

    @Test
    public void testPostorderSkewed() {
        List<Integer> expected = List.of(3, 2, 1);
        List<Integer> result = traversals.postorder(root2);
        assertEquals(expected, result);
    }

    @Test
    public void testEmptyTree() {
        List<Integer> result = traversals.preorder(null);
        assertTrue(result.isEmpty());
        
        result = traversals.inorder(null);
        assertTrue(result.isEmpty());
        
        result = traversals.postorder(null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSingleNode() {
        TreeNode<Integer> singleNode = new TreeNode<>(1);
        
        List<Integer> expected = List.of(1);
        List<Integer> result;
        
        result = traversals.preorder(singleNode);
        assertEquals(expected, result);
        
        result = traversals.inorder(singleNode);
        assertEquals(expected, result);
        
        result = traversals.postorder(singleNode);
        assertEquals(expected, result);
    }

    @Test
    public void testLeftSkewedTree() {
        // Create a left-skewed tree:
        //   3
        //  /
        // 2
        // /
        //1
        TreeNode<Integer> leftSkewed = new TreeNode<>(3);
        leftSkewed.setLeft(new TreeNode<>(2));
        leftSkewed.getLeft().setLeft(new TreeNode<>(1));
        
        List<Integer> preorderExpected = List.of(3, 2, 1);
        List<Integer> inorderExpected = List.of(1, 2, 3);
        List<Integer> postorderExpected = List.of(1, 2, 3);
        
        assertEquals(preorderExpected, traversals.preorder(leftSkewed));
        assertEquals(inorderExpected, traversals.inorder(leftSkewed));
        assertEquals(postorderExpected, traversals.postorder(leftSkewed));
    }

    @Test
    public void testUnbalancedTree() {
        // Create an unbalanced tree:
        //      5
        //     / \
        //    3   6
        //   /     \
        //  1       8
        //   \     /
        //    2   7
        TreeNode<Integer> unbalanced = new TreeNode<>(5);
        unbalanced.setLeft(new TreeNode<>(3));
        unbalanced.setRight(new TreeNode<>(6));
        unbalanced.getLeft().setLeft(new TreeNode<>(1));
        unbalanced.getLeft().getLeft().setRight(new TreeNode<>(2));
        unbalanced.getRight().setRight(new TreeNode<>(8));
        unbalanced.getRight().getRight().setLeft(new TreeNode<>(7));
        
        List<Integer> preorderExpected = List.of(5, 3, 1, 2, 6, 8, 7);
        List<Integer> inorderExpected = List.of(1, 2, 3, 5, 6, 7, 8);
        List<Integer> postorderExpected = List.of(2, 1, 3, 7, 8, 6, 5);
        
        assertEquals(preorderExpected, traversals.preorder(unbalanced));
        assertEquals(inorderExpected, traversals.inorder(unbalanced));
        assertEquals(postorderExpected, traversals.postorder(unbalanced));
    }
}