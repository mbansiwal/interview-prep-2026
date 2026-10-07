package com.interview.blind75.trees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvertBinaryTreeTest {

    private final InvertBinaryTree solution = new InvertBinaryTree();

    @Test
    void invertTree() {
        TreeNode root = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7, new TreeNode(6), new TreeNode(9)));
        TreeNode inverted = solution.invertTree(root);
        assertEquals(7, inverted.left.val);
        assertEquals(2, inverted.right.val);
        assertEquals(9, inverted.left.left.val);
        assertEquals(6, inverted.left.right.val);
    }

    @Test
    void nullTree() {
        assertNull(solution.invertTree(null));
    }

    @Test
    void singleNode() {
        TreeNode root = new TreeNode(1);
        assertEquals(1, solution.invertTree(root).val);
    }
}
