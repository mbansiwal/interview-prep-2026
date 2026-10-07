package com.interview.blind75.linkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FindDuplicateNumberTest {

    private final FindDuplicateNumber solution = new FindDuplicateNumber();

    @Test
    void example1() {
        assertEquals(2, solution.findDuplicate(new int[]{1, 3, 4, 2, 2}));
    }

    @Test
    void example2() {
        assertEquals(3, solution.findDuplicate(new int[]{3, 1, 3, 4, 2}));
    }

    @Test
    void duplicateAtStart() {
        assertEquals(1, solution.findDuplicate(new int[]{1, 1}));
    }
}
