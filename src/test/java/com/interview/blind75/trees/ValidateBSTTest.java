package com.interview.blind75.trees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidateBSTTest {

    private final ValidateBST solution = new ValidateBST();

    @Test
    void validBST() {
        assertTrue(solution.isValidBST(new TreeNode(2, new TreeNode(1), new TreeNode(3))));
    }

    @Test
    void invalidBST() {
        TreeNode root = new TreeNode(5,
                new TreeNode(1),
                new TreeNode(4, new TreeNode(3), new TreeNode(6)));
        assertFalse(solution.isValidBST(root));
    }

    @Test
    void hiddenViolation() {
        // [10,5,15,null,null,6,20] — 6 violates the left-subtree-of-10 constraint
        TreeNode root = new TreeNode(10,
                new TreeNode(5),
                new TreeNode(15, new TreeNode(6), new TreeNode(20)));
        assertFalse(solution.isValidBST(root));
    }

    @Test
    void singleNodeAtIntMax() {
        assertTrue(solution.isValidBST(new TreeNode(Integer.MAX_VALUE)));
    }

    @Test
    void intMinAndMaxValues() {
        // [-2147483648,null,2147483647] — int bounds would wrongly reject this
        TreeNode root = new TreeNode(Integer.MIN_VALUE, null, new TreeNode(Integer.MAX_VALUE));
        assertTrue(solution.isValidBST(root));
    }

    @Test
    void duplicateValueIsInvalid() {
        assertFalse(solution.isValidBST(new TreeNode(1, new TreeNode(1), null)));
    }
}
