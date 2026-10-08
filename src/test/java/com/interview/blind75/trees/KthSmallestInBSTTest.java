package com.interview.blind75.trees;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntBiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class KthSmallestInBSTTest {

    private static final KthSmallestInBST solution = new KthSmallestInBST();

    static Stream<ToIntBiFunction<TreeNode, Integer>> approaches() {
        return Stream.of(solution::kthSmallest, solution::kthSmallestMorris);
    }

    private static TreeNode bst() {
        return new TreeNode(5,
                new TreeNode(3, new TreeNode(2, new TreeNode(1), null), new TreeNode(4)),
                new TreeNode(6));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void kthSmallest1(ToIntBiFunction<TreeNode, Integer> kth) {
        TreeNode root = new TreeNode(3, new TreeNode(1, null, new TreeNode(2)), new TreeNode(4));
        assertEquals(1, kth.applyAsInt(root, 1));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void kthSmallest3(ToIntBiFunction<TreeNode, Integer> kth) {
        assertEquals(3, kth.applyAsInt(bst(), 3));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void kthSmallestLast(ToIntBiFunction<TreeNode, Integer> kth) {
        assertEquals(3, kth.applyAsInt(new TreeNode(2, new TreeNode(1), new TreeNode(3)), 3));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void everyRankAndTreeLeftIntact(ToIntBiFunction<TreeNode, Integer> kth) {
        TreeNode root = bst();
        for (int k = 1; k <= 6; k++) assertEquals(k, kth.applyAsInt(root, k));
        // Morris threads must be removed: structure unchanged
        assertNull(root.left.right.right);
        assertNull(root.left.left.left.right);
        assertNull(root.right.right);
    }
}
