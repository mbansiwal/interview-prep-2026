package com.interview.blind75.binarysearch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    private final BinarySearch solution = new BinarySearch();

    @Test
    void found() {
        assertEquals(4, solution.search(new int[]{-1, 0, 3, 5, 9, 12}, 9));
    }

    @Test
    void notFound() {
        assertEquals(-1, solution.search(new int[]{-1, 0, 3, 5, 9, 12}, 2));
    }

    @Test
    void singleElement() {
        assertEquals(0, solution.search(new int[]{5}, 5));
    }

    @Test
    void firstElement() {
        assertEquals(0, solution.search(new int[]{1, 3, 5}, 1));
    }
}
