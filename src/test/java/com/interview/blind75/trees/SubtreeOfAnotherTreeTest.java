package com.interview.blind75.trees;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SubtreeOfAnotherTreeTest {

    private static final SubtreeOfAnotherTree solution = new SubtreeOfAnotherTree();

    static Stream<BiPredicate<TreeNode, TreeNode>> approaches() {
        return Stream.of(solution::isSubtree, solution::isSubtreeKmp);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void isSubtree(BiPredicate<TreeNode, TreeNode> check) {
        TreeNode root = new TreeNode(3,
                new TreeNode(4, new TreeNode(1), new TreeNode(2)),
                new TreeNode(5));
        TreeNode subRoot = new TreeNode(4, new TreeNode(1), new TreeNode(2));
        assertTrue(check.test(root, subRoot));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void notSubtree(BiPredicate<TreeNode, TreeNode> check) {
        TreeNode root = new TreeNode(3,
                new TreeNode(4, new TreeNode(1), new TreeNode(2, new TreeNode(0), null)),
                new TreeNode(5));
        TreeNode subRoot = new TreeNode(4, new TreeNode(1), new TreeNode(2));
        assertFalse(check.test(root, subRoot));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void digitPrefixIsNotAMatch(BiPredicate<TreeNode, TreeNode> check) {
        // "2" must not match inside "12"
        assertFalse(check.test(new TreeNode(12), new TreeNode(2)));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void wholeTreeMatches(BiPredicate<TreeNode, TreeNode> check) {
        assertTrue(check.test(new TreeNode(1, new TreeNode(1), null), new TreeNode(1, new TreeNode(1), null)));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void nullRoot(BiPredicate<TreeNode, TreeNode> check) {
        assertFalse(check.test(null, new TreeNode(1)));
    }
}
