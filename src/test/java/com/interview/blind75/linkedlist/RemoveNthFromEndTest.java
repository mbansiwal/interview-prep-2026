package com.interview.blind75.linkedlist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RemoveNthFromEndTest {

    private static final RemoveNthFromEnd solution = new RemoveNthFromEnd();

    static Stream<BiFunction<ListNode, Integer, ListNode>> approaches() {
        return Stream.of(solution::removeNthFromEnd, solution::removeNthFromEndTwoPass);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void removeSecondFromEnd(BiFunction<ListNode, Integer, ListNode> remove) {
        assertArrayEquals(new int[]{1, 2, 3, 5}, remove.apply(ListNode.of(1, 2, 3, 4, 5), 2).toArray());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void removeLast(BiFunction<ListNode, Integer, ListNode> remove) {
        assertArrayEquals(new int[]{1, 2}, remove.apply(ListNode.of(1, 2, 3), 1).toArray());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void removeHead(BiFunction<ListNode, Integer, ListNode> remove) {
        assertArrayEquals(new int[]{2, 3}, remove.apply(ListNode.of(1, 2, 3), 3).toArray());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void removeOnly(BiFunction<ListNode, Integer, ListNode> remove) {
        assertNull(remove.apply(ListNode.of(1), 1));
    }
}
