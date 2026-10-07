package com.interview.blind75.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DecodeWaysTest {

    private final DecodeWays solution = new DecodeWays();

    @Test
    void twoWays() { assertEquals(2, solution.numDecodings("12")); }

    @Test
    void threeWays() { assertEquals(3, solution.numDecodings("226")); }

    @Test
    void leadingZero() { assertEquals(0, solution.numDecodings("06")); }

    @Test
    void tenIsOnlyAPair() { assertEquals(1, solution.numDecodings("10")); }

    @Test
    void doubleZeroImpossible() { assertEquals(0, solution.numDecodings("100")); }

    @Test
    void zeroInMiddleForcesPairing() {
        // must split as 2|10|1 — "21" would leave "01", which is invalid
        assertEquals(1, solution.numDecodings("2101"));
    }
}
