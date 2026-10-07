package com.interview.blind75.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SetMatrixZeroesTest {

    private final SetMatrixZeroes solution = new SetMatrixZeroes();

    @Test
    void example1() {
        int[][] m = {{1,1,1},{1,0,1},{1,1,1}};
        solution.setZeroes(m);
        assertArrayEquals(new int[][]{{1,0,1},{0,0,0},{1,0,1}}, m);
    }

    @Test
    void example2() {
        int[][] m = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        solution.setZeroes(m);
        assertArrayEquals(new int[][]{{0,0,0,0},{0,4,5,0},{0,3,1,0}}, m);
    }

    @Test
    void noZeroes() {
        int[][] m = {{1,2},{3,4}};
        solution.setZeroes(m);
        assertArrayEquals(new int[][]{{1,2},{3,4}}, m);
    }
}
