package com.interview.blind75.graphs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaxAreaOfIslandTest {

    private final MaxAreaOfIsland solution = new MaxAreaOfIsland();

    @Test
    void multipleIslands() {
        int[][] grid = {
            {0,0,1,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,1,1,0,1,0,0,0,0,0,0,0,0},
            {0,1,0,0,1,1,0,0,1,0,1,0,0},
            {0,1,0,0,1,1,0,0,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
        assertEquals(6, solution.maxAreaOfIsland(grid));
    }

    @Test
    void noIsland() {
        int[][] grid = {{0, 0, 0, 0, 0, 0, 0, 0}};
        assertEquals(0, solution.maxAreaOfIsland(grid));
    }

    @Test
    void singleCell() {
        int[][] grid = {{1}};
        assertEquals(1, solution.maxAreaOfIsland(grid));
    }
}
