package com.interview.blind75.linkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddTwoNumbersTest {

    private final AddTwoNumbers solution = new AddTwoNumbers();

    @Test
    void basicAddition() {
        assertArrayEquals(new int[]{7, 0, 8},
                solution.addTwoNumbers(ListNode.of(2, 4, 3), ListNode.of(5, 6, 4)).toArray());
    }

    @Test
    void withCarry() {
        assertArrayEquals(new int[]{8, 9, 9, 0, 1},
                solution.addTwoNumbers(ListNode.of(9, 9, 9, 9), ListNode.of(9, 9, 9)).toArray());
    }

    @Test
    void zeros() {
        assertArrayEquals(new int[]{0},
                solution.addTwoNumbers(ListNode.of(0), ListNode.of(0)).toArray());
    }
}
