package com.interview.blind75.graphs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GraphValidTreeTest {

    private final GraphValidTree solution = new GraphValidTree();

    @Test
    void validTree() {
        assertTrue(solution.validTree(5, new int[][]{{0,1},{0,2},{0,3},{1,4}}));
    }

    @Test
    void cycleInvalid() {
        assertFalse(solution.validTree(5, new int[][]{{0,1},{1,2},{2,3},{1,3},{1,4}}));
    }

    @Test
    void singleNode() {
        assertTrue(solution.validTree(1, new int[][]{}));
    }
}
