package com.interview.blind75.binarysearch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FindMinInRotatedSortedArrayTest {

    private final FindMinInRotatedSortedArray solution = new FindMinInRotatedSortedArray();

    @Test
    void rotated() {
        assertEquals(1, solution.findMin(new int[]{3, 4, 5, 1, 2}));
    }

    @Test
    void rotatedLonger() {
        assertEquals(0, solution.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
    }

    @Test
    void notRotated() {
        assertEquals(0, solution.findMin(new int[]{0, 1, 2}));
    }

    @Test
    void singleElement() {
        assertEquals(5, solution.findMin(new int[]{5}));
    }
}
