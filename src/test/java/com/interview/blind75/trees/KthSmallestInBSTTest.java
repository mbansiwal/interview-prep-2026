package com.interview.blind75.trees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KthSmallestInBSTTest {

    private final KthSmallestInBST solution = new KthSmallestInBST();

    @Test
    void kthSmallest1() {
        TreeNode root = new TreeNode(3, new TreeNode(1, null, new TreeNode(2)), new TreeNode(4));
        assertEquals(1, solution.kthSmallest(root, 1));
    }

    @Test
    void kthSmallest3() {
        TreeNode root = new TreeNode(5,
                new TreeNode(3, new TreeNode(2, new TreeNode(1), null), new TreeNode(4)),
                new TreeNode(6));
        assertEquals(3, solution.kthSmallest(root, 3));
    }

    @Test
    void kthSmallestLast() {
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        assertEquals(3, solution.kthSmallest(root, 3));
    }
}
