package com.interview.blind75.slidingwindow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LongestSubstringWithoutRepeatingTest {

    private final LongestSubstringWithoutRepeating solution = new LongestSubstringWithoutRepeating();

    @Test
    void basicExample() {
        assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"));
    }

    @Test
    void allSame() {
        assertEquals(1, solution.lengthOfLongestSubstring("bbbbb"));
    }

    @Test
    void allUnique() {
        assertEquals(3, solution.lengthOfLongestSubstring("pwwkew"));
    }

    @Test
    void emptyString() {
        assertEquals(0, solution.lengthOfLongestSubstring(""));
    }
}
