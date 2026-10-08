package com.interview.blind75.trees;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeRightSideViewTest {

    private static final BinaryTreeRightSideView solution = new BinaryTreeRightSideView();

    static Stream<Function<TreeNode, List<Integer>>> approaches() {
        return Stream.of(solution::rightSideView, solution::rightSideViewDfs);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void rightSideView(Function<TreeNode, List<Integer>> view) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(5)),
                new TreeNode(3, null, new TreeNode(4)));
        assertEquals(List.of(1, 3, 4), view.apply(root));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void leftNodeVisibleWhenDeeper(Function<TreeNode, List<Integer>> view) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3));
        assertEquals(List.of(1, 3, 4), view.apply(root));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleNode(Function<TreeNode, List<Integer>> view) {
        assertEquals(List.of(1), view.apply(new TreeNode(1)));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void nullTree(Function<TreeNode, List<Integer>> view) {
        assertEquals(List.of(), view.apply(null));
    }
}
