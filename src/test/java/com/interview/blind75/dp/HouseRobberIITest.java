package com.interview.blind75.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HouseRobberIITest {

    private final HouseRobberII solution = new HouseRobberII();

    @Test
    void circularThree() { assertEquals(3, solution.rob(new int[]{2, 3, 2})); }

    @Test
    void circularFour() { assertEquals(4, solution.rob(new int[]{1, 2, 3, 1})); }

    @Test
    void singleHouse() { assertEquals(1, solution.rob(new int[]{1})); }
}
