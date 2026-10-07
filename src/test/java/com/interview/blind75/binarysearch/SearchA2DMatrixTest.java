package com.interview.blind75.binarysearch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SearchA2DMatrixTest {

    private final SearchA2DMatrix solution = new SearchA2DMatrix();
    private final int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};

    @Test
    void found() {
        assertTrue(solution.searchMatrix(matrix, 3));
    }

    @Test
    void notFound() {
        assertFalse(solution.searchMatrix(matrix, 13));
    }

    @Test
    void firstElement() {
        assertTrue(solution.searchMatrix(matrix, 1));
    }

    @Test
    void lastElement() {
        assertTrue(solution.searchMatrix(matrix, 60));
    }
}
