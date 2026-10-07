package com.interview.blind75.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HouseRobberTest {

    private final HouseRobber solution = new HouseRobber();

    @Test
    void example1() { assertEquals(4, solution.rob(new int[]{1, 2, 3, 1})); }

    @Test
    void example2() { assertEquals(12, solution.rob(new int[]{2, 7, 9, 3, 1})); }

    @Test
    void singleHouse() { assertEquals(5, solution.rob(new int[]{5})); }
}
