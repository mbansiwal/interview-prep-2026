package com.interview.blind75.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LongestCommonSubsequenceTest {

    private final LongestCommonSubsequence solution = new LongestCommonSubsequence();

    @Test
    void example1() { assertEquals(3, solution.longestCommonSubsequence("abcde", "ace")); }

    @Test
    void identical() { assertEquals(3, solution.longestCommonSubsequence("abc", "abc")); }

    @Test
    void noCommon() { assertEquals(0, solution.longestCommonSubsequence("abc", "def")); }
}
