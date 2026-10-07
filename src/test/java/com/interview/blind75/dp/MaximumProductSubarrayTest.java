package com.interview.blind75.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaximumProductSubarrayTest {

    private final MaximumProductSubarray solution = new MaximumProductSubarray();

    @Test
    void example1() { assertEquals(6, solution.maxProduct(new int[]{2, 3, -2, 4})); }

    @Test
    void withZero() { assertEquals(0, solution.maxProduct(new int[]{-2, 0, -1})); }

    @Test
    void allNegative() { assertEquals(2, solution.maxProduct(new int[]{-2, -1})); }
}
