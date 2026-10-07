package com.interview.blind75.linkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReverseLinkedListTest {

    private final ReverseLinkedList solution = new ReverseLinkedList();

    @Test
    void fiveNodes() {
        assertArrayEquals(new int[]{5, 4, 3, 2, 1},
                solution.reverseList(ListNode.of(1, 2, 3, 4, 5)).toArray());
    }

    @Test
    void twoNodes() {
        assertArrayEquals(new int[]{2, 1},
                solution.reverseList(ListNode.of(1, 2)).toArray());
    }

    @Test
    void nullInput() {
        assertNull(solution.reverseList(null));
    }

    @Test
    void singleNode() {
        assertArrayEquals(new int[]{1},
                solution.reverseList(ListNode.of(1)).toArray());
    }
}
