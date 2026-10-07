package com.interview.blind75.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClimbingStairsTest {

    private final ClimbingStairs solution = new ClimbingStairs();

    @Test
    void twoSteps() { assertEquals(2, solution.climbStairs(2)); }

    @Test
    void threeSteps() { assertEquals(3, solution.climbStairs(3)); }

    @Test
    void tenSteps() { assertEquals(89, solution.climbStairs(10)); }
}
