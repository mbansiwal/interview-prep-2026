package com.interview.blind75.linkedlist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ReverseNodesInKGroupTest {

    private static final ReverseNodesInKGroup solution = new ReverseNodesInKGroup();

    static Stream<BiFunction<ListNode, Integer, ListNode>> approaches() {
        return Stream.of(solution::reverseKGroup, solution::reverseKGroupRecursive);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void k2(BiFunction<ListNode, Integer, ListNode> reverse) {
        assertArrayEquals(new int[]{2, 1, 4, 3, 5}, reverse.apply(ListNode.of(1, 2, 3, 4, 5), 2).toArray());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void k3(BiFunction<ListNode, Integer, ListNode> reverse) {
        assertArrayEquals(new int[]{3, 2, 1, 4, 5}, reverse.apply(ListNode.of(1, 2, 3, 4, 5), 3).toArray());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void k1(BiFunction<ListNode, Integer, ListNode> reverse) {
        assertArrayEquals(new int[]{1, 2, 3}, reverse.apply(ListNode.of(1, 2, 3), 1).toArray());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void kEqualsLength(BiFunction<ListNode, Integer, ListNode> reverse) {
        assertArrayEquals(new int[]{4, 3, 2, 1}, reverse.apply(ListNode.of(1, 2, 3, 4), 4).toArray());
    }
}
