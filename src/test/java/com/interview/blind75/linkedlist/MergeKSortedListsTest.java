package com.interview.blind75.linkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MergeKSortedListsTest {

    private final MergeKSortedLists solution = new MergeKSortedLists();

    @Test
    void threeListsMerge() {
        ListNode[] lists = {ListNode.of(1, 4, 5), ListNode.of(1, 3, 4), ListNode.of(2, 6)};
        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 4, 5, 6}, solution.mergeKLists(lists).toArray());
    }

    @Test
    void emptyArray() {
        assertNull(solution.mergeKLists(new ListNode[]{}));
    }

    @Test
    void singleNullList() {
        assertNull(solution.mergeKLists(new ListNode[]{null}));
    }
}
