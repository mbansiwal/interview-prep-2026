package com.interview.blind75.linkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReorderListTest {

    private final ReorderList solution = new ReorderList();

    @Test
    void evenLength() {
        ListNode head = ListNode.of(1, 2, 3, 4);
        solution.reorderList(head);
        assertArrayEquals(new int[]{1, 4, 2, 3}, head.toArray());
    }

    @Test
    void oddLength() {
        ListNode head = ListNode.of(1, 2, 3, 4, 5);
        solution.reorderList(head);
        assertArrayEquals(new int[]{1, 5, 2, 4, 3}, head.toArray());
    }

    @Test
    void singleNode() {
        ListNode head = ListNode.of(1);
        solution.reorderList(head);
        assertArrayEquals(new int[]{1}, head.toArray());
    }
}
