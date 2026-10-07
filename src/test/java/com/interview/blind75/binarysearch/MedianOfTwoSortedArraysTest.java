package com.interview.blind75.binarysearch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MedianOfTwoSortedArraysTest {

    private final MedianOfTwoSortedArrays solution = new MedianOfTwoSortedArrays();

    @Test
    void oddTotal() {
        assertEquals(2.0, solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }

    @Test
    void evenTotal() {
        assertEquals(2.5, solution.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }

    @Test
    void oneEmpty() {
        assertEquals(2.0, solution.findMedianSortedArrays(new int[]{}, new int[]{1, 2, 3}));
    }

    @Test
    void identical() {
        assertEquals(1.0, solution.findMedianSortedArrays(new int[]{1}, new int[]{1}));
    }
}
