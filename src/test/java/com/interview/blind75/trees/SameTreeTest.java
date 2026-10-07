package com.interview.blind75.trees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SameTreeTest {

    private final SameTree solution = new SameTree();

    @Test
    void identical() {
        TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode q = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        assertTrue(solution.isSameTree(p, q));
    }

    @Test
    void differentStructure() {
        TreeNode p = new TreeNode(1, new TreeNode(2), null);
        TreeNode q = new TreeNode(1, null, new TreeNode(2));
        assertFalse(solution.isSameTree(p, q));
    }

    @Test
    void differentValues() {
        TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(1));
        TreeNode q = new TreeNode(1, new TreeNode(1), new TreeNode(2));
        assertFalse(solution.isSameTree(p, q));
    }
}
