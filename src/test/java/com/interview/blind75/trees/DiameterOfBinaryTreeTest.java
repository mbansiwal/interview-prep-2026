package com.interview.blind75.trees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiameterOfBinaryTreeTest {

    private final DiameterOfBinaryTree solution = new DiameterOfBinaryTree();

    @Test
    void diameter3() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3));
        assertEquals(3, solution.diameterOfBinaryTree(root));
    }

    @Test
    void twoNodes() {
        assertEquals(1, solution.diameterOfBinaryTree(new TreeNode(1, new TreeNode(2), null)));
    }

    @Test
    void singleNode() {
        assertEquals(0, solution.diameterOfBinaryTree(new TreeNode(1)));
    }
}
