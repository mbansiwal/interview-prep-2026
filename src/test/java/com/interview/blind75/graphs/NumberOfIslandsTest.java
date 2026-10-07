package com.interview.blind75.graphs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberOfIslandsTest {

    private final NumberOfIslands solution = new NumberOfIslands();

    @Test
    void oneIsland() {
        char[][] grid = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        assertEquals(1, solution.numIslands(grid));
    }

    @Test
    void threeIslands() {
        char[][] grid = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        assertEquals(3, solution.numIslands(grid));
    }

    @Test
    void allWater() {
        char[][] grid = {{'0','0'},{'0','0'}};
        assertEquals(0, solution.numIslands(grid));
    }
}
