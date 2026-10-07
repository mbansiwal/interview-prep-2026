package com.interview.blind75.greedy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaximumSubarrayTest {

    private final MaximumSubarray solution = new MaximumSubarray();

    @Test
    void example1() { assertEquals(6, solution.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4})); }

    @Test
    void allPositive() { assertEquals(23, solution.maxSubArray(new int[]{5,4,-1,7,8})); }

    @Test
    void singleNegative() { assertEquals(-1, solution.maxSubArray(new int[]{-1})); }
}
