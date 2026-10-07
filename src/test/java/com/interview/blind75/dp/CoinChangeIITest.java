package com.interview.blind75.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CoinChangeIITest {

    private final CoinChangeII solution = new CoinChangeII();

    @Test
    void example1() { assertEquals(4, solution.change(5, new int[]{1, 2, 5})); }

    @Test
    void impossible() { assertEquals(0, solution.change(3, new int[]{2})); }

    @Test
    void zeroAmount() { assertEquals(1, solution.change(0, new int[]{1, 2, 3})); }
}
