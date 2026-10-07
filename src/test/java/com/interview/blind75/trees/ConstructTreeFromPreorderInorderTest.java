package com.interview.blind75.trees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstructTreeFromPreorderInorderTest {

    private final ConstructTreeFromPreorderInorder solution = new ConstructTreeFromPreorderInorder();

    @Test
    void buildTree() {
        TreeNode root = solution.buildTree(
                new int[]{3, 9, 20, 15, 7},
                new int[]{9, 3, 15, 20, 7});
        assertEquals(3, root.val);
        assertEquals(9, root.left.val);
        assertEquals(20, root.right.val);
        assertEquals(15, root.right.left.val);
        assertEquals(7, root.right.right.val);
    }

    @Test
    void singleNode() {
        TreeNode root = solution.buildTree(new int[]{1}, new int[]{1});
        assertEquals(1, root.val);
        assertNull(root.left);
        assertNull(root.right);
    }

    @Test
    void leftSkewed() {
        TreeNode root = solution.buildTree(
                new int[]{3, 2, 1},
                new int[]{1, 2, 3});
        assertEquals(3, root.val);
        assertEquals(2, root.left.val);
        assertEquals(1, root.left.left.val);
    }
}
