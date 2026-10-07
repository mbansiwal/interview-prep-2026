package com.interview.blind75.heap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LastStoneWeightTest {

    private final LastStoneWeight solution = new LastStoneWeight();

    @Test
    void example() {
        assertEquals(1, solution.lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
    }

    @Test
    void singleStone() {
        assertEquals(1, solution.lastStoneWeight(new int[]{1}));
    }

    @Test
    void allSame() {
        assertEquals(0, solution.lastStoneWeight(new int[]{2, 2}));
    }

    @Test
    void allDestroyed() {
        assertEquals(0, solution.lastStoneWeight(new int[]{2, 2, 4, 4}));
    }
}
