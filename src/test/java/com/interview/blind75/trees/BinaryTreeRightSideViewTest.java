package com.interview.blind75.trees;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeRightSideViewTest {

    private final BinaryTreeRightSideView solution = new BinaryTreeRightSideView();

    @Test
    void rightSideView() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(5)),
                new TreeNode(3, null, new TreeNode(4)));
        assertEquals(List.of(1, 3, 4), solution.rightSideView(root));
    }

    @Test
    void singleNode() {
        assertEquals(List.of(1), solution.rightSideView(new TreeNode(1)));
    }

    @Test
    void nullTree() {
        assertEquals(List.of(), solution.rightSideView(null));
    }
}
