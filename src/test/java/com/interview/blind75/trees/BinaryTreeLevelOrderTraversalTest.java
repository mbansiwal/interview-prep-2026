package com.interview.blind75.trees;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeLevelOrderTraversalTest {

    private final BinaryTreeLevelOrderTraversal solution = new BinaryTreeLevelOrderTraversal();

    @Test
    void threeLevel() {
        TreeNode root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        List<List<Integer>> expected = List.of(List.of(3), List.of(9, 20), List.of(15, 7));
        assertEquals(expected, solution.levelOrder(root));
    }

    @Test
    void singleNode() {
        assertEquals(List.of(List.of(1)), solution.levelOrder(new TreeNode(1)));
    }

    @Test
    void nullTree() {
        assertEquals(Collections.emptyList(), solution.levelOrder(null));
    }
}
