package com.interview.blind75.trees;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeLevelOrderTraversalTest {

    private static final BinaryTreeLevelOrderTraversal solution = new BinaryTreeLevelOrderTraversal();

    static Stream<Function<TreeNode, List<List<Integer>>>> approaches() {
        return Stream.of(solution::levelOrder, solution::levelOrderDfs);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void threeLevel(Function<TreeNode, List<List<Integer>>> traverse) {
        TreeNode root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        assertEquals(List.of(List.of(3), List.of(9, 20), List.of(15, 7)), traverse.apply(root));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void unevenLevels(Function<TreeNode, List<List<Integer>>> traverse) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), null),
                new TreeNode(3, null, new TreeNode(5, new TreeNode(6), null)));
        assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5), List.of(6)), traverse.apply(root));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleNode(Function<TreeNode, List<List<Integer>>> traverse) {
        assertEquals(List.of(List.of(1)), traverse.apply(new TreeNode(1)));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void nullTree(Function<TreeNode, List<List<Integer>>> traverse) {
        assertEquals(List.of(), traverse.apply(null));
    }
}
