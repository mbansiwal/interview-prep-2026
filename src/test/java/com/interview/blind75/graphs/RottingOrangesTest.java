package com.interview.blind75.graphs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RottingOrangesTest {

    private final RottingOranges solution = new RottingOranges();

    @Test
    void fourMinutes() {
        assertEquals(4, solution.orangesRotting(new int[][]{{2,1,1},{1,1,0},{0,1,1}}));
    }

    @Test
    void impossible() {
        assertEquals(-1, solution.orangesRotting(new int[][]{{2,1,1},{0,1,1},{1,0,1}}));
    }

    @Test
    void noFresh() {
        assertEquals(0, solution.orangesRotting(new int[][]{{0,2}}));
    }
}
