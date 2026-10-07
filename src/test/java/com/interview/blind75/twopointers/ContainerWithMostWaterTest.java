package com.interview.blind75.twopointers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContainerWithMostWaterTest {

    private final ContainerWithMostWater solution = new ContainerWithMostWater();

    @Test
    void standardExample() {
        assertEquals(49, solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    @Test
    void twoElements() {
        assertEquals(1, solution.maxArea(new int[]{1, 1}));
    }

    @Test
    void increasing() {
        assertEquals(6, solution.maxArea(new int[]{1, 2, 3, 4, 5}));
    }
}
