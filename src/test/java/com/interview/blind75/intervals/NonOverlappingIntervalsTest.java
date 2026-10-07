package com.interview.blind75.intervals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NonOverlappingIntervalsTest {

    private final NonOverlappingIntervals solution = new NonOverlappingIntervals();

    @Test
    void removeOne() { assertEquals(1, solution.eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}})); }

    @Test
    void removeTwo() { assertEquals(2, solution.eraseOverlapIntervals(new int[][]{{1,2},{1,2},{1,2}})); }

    @Test
    void noRemoval() { assertEquals(0, solution.eraseOverlapIntervals(new int[][]{{1,2},{2,3}})); }
}
