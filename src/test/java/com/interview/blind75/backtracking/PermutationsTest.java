package com.interview.blind75.backtracking;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PermutationsTest {

    private final Permutations solution = new Permutations();

    @Test
    void threeElements() {
        assertEquals(6, solution.permute(new int[]{1, 2, 3}).size());
    }

    @Test
    void twoElements() {
        List<List<Integer>> result = solution.permute(new int[]{0, 1});
        assertEquals(2, result.size());
    }

    @Test
    void singleElement() {
        List<List<Integer>> result = solution.permute(new int[]{1});
        assertEquals(1, result.size());
        assertEquals(List.of(1), result.get(0));
    }
}
