package com.interview.blind75.arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LongestConsecutiveSequenceTest {

    private final LongestConsecutiveSequence solution = new LongestConsecutiveSequence();

    @Test
    void basicExample() {
        assertEquals(4, solution.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

    @Test
    void longerSequence() {
        assertEquals(9, solution.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
    }

    @Test
    void emptyArray() {
        assertEquals(0, solution.longestConsecutive(new int[]{}));
    }

    @Test
    void singleElement() {
        assertEquals(1, solution.longestConsecutive(new int[]{5}));
    }
}
