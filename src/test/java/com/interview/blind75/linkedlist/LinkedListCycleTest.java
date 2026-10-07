package com.interview.blind75.linkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinkedListCycleTest {

    private final LinkedListCycle solution = new LinkedListCycle();

    @Test
    void hasCycle() {
        ListNode head = ListNode.of(3, 2, 0, -4);
        // Create cycle: tail → node at index 1
        ListNode tail = head;
        ListNode cycleEntry = head.next;
        while (tail.next != null) tail = tail.next;
        tail.next = cycleEntry;
        assertTrue(solution.hasCycle(head));
    }

    @Test
    void noCycle() {
        assertFalse(solution.hasCycle(ListNode.of(1, 2, 3)));
    }

    @Test
    void nullHead() {
        assertFalse(solution.hasCycle(null));
    }
}
