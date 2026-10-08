package com.interview.blind75.trees;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SameTreeTest {

    private static final SameTree solution = new SameTree();

    static Stream<BiPredicate<TreeNode, TreeNode>> approaches() {
        return Stream.of(solution::isSameTree, solution::isSameTreeIterative);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void identical(BiPredicate<TreeNode, TreeNode> same) {
        TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode q = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        assertTrue(same.test(p, q));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void differentStructure(BiPredicate<TreeNode, TreeNode> same) {
        TreeNode p = new TreeNode(1, new TreeNode(2), null);
        TreeNode q = new TreeNode(1, null, new TreeNode(2));
        assertFalse(same.test(p, q));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void differentValues(BiPredicate<TreeNode, TreeNode> same) {
        TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(1));
        TreeNode q = new TreeNode(1, new TreeNode(1), new TreeNode(2));
        assertFalse(same.test(p, q));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void bothNullAndOneNull(BiPredicate<TreeNode, TreeNode> same) {
        assertTrue(same.test(null, null));
        assertFalse(same.test(new TreeNode(1), null));
    }
}
