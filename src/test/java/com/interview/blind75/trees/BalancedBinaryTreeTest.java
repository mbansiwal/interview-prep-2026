package com.interview.blind75.trees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BalancedBinaryTreeTest {

    private final BalancedBinaryTree solution = new BalancedBinaryTree();

    @Test
    void balanced() {
        TreeNode root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        assertTrue(solution.isBalanced(root));
    }

    @Test
    void unbalanced() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(3, new TreeNode(4), null), null), null);
        assertFalse(solution.isBalanced(root));
    }

    @Test
    void nullTree() {
        assertTrue(solution.isBalanced(null));
    }
}
