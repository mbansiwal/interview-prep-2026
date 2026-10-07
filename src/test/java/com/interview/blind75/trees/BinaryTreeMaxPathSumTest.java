package com.interview.blind75.trees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeMaxPathSumTest {

    private final BinaryTreeMaxPathSum solution = new BinaryTreeMaxPathSum();

    @Test
    void simple() {
        assertEquals(6, solution.maxPathSum(new TreeNode(1, new TreeNode(2), new TreeNode(3))));
    }

    @Test
    void negatives() {
        TreeNode root = new TreeNode(-10,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        assertEquals(42, solution.maxPathSum(root));
    }

    @Test
    void allNegative() {
        assertEquals(-1, solution.maxPathSum(new TreeNode(-3, new TreeNode(-1), new TreeNode(-2))));
    }
}
