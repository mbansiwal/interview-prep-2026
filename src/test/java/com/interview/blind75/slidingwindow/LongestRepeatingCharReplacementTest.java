package com.interview.blind75.slidingwindow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LongestRepeatingCharReplacementTest {

    private final LongestRepeatingCharReplacement solution = new LongestRepeatingCharReplacement();

    @Test
    void abab() {
        assertEquals(4, solution.characterReplacement("ABAB", 2));
    }

    @Test
    void aababba() {
        assertEquals(4, solution.characterReplacement("AABABBA", 1));
    }

    @Test
    void noReplacements() {
        assertEquals(1, solution.characterReplacement("ABCD", 0));
    }

    @Test
    void allSame() {
        assertEquals(5, solution.characterReplacement("AAAAA", 0));
    }
}
