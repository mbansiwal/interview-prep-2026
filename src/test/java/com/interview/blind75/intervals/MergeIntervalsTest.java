package com.interview.blind75.intervals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MergeIntervalsTest {

    private final MergeIntervals solution = new MergeIntervals();

    @Test
    void example1() {
        assertArrayEquals(new int[][]{{1,6},{8,10},{15,18}},
            solution.merge(new int[][]{{1,3},{2,6},{8,10},{15,18}}));
    }

    @Test
    void touchingIntervals() {
        assertArrayEquals(new int[][]{{1,5}},
            solution.merge(new int[][]{{1,4},{4,5}}));
    }

    @Test
    void single() {
        assertArrayEquals(new int[][]{{1,2}},
            solution.merge(new int[][]{{1,2}}));
    }
}
