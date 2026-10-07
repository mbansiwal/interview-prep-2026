package com.interview.blind75.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CombinationSumIVTest {

    private final CombinationSumIV solution = new CombinationSumIV();

    @Test
    void orderedSequencesCounted() { assertEquals(7, solution.combinationSum4(new int[]{1, 2, 3}, 4)); }

    @Test
    void unreachableTarget() { assertEquals(0, solution.combinationSum4(new int[]{9}, 3)); }

    @Test
    void singleCoinExactlyDivides() { assertEquals(1, solution.combinationSum4(new int[]{2}, 6)); }

    @Test
    void differsFromCoinChangeII() {
        // CoinChangeII counts {1,2},{1,1,1} = 2 for amount 3; ordered count is 3
        assertEquals(3, solution.combinationSum4(new int[]{1, 2}, 3));
    }
}
