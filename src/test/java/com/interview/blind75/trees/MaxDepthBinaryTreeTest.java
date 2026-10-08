package com.interview.blind75.trees;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MaxDepthBinaryTreeTest {

    private static final MaxDepthBinaryTree solution = new MaxDepthBinaryTree();

    static Stream<ToIntFunction<TreeNode>> approaches() {
        return Stream.of(solution::maxDepth, solution::maxDepthBfs);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void depth3(ToIntFunction<TreeNode> depth) {
        TreeNode root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        assertEquals(3, depth.applyAsInt(root));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void skewed(ToIntFunction<TreeNode> depth) {
        assertEquals(2, depth.applyAsInt(new TreeNode(1, null, new TreeNode(2))));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleNode(ToIntFunction<TreeNode> depth) {
        assertEquals(1, depth.applyAsInt(new TreeNode(1)));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void nullTree(ToIntFunction<TreeNode> depth) {
        assertEquals(0, depth.applyAsInt(null));
    }
}
