package com.interview.blind75.heap;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class KClosestPointsToOriginTest {

    private final KClosestPointsToOrigin solution = new KClosestPointsToOrigin();

    @Test
    void k1() {
        int[][] result = solution.kClosest(new int[][]{{1, 3}, {-2, 2}}, 1);
        assertEquals(1, result.length);
        assertArrayEquals(new int[]{-2, 2}, result[0]);
    }

    @Test
    void k2() {
        int[][] result = solution.kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2);
        assertEquals(2, result.length);
    }

    @Test
    void allPoints() {
        int[][] points = {{1, 0}, {0, 1}};
        assertEquals(2, solution.kClosest(points, 2).length);
    }
}
