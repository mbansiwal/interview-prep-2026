package com.interview.blind75.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RotateImageTest {

    private final RotateImage solution = new RotateImage();

    @Test
    void rotate3x3() {
        int[][] m = {{1,2,3},{4,5,6},{7,8,9}};
        solution.rotate(m);
        assertArrayEquals(new int[][]{{7,4,1},{8,5,2},{9,6,3}}, m);
    }

    @Test
    void rotate4x4() {
        int[][] m = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        solution.rotate(m);
        assertArrayEquals(new int[][]{{15,13,2,5},{14,3,4,1},{12,6,8,9},{16,7,10,11}}, m);
    }

    @Test
    void rotate1x1() {
        int[][] m = {{1}};
        solution.rotate(m);
        assertArrayEquals(new int[][]{{1}}, m);
    }
}
