package com.interview.blind75.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CoinChangeTest {

    private final CoinChange solution = new CoinChange();

    @Test
    void example1() { assertEquals(3, solution.coinChange(new int[]{1, 2, 5}, 11)); }

    @Test
    void impossible() { assertEquals(-1, solution.coinChange(new int[]{2}, 3)); }

    @Test
    void zeroAmount() { assertEquals(0, solution.coinChange(new int[]{1}, 0)); }
}
