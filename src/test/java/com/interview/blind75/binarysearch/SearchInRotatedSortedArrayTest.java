package com.interview.blind75.binarysearch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SearchInRotatedSortedArrayTest {

    private final SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();

    @Test
    void found() {
        assertEquals(4, solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    }

    @Test
    void notFound() {
        assertEquals(-1, solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    }

    @Test
    void notRotated() {
        assertEquals(2, solution.search(new int[]{1, 2, 3, 4, 5}, 3));
    }

    @Test
    void singleElement() {
        assertEquals(0, solution.search(new int[]{1}, 1));
    }
}
