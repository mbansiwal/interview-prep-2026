package com.interview.blind75.intervals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InsertIntervalTest {

    private final InsertInterval solution = new InsertInterval();

    @Test
    void mergeInMiddle() {
        assertArrayEquals(new int[][]{{1,5},{6,9}},
            solution.insert(new int[][]{{1,3},{6,9}}, new int[]{2,5}));
    }

    @Test
    void mergeMultiple() {
        assertArrayEquals(new int[][]{{1,2},{3,10},{12,16}},
            solution.insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[]{4,8}));
    }

    @Test
    void noMerge() {
        assertArrayEquals(new int[][]{{1,2},{3,4},{5,6}},
            solution.insert(new int[][]{{1,2},{5,6}}, new int[]{3,4}));
    }
}
