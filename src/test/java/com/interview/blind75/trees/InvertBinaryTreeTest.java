package com.interview.blind75.trees;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InvertBinaryTreeTest {

    private static final InvertBinaryTree solution = new InvertBinaryTree();

    static Stream<UnaryOperator<TreeNode>> approaches() {
        return Stream.of(solution::invertTree, solution::invertTreeIterative);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void invertTree(UnaryOperator<TreeNode> invert) {
        TreeNode root = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7, new TreeNode(6), new TreeNode(9)));
        TreeNode inverted = invert.apply(root);
        assertEquals(7, inverted.left.val);
        assertEquals(2, inverted.right.val);
        assertEquals(9, inverted.left.left.val);
        assertEquals(6, inverted.left.right.val);
        assertEquals(3, inverted.right.left.val);
        assertEquals(1, inverted.right.right.val);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void skewedBecomesMirrored(UnaryOperator<TreeNode> invert) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), null), null);
        TreeNode inverted = invert.apply(root);
        assertNull(inverted.left);
        assertEquals(2, inverted.right.val);
        assertEquals(3, inverted.right.right.val);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void nullTree(UnaryOperator<TreeNode> invert) {
        assertNull(invert.apply(null));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleNode(UnaryOperator<TreeNode> invert) {
        assertEquals(1, invert.apply(new TreeNode(1)).val);
    }
}
