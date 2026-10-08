package com.interview.blind75.trees;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CountGoodNodesTest {

    private static final CountGoodNodes solution = new CountGoodNodes();

    static Stream<ToIntFunction<TreeNode>> approaches() {
        return Stream.of(solution::goodNodes, solution::goodNodesBfs);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void example1(ToIntFunction<TreeNode> count) {
        TreeNode root = new TreeNode(3,
                new TreeNode(1, new TreeNode(3), null),
                new TreeNode(4, new TreeNode(1), new TreeNode(5)));
        assertEquals(4, count.applyAsInt(root));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void example2(ToIntFunction<TreeNode> count) {
        TreeNode root = new TreeNode(3, new TreeNode(3, new TreeNode(4), new TreeNode(2)), null);
        assertEquals(3, count.applyAsInt(root));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleNode(ToIntFunction<TreeNode> count) {
        assertEquals(1, count.applyAsInt(new TreeNode(1)));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void allGood(ToIntFunction<TreeNode> count) {
        assertEquals(3, count.applyAsInt(new TreeNode(1, new TreeNode(2), new TreeNode(3))));
    }
}
