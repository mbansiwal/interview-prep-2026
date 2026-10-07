package com.interview.blind75.backtracking;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CombinationSumTest {

    private final CombinationSum solution = new CombinationSum();

    private static List<List<Integer>> normalize(List<List<Integer>> combos) {
        List<List<Integer>> out = new ArrayList<>();
        for (List<Integer> c : combos) out.add(c.stream().sorted().toList());
        out.sort(Comparator.comparing(Object::toString));
        return out;
    }

    @Test
    void example1() {
        assertEquals(normalize(List.of(List.of(2, 2, 3), List.of(7))),
                normalize(solution.combinationSum(new int[]{2, 3, 6, 7}, 7)));
    }

    @Test
    void example2() {
        assertEquals(normalize(List.of(List.of(2, 2, 2, 2), List.of(2, 3, 3), List.of(3, 5))),
                normalize(solution.combinationSum(new int[]{2, 3, 5}, 8)));
    }

    @Test
    void unsortedInput() {
        assertEquals(normalize(List.of(List.of(2, 2, 3), List.of(7))),
                normalize(solution.combinationSum(new int[]{7, 3, 6, 2}, 7)));
    }

    @Test
    void noSolution() {
        assertTrue(solution.combinationSum(new int[]{3, 5}, 4).isEmpty());
    }
}
