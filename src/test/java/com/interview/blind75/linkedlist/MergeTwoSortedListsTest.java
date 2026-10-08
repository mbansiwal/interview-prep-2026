package com.interview.blind75.linkedlist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MergeTwoSortedListsTest {

    private static final MergeTwoSortedLists solution = new MergeTwoSortedLists();

    static Stream<BinaryOperator<ListNode>> approaches() {
        return Stream.of(solution::mergeTwoLists, solution::mergeTwoListsRecursive);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void mergeTwoLists(BinaryOperator<ListNode> merge) {
        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 4},
                merge.apply(ListNode.of(1, 2, 4), ListNode.of(1, 3, 4)).toArray());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void bothEmpty(BinaryOperator<ListNode> merge) {
        assertNull(merge.apply(null, null));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void oneEmpty(BinaryOperator<ListNode> merge) {
        assertArrayEquals(new int[]{1, 2}, merge.apply(null, ListNode.of(1, 2)).toArray());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void unevenLengths(BinaryOperator<ListNode> merge) {
        assertArrayEquals(new int[]{-3, 0, 5, 7, 8, 9},
                merge.apply(ListNode.of(5), ListNode.of(-3, 0, 7, 8, 9)).toArray());
    }
}
