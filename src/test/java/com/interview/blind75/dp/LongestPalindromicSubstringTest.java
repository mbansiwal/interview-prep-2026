package com.interview.blind75.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LongestPalindromicSubstringTest {

    private final LongestPalindromicSubstring solution = new LongestPalindromicSubstring();

    @Test
    void oddLength() {
        String result = solution.longestPalindrome("babad");
        assertTrue(result.equals("bab") || result.equals("aba"));
    }

    @Test
    void evenLength() {
        assertEquals("bb", solution.longestPalindrome("cbbd"));
    }

    @Test
    void singleChar() {
        assertEquals("a", solution.longestPalindrome("a"));
    }
}
