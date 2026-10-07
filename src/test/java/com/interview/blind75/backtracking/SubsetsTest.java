package com.interview.blind75.backtracking;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SubsetsTest {

    private final Subsets solution = new Subsets();

    @Test
    void threeElements() {
        List<List<Integer>> result = solution.subsets(new int[]{1, 2, 3});
        assertEquals(8, result.size());
    }

    @Test
    void singleElement() {
        List<List<Integer>> result = solution.subsets(new int[]{0});
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(List::isEmpty));
        assertTrue(result.stream().anyMatch(s -> s.equals(List.of(0))));
    }

    @Test
    void containsEmptySubset() {
        List<List<Integer>> result = solution.subsets(new int[]{1, 2});
        assertTrue(result.stream().anyMatch(List::isEmpty));
        assertEquals(4, result.size());
    }
}
