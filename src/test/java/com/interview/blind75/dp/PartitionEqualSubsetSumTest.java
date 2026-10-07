package com.interview.blind75.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PartitionEqualSubsetSumTest {

    private final PartitionEqualSubsetSum solution = new PartitionEqualSubsetSum();

    @Test
    void canPartition() { assertTrue(solution.canPartition(new int[]{1, 5, 11, 5})); }

    @Test
    void cannotPartition() { assertFalse(solution.canPartition(new int[]{1, 2, 3, 5})); }

    @Test
    void oddSum() { assertFalse(solution.canPartition(new int[]{1, 2})); }
}
