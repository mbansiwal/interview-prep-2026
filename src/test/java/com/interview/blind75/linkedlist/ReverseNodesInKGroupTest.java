package com.interview.blind75.linkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReverseNodesInKGroupTest {

    private final ReverseNodesInKGroup solution = new ReverseNodesInKGroup();

    @Test
    void k2() {
        assertArrayEquals(new int[]{2, 1, 4, 3, 5},
                solution.reverseKGroup(ListNode.of(1, 2, 3, 4, 5), 2).toArray());
    }

    @Test
    void k3() {
        assertArrayEquals(new int[]{3, 2, 1, 4, 5},
                solution.reverseKGroup(ListNode.of(1, 2, 3, 4, 5), 3).toArray());
    }

    @Test
    void k1() {
        assertArrayEquals(new int[]{1, 2, 3},
                solution.reverseKGroup(ListNode.of(1, 2, 3), 1).toArray());
    }
}
