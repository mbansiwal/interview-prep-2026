package com.interview.blind75.linkedlist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ReverseLinkedListTest {

    private static final ReverseLinkedList solution = new ReverseLinkedList();

    static Stream<UnaryOperator<ListNode>> approaches() {
        return Stream.of(solution::reverseList, solution::reverseListRecursive);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void fiveNodes(UnaryOperator<ListNode> reverse) {
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, reverse.apply(ListNode.of(1, 2, 3, 4, 5)).toArray());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void twoNodes(UnaryOperator<ListNode> reverse) {
        assertArrayEquals(new int[]{2, 1}, reverse.apply(ListNode.of(1, 2)).toArray());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void nullInput(UnaryOperator<ListNode> reverse) {
        assertNull(reverse.apply(null));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleNode(UnaryOperator<ListNode> reverse) {
        assertArrayEquals(new int[]{1}, reverse.apply(ListNode.of(1)).toArray());
    }
}
