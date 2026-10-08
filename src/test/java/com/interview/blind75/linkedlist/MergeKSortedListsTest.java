package com.interview.blind75.linkedlist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MergeKSortedListsTest {

    private static final MergeKSortedLists solution = new MergeKSortedLists();

    static Stream<Function<ListNode[], ListNode>> approaches() {
        return Stream.of(solution::mergeKLists, solution::mergeKListsDivideConquer);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void threeListsMerge(Function<ListNode[], ListNode> merge) {
        ListNode[] lists = {ListNode.of(1, 4, 5), ListNode.of(1, 3, 4), ListNode.of(2, 6)};
        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 4, 5, 6}, merge.apply(lists).toArray());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void fiveListsWithNulls(Function<ListNode[], ListNode> merge) {
        ListNode[] lists = {ListNode.of(5, 9), null, ListNode.of(-2, 7), ListNode.of(0), null};
        assertArrayEquals(new int[]{-2, 0, 5, 7, 9}, merge.apply(lists).toArray());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void emptyArray(Function<ListNode[], ListNode> merge) {
        assertNull(merge.apply(new ListNode[]{}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleNullList(Function<ListNode[], ListNode> merge) {
        assertNull(merge.apply(new ListNode[]{null}));
    }
}
