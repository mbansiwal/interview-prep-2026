package com.interview.blind75.linkedlist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ReorderListTest {

    private static final ReorderList solution = new ReorderList();

    static Stream<Consumer<ListNode>> approaches() {
        return Stream.of(solution::reorderList, solution::reorderListWithArray);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void evenLength(Consumer<ListNode> reorder) {
        ListNode head = ListNode.of(1, 2, 3, 4);
        reorder.accept(head);
        assertArrayEquals(new int[]{1, 4, 2, 3}, head.toArray());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void oddLength(Consumer<ListNode> reorder) {
        ListNode head = ListNode.of(1, 2, 3, 4, 5);
        reorder.accept(head);
        assertArrayEquals(new int[]{1, 5, 2, 4, 3}, head.toArray());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleNode(Consumer<ListNode> reorder) {
        ListNode head = ListNode.of(1);
        reorder.accept(head);
        assertArrayEquals(new int[]{1}, head.toArray());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void twoNodes(Consumer<ListNode> reorder) {
        ListNode head = ListNode.of(1, 2);
        reorder.accept(head);
        assertArrayEquals(new int[]{1, 2}, head.toArray());
    }
}
