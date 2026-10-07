package com.interview.blind75.dp;

/**
 * ============================================================
 * PROBLEM : Best Time to Buy and Sell Stock with Cooldown
 * LINK    : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * DIFFICULTY: Medium
 * PATTERN : Dynamic Programming (State Machine)
 * ============================================================
 *
 * DESCRIPTION:
 * Find max profit. You may complete as many transactions as you like, but after
 * selling you must wait 1 day (cooldown) before buying again.
 * You may not hold multiple stocks at once.
 *
 * EXAMPLES:
 *   Input : prices = [1,2,3,0,2]  →  Output: 3  (buy@1, sell@2, cooldown, buy@0, sell@2)
 *   Input : prices = [1]           →  Output: 0
 *
 * CONSTRAINTS:
 *   - 1 <= prices.length <= 5000
 *
 * ============================================================
 * APPROACH: 3-State DP — Hold, Sold (cooldown), Idle
 * ============================================================
 * States at the end of each day:
 * - hold: max profit while holding a stock
 * - sold: max profit if we sold TODAY (tomorrow is a cooldown day)
 * - idle: max profit while not holding and free to buy tomorrow
 *
 * Transitions (prev* = value from yesterday):
 * - sold = prevHold + price              // sell the stock we held yesterday
 * - hold = max(prevHold, prevIdle - price) // keep holding, OR buy (only from idle)
 * - idle = max(prevIdle, prevSold)       // stay idle, OR yesterday's sale cools down
 *
 * WHY THIS WORKS:
 * The cooldown lives in the timing: you can only buy from yesterday's IDLE,
 * and a sale only becomes idle one day later (idle uses prevSold, not today's sold).
 * So a sell on day d cannot be followed by a buy on day d+1.
 * In code we only need to save prevSold: hold and idle are updated after sold
 * reads hold, and idle is the last thing updated.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class BuyAndSellStockWithCooldown {

    public int maxProfit(int[] prices) {
        int hold = Integer.MIN_VALUE, sold = 0, idle = 0;
        for (int price : prices) {
            int prevSold = sold;
            sold = hold + price;
            hold = Math.max(hold, idle - price);
            idle = Math.max(idle, prevSold);
        }
        return Math.max(idle, sold);
    }
}
