package com.interview.blind75.math;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SpiralMatrixTest {

    private final SpiralMatrix solution = new SpiralMatrix();

    @Test
    void threeByThree() {
        assertEquals(List.of(1,2,3,6,9,8,7,4,5),
            solution.spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
    }

    @Test
    void threeByFour() {
        assertEquals(List.of(1,2,3,4,8,12,11,10,9,5,6,7),
            solution.spiralOrder(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
    }

    @Test
    void singleRow() {
        assertEquals(List.of(1,2,3), solution.spiralOrder(new int[][]{{1,2,3}}));
    }
}
