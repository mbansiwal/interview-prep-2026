package com.interview.blind75.slidingwindow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinWindowSubstringTest {

    private final MinWindowSubstring solution = new MinWindowSubstring();

    @Test
    void standardExample() {
        assertEquals("BANC", solution.minWindow("ADOBECODEBANC", "ABC"));
    }

    @Test
    void exact() {
        assertEquals("a", solution.minWindow("a", "a"));
    }

    @Test
    void noWindow() {
        assertEquals("", solution.minWindow("a", "aa"));
    }

    @Test
    void duplicatesInT() {
        assertEquals("aa", solution.minWindow("aa", "aa"));
    }
}
