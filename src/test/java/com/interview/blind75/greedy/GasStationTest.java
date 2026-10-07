package com.interview.blind75.greedy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GasStationTest {

    private final GasStation solution = new GasStation();

    @Test
    void example1() { assertEquals(3, solution.canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2})); }

    @Test
    void impossible() { assertEquals(-1, solution.canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3})); }

    @Test
    void single() { assertEquals(0, solution.canCompleteCircuit(new int[]{5}, new int[]{4})); }
}
