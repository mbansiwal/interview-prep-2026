package com.interview.blind75.trees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountGoodNodesTest {

    private final CountGoodNodes solution = new CountGoodNodes();

    @Test
    void example1() {
        TreeNode root = new TreeNode(3,
                new TreeNode(1, new TreeNode(3), null),
                new TreeNode(4, new TreeNode(1), new TreeNode(5)));
        assertEquals(4, solution.goodNodes(root));
    }

    @Test
    void singleNode() {
        assertEquals(1, solution.goodNodes(new TreeNode(1)));
    }

    @Test
    void allGood() {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        assertEquals(3, solution.goodNodes(root));
    }
}
