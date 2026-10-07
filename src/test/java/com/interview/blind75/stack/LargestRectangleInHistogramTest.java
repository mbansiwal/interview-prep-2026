package com.interview.blind75.stack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LargestRectangleInHistogramTest {

    private final LargestRectangleInHistogram solution = new LargestRectangleInHistogram();

    @Test
    void basicExample() {
        assertEquals(10, solution.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }

    @Test
    void twoBar() {
        assertEquals(4, solution.largestRectangleArea(new int[]{2, 4}));
    }

    @Test
    void uniform() {
        assertEquals(9, solution.largestRectangleArea(new int[]{3, 3, 3}));
    }

    @Test
    void singleBar() {
        assertEquals(5, solution.largestRectangleArea(new int[]{5}));
    }
}
