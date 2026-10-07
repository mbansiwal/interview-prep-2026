package com.interview.blind75.trees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SubtreeOfAnotherTreeTest {

    private final SubtreeOfAnotherTree solution = new SubtreeOfAnotherTree();

    @Test
    void isSubtree() {
        TreeNode root = new TreeNode(3,
                new TreeNode(4, new TreeNode(1), new TreeNode(2)),
                new TreeNode(5));
        TreeNode subRoot = new TreeNode(4, new TreeNode(1), new TreeNode(2));
        assertTrue(solution.isSubtree(root, subRoot));
    }

    @Test
    void notSubtree() {
        TreeNode root = new TreeNode(3,
                new TreeNode(4, new TreeNode(1), new TreeNode(2, new TreeNode(0), null)),
                new TreeNode(5));
        TreeNode subRoot = new TreeNode(4, new TreeNode(1), new TreeNode(2));
        assertFalse(solution.isSubtree(root, subRoot));
    }

    @Test
    void nullRoot() {
        assertFalse(solution.isSubtree(null, new TreeNode(1)));
    }
}
