package com.interview.blind75.graphs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RedundantConnectionTest {

    private final RedundantConnection solution = new RedundantConnection();

    @Test
    void example1() {
        assertArrayEquals(new int[]{2, 3},
            solution.findRedundantConnection(new int[][]{{1,2},{1,3},{2,3}}));
    }

    @Test
    void example2() {
        assertArrayEquals(new int[]{1, 4},
            solution.findRedundantConnection(new int[][]{{1,2},{2,3},{3,4},{1,4},{1,5}}));
    }

    @Test
    void simpleLoop() {
        assertArrayEquals(new int[]{1, 2},
            solution.findRedundantConnection(new int[][]{{1,2},{1,2}}));
    }
}
