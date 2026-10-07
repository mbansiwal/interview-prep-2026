package com.interview.blind75.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BuyAndSellStockWithCooldownTest {

    private final BuyAndSellStockWithCooldown solution = new BuyAndSellStockWithCooldown();

    @Test
    void example1() { assertEquals(3, solution.maxProfit(new int[]{1, 2, 3, 0, 2})); }

    @Test
    void singlePrice() { assertEquals(0, solution.maxProfit(new int[]{1})); }

    @Test
    void decreasing() { assertEquals(0, solution.maxProfit(new int[]{5, 4, 3, 2, 1})); }
}
