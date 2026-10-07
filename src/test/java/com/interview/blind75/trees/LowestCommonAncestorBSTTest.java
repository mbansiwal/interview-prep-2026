package com.interview.blind75.trees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LowestCommonAncestorBSTTest {

    private final LowestCommonAncestorBST solution = new LowestCommonAncestorBST();

    private TreeNode buildBST() {
        return new TreeNode(6,
                new TreeNode(2, new TreeNode(0), new TreeNode(4, new TreeNode(3), new TreeNode(5))),
                new TreeNode(8, new TreeNode(7), new TreeNode(9)));
    }

    @Test
    void lcaAtRoot() {
        TreeNode root = buildBST();
        TreeNode p = root.left;       // 2
        TreeNode q = root.right;      // 8
        assertEquals(6, solution.lowestCommonAncestor(root, p, q).val);
    }

    @Test
    void nodeIsLCA() {
        TreeNode root = buildBST();
        TreeNode p = root.left;       // 2
        TreeNode q = root.left.right; // 4
        assertEquals(2, solution.lowestCommonAncestor(root, p, q).val);
    }

    @Test
    void deepNodes() {
        TreeNode root = buildBST();
        TreeNode p = root.left.right.left;  // 3
        TreeNode q = root.left.right.right; // 5
        assertEquals(4, solution.lowestCommonAncestor(root, p, q).val);
    }
}
