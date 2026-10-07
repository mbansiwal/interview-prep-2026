package com.interview.blind75.linkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RemoveNthFromEndTest {

    private final RemoveNthFromEnd solution = new RemoveNthFromEnd();

    @Test
    void removeSecondFromEnd() {
        assertArrayEquals(new int[]{1, 2, 3, 5},
                solution.removeNthFromEnd(ListNode.of(1, 2, 3, 4, 5), 2).toArray());
    }

    @Test
    void removeLast() {
        assertArrayEquals(new int[]{1, 2},
                solution.removeNthFromEnd(ListNode.of(1, 2, 3), 1).toArray());
    }

    @Test
    void removeOnly() {
        assertNull(solution.removeNthFromEnd(ListNode.of(1), 1));
    }
}
