package com.interview.blind75.arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContainsDuplicateTest {

    private final ContainsDuplicate solution = new ContainsDuplicate();

    @Test
    void hasDuplicate() {
        assertTrue(solution.containsDuplicate(new int[]{1, 2, 3, 1}));
    }

    @Test
    void noDuplicate() {
        assertFalse(solution.containsDuplicate(new int[]{1, 2, 3, 4}));
    }

    @Test
    void singleElement() {
        assertFalse(solution.containsDuplicate(new int[]{1}));
    }

    @Test
    void allSame() {
        assertTrue(solution.containsDuplicate(new int[]{5, 5, 5, 5}));
    }
}
