package com.interview.blind75.stack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarFleetTest {

    private final CarFleet solution = new CarFleet();

    @Test
    void threeFleets() {
        assertEquals(3, solution.carFleet(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3}));
    }

    @Test
    void singleCar() {
        assertEquals(1, solution.carFleet(10, new int[]{3}, new int[]{3}));
    }

    @Test
    void allMerge() {
        assertEquals(1, solution.carFleet(10, new int[]{0, 4, 2}, new int[]{2, 1, 3}));
    }
}
