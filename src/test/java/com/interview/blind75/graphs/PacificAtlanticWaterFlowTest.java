package com.interview.blind75.graphs;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PacificAtlanticWaterFlowTest {

    private final PacificAtlanticWaterFlow solution = new PacificAtlanticWaterFlow();

    @Test
    void example() {
        int[][] heights = {
            {1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}
        };
        List<List<Integer>> result = solution.pacificAtlantic(heights);
        assertEquals(7, result.size());
    }

    @Test
    void singleCell() {
        List<List<Integer>> result = solution.pacificAtlantic(new int[][]{{1}});
        assertEquals(1, result.size());
    }

    @Test
    void uniform() {
        int[][] heights = {{1,1},{1,1}};
        assertEquals(4, solution.pacificAtlantic(heights).size());
    }
}
