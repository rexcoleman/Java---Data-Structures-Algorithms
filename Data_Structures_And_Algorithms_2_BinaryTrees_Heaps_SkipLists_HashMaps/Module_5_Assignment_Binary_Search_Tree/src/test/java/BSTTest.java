import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

public class BSTTest {
    private BST<Integer> bst;

    @BeforeEach
    public void setUp() {
        bst = new BST<>();
    }

    @Test
    public void testAddNull() {
        assertThrows(IllegalArgumentException.class, () -> bst.add(null));
    }

    @Test
    public void testRemoveNull() {
        assertThrows(IllegalArgumentException.class, () -> bst.remove(null));
    }

    @Test
    public void testRemoveFromEmpty() {
        assertThrows(NoSuchElementException.class, () -> bst.remove(5));
    }

    @Test
    public void testAddAndSize() {
        assertEquals(0, bst.size());
        
        bst.add(5);
        assertEquals(1, bst.size());
        
        bst.add(3);
        assertEquals(2, bst.size());
        
        // Test duplicate
        bst.add(5);
        assertEquals(2, bst.size());
    }

    @Test
    public void testBasicStructure() {
        bst.add(5);
        bst.add(3);
        bst.add(7);

        BSTNode<Integer> root = bst.getRoot();
        assertEquals(Integer.valueOf(5), root.getData());
        assertEquals(Integer.valueOf(3), root.getLeft().getData());
        assertEquals(Integer.valueOf(7), root.getRight().getData());
    }

    @Test
    public void testRemoveLeaf() {
        bst.add(5);
        bst.add(3);
        bst.add(7);

        Integer removed = bst.remove(3);
        assertEquals(Integer.valueOf(3), removed);
        assertEquals(2, bst.size());
        assertNull(bst.getRoot().getLeft());
    }

    @Test
    public void testRemoveWithOneChild() {
        bst.add(5);
        bst.add(3);
        bst.add(2);

        Integer removed = bst.remove(3);
        assertEquals(Integer.valueOf(3), removed);
        assertEquals(2, bst.size());
        assertEquals(Integer.valueOf(2), bst.getRoot().getLeft().getData());
    }

    @Test
    public void testRemoveWithTwoChildren() {
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(6);
        bst.add(8);

        Integer removed = bst.remove(7);
        assertEquals(Integer.valueOf(7), removed);
        assertEquals(4, bst.size());
        assertEquals(Integer.valueOf(8), bst.getRoot().getRight().getData());
        assertEquals(Integer.valueOf(6), bst.getRoot().getRight().getLeft().getData());
    }

    @Test
    public void testRemoveRoot() {
        bst.add(5);
        bst.add(3);
        bst.add(7);

        Integer removed = bst.remove(5);
        assertEquals(Integer.valueOf(5), removed);
        assertEquals(2, bst.size());
    }

    @Test
    public void testAddManyNodes() {
        int[] values = {50, 25, 75, 12, 37, 62, 87, 6, 18, 31, 43, 56, 68, 81, 93};
        for (int value : values) {
            bst.add(value);
        }
        assertEquals(15, bst.size());
    }

    @Test
    public void testDataHandling() {
        // Test that we're using compareTo and not reference equality
        String str1 = new String("test");
        String str2 = new String("test");
        
        BST<String> stringBST = new BST<>();
        stringBST.add(str1);
        
        // Should not add duplicate even though it's a different object
        stringBST.add(str2);
        assertEquals(1, stringBST.size());
        
        // Should return the actual stored object, not the parameter passed
        String removed = stringBST.remove(str2);
        assertSame(str1, removed);
    }
}