package com.interview.blind75.trees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaxDepthBinaryTreeTest {

    private final MaxDepthBinaryTree solution = new MaxDepthBinaryTree();

    @Test
    void depth3() {
        TreeNode root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        assertEquals(3, solution.maxDepth(root));
    }

    @Test
    void skewed() {
        assertEquals(2, solution.maxDepth(new TreeNode(1, null, new TreeNode(2))));
    }

    @Test
    void nullTree() {
        assertEquals(0, solution.maxDepth(null));
    }
}
