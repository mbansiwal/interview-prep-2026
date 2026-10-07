package com.interview.blind75.linkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MergeTwoSortedListsTest {

    private final MergeTwoSortedLists solution = new MergeTwoSortedLists();

    @Test
    void mergeTwoLists() {
        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 4},
                solution.mergeTwoLists(ListNode.of(1, 2, 4), ListNode.of(1, 3, 4)).toArray());
    }

    @Test
    void bothEmpty() {
        assertNull(solution.mergeTwoLists(null, null));
    }

    @Test
    void oneEmpty() {
        assertArrayEquals(new int[]{1, 2},
                solution.mergeTwoLists(null, ListNode.of(1, 2)).toArray());
    }
}
