package com.interview.blind75.arrays;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class TopKFrequentTest {

    private final TopKFrequent solution = new TopKFrequent();

    @Test
    void topTwo() {
        int[] result = solution.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        Arrays.sort(result);
        assertArrayEquals(new int[]{1, 2}, result);
    }

    @Test
    void singleElement() {
        assertArrayEquals(new int[]{1}, solution.topKFrequent(new int[]{1}, 1));
    }

    @Test
    void uniqueTopKWithMixedFrequencies() {
        int[] result = solution.topKFrequent(new int[]{6, 4, 5, 4, 5, 4}, 2);
        Arrays.sort(result);
        assertArrayEquals(new int[]{4, 5}, result);
    }

    @Test
    void negativeNumbers() {
        assertArrayEquals(new int[]{-1}, solution.topKFrequent(new int[]{-1, -1, 2}, 1));
    }
}
