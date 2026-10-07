package com.interview.blind75.heap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KthLargestInArrayTest {

    private final KthLargestInArray solution = new KthLargestInArray();

    @Test
    void k2() {
        assertEquals(5, solution.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }

    @Test
    void k4WithDuplicates() {
        assertEquals(4, solution.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    @Test
    void k1() {
        assertEquals(6, solution.findKthLargest(new int[]{1, 2, 3, 4, 5, 6}, 1));
    }

    @Test
    void quickselectMatchesHeap() {
        assertEquals(5, solution.findKthLargestQuickselect(new int[]{3, 2, 1, 5, 6, 4}, 2));
        assertEquals(4, solution.findKthLargestQuickselect(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        assertEquals(1, solution.findKthLargestQuickselect(new int[]{1}, 1));
        assertEquals(-1, solution.findKthLargestQuickselect(new int[]{-1, -1, -1}, 2));
    }
}
