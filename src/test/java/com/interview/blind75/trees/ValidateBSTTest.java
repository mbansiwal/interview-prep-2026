package com.interview.blind75.trees;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ValidateBSTTest {

    private static final ValidateBST solution = new ValidateBST();

    static Stream<Predicate<TreeNode>> approaches() {
        return Stream.of(solution::isValidBST, solution::isValidBSTInorder);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void validBST(Predicate<TreeNode> valid) {
        assertTrue(valid.test(new TreeNode(2, new TreeNode(1), new TreeNode(3))));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void invalidBST(Predicate<TreeNode> valid) {
        TreeNode root = new TreeNode(5,
                new TreeNode(1),
                new TreeNode(4, new TreeNode(3), new TreeNode(6)));
        assertFalse(valid.test(root));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void hiddenViolation(Predicate<TreeNode> valid) {
        // [10,5,15,null,null,6,20] — 6 violates the left-subtree-of-10 constraint
        TreeNode root = new TreeNode(10,
                new TreeNode(5),
                new TreeNode(15, new TreeNode(6), new TreeNode(20)));
        assertFalse(valid.test(root));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleNodeAtIntMax(Predicate<TreeNode> valid) {
        assertTrue(valid.test(new TreeNode(Integer.MAX_VALUE)));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void intMinAndMaxValues(Predicate<TreeNode> valid) {
        TreeNode root = new TreeNode(Integer.MIN_VALUE, null, new TreeNode(Integer.MAX_VALUE));
        assertTrue(valid.test(root));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void duplicateValueIsInvalid(Predicate<TreeNode> valid) {
        assertFalse(valid.test(new TreeNode(1, new TreeNode(1), null)));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void emptyTree(Predicate<TreeNode> valid) {
        assertTrue(valid.test(null));
    }
}
