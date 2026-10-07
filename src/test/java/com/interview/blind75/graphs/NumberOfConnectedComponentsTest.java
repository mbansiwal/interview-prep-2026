package com.interview.blind75.graphs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberOfConnectedComponentsTest {

    private final NumberOfConnectedComponents solution = new NumberOfConnectedComponents();

    @Test
    void twoComponents() {
        assertEquals(2, solution.countComponents(5, new int[][]{{0, 1}, {1, 2}, {3, 4}}));
    }

    @Test
    void singleChain() {
        assertEquals(1, solution.countComponents(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}));
    }

    @Test
    void noEdgesEveryNodeAlone() {
        assertEquals(4, solution.countComponents(4, new int[][]{}));
    }

    @Test
    void cycleDoesNotDoubleCount() {
        assertEquals(2, solution.countComponents(4, new int[][]{{0, 1}, {1, 2}, {2, 0}}));
    }
}
