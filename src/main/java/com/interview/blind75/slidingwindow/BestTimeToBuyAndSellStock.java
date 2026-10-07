package com.interview.blind75.slidingwindow;

/**
 * ============================================================
 * PROBLEM : Best Time to Buy and Sell Stock
 * LINK    : https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * DIFFICULTY: Easy
 * PATTERN : Sliding Window
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array prices where prices[i] is the price of stock on day i,
 * find the maximum profit from one buy-sell transaction. Return 0 if
 * no profit is possible.
 *
 * EXAMPLES:
 *   Input : prices = [7,1,5,3,6,4]
 *   Output: 5  (buy at 1, sell at 6)
 *
 *   Input : prices = [7,6,4,3,1]
 *   Output: 0  (no profitable transaction)
 *
 * CONSTRAINTS:
 *   - 1 <= prices.length <= 10^5
 *   - 0 <= prices[i] <= 10^4
 *
 * ============================================================
 * APPROACH: Single Pass with Running Minimum
 * ============================================================
 * 1. Track the minimum price seen so far.
 * 2. For each price, compute profit = price - minPrice.
 * 3. Update maxProfit if this profit is larger.
 * 4. Update minPrice if current price is lower.
 *
 * WHY THIS WORKS:
 * At each day, the best we can do is sell at today's price after buying
 * at the cheapest price seen before today. One pass handles both.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            maxProfit = Math.max(maxProfit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }
        return maxProfit;
    }
}
