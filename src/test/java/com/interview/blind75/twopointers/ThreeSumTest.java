package com.interview.blind75.twopointers;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class ThreeSumTest {

    private final ThreeSum solution = new ThreeSum();

    @Test
    void twoTriplets() {
        List<List<Integer>> result = solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        assertEquals(2, result.size());
    }

    @Test
    void allZeros() {
        List<List<Integer>> result = solution.threeSum(new int[]{0, 0, 0});
        assertEquals(1, result.size());
        assertEquals(Arrays.asList(0, 0, 0), result.get(0));
    }

    @Test
    void noTriplet() {
        List<List<Integer>> result = solution.threeSum(new int[]{1, 2, 3});
        assertEquals(0, result.size());
    }
}
