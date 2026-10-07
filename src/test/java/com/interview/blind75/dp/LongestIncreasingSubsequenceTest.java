package com.interview.blind75.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LongestIncreasingSubsequenceTest {

    private final LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();

    @Test
    void example1() { assertEquals(4, solution.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18})); }

    @Test
    void example2() { assertEquals(4, solution.lengthOfLIS(new int[]{0,1,0,3,2,3})); }

    @Test
    void allSame() { assertEquals(1, solution.lengthOfLIS(new int[]{7,7,7,7,7})); }
}
