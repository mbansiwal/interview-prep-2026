package com.interview.blind75.binarysearch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KokoEatingBananasTest {

    private final KokoEatingBananas solution = new KokoEatingBananas();

    @Test
    void example1() {
        assertEquals(4, solution.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
    }

    @Test
    void example2() {
        assertEquals(30, solution.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
    }

    @Test
    void singlePile() {
        assertEquals(2, solution.minEatingSpeed(new int[]{3}, 2));
    }
}
