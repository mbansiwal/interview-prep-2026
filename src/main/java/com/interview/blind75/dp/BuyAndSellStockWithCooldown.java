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
 * APPROACHES
 * ============================================================
 * Both use three states at the end of each day:
 * - hold: best profit while holding a stock
 * - sold: best profit if we sold TODAY (tomorrow is a cooldown day)
 * - idle: best profit while not holding and free to buy tomorrow
 * Transitions (prev* = yesterday):
 *   sold = prevHold + price
 *   hold = max(prevHold, prevIdle - price)   // buying is only allowed from idle
 *   idle = max(prevIdle, prevSold)           // yesterday's sale cools down into idle
 * Brute force (try buy/sell/skip every day) is O(3^n).
 *
 * APPROACH 1: Three rolling variables (space-optimised)    → maxProfit
 *   1. Keep hold / sold / idle for "yesterday".
 *   2. Save prevSold, then update sold, hold, idle in that order.
 *   Intuition: each day only depends on the previous day's three numbers.
 *   TIME  : O(n)    SPACE : O(1)
 *
 * APPROACH 2: State arrays per day                         → maxProfitArrays
 *   1. hold[i], sold[i], idle[i] filled with the same transitions.
 *   2. Answer = max(sold[n-1], idle[n-1]).
 *   Intuition: the explicit state-machine table — easiest to trace by hand.
 *   TIME  : O(n)    SPACE : O(n)
 *
 * WHY THE COOLDOWN HOLDS: you can only buy from yesterday's IDLE, and a sale only
 * becomes idle one day later, so a sell on day d can't be followed by a buy on d+1.
 *
 * WHICH TO USE:
 * Draw Approach 2's state diagram, then submit Approach 1.
 * ============================================================
 */
public class BuyAndSellStockWithCooldown {

    /** Approach 1 — Three rolling state variables. TIME O(n) · SPACE O(1) */
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

    /** Approach 2 — Per-day state arrays. TIME O(n) · SPACE O(n) */
    public int maxProfitArrays(int[] prices) {
        int n = prices.length;
        int[] hold = new int[n], sold = new int[n], idle = new int[n];
        hold[0] = -prices[0];
        sold[0] = 0;   // can't sell on day 0 for a profit; 0 is never better than idle
        idle[0] = 0;
        for (int i = 1; i < n; i++) {
            sold[i] = hold[i - 1] + prices[i];
            hold[i] = Math.max(hold[i - 1], idle[i - 1] - prices[i]);
            idle[i] = Math.max(idle[i - 1], sold[i - 1]);
        }
        return Math.max(sold[n - 1], idle[n - 1]);
    }
}
