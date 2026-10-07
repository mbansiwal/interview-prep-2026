package com.interview.blind75.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PalindromicSubstringsTest {

    private final PalindromicSubstrings solution = new PalindromicSubstrings();

    @Test
    void allDistinct() { assertEquals(3, solution.countSubstrings("abc")); }

    @Test
    void allSame() { assertEquals(6, solution.countSubstrings("aaa")); }

    @Test
    void singleChar() { assertEquals(1, solution.countSubstrings("a")); }
}
