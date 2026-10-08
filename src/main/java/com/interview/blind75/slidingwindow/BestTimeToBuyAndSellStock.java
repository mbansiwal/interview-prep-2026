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
 * APPROACHES
 * ============================================================
 * APPROACH 1: One pass with a running minimum  (primary)
 *   1. Track the cheapest price seen so far.
 *   2. At each day, profit = price − cheapest; keep the best.
 *   Intuition: the best sell day pairs with the cheapest earlier buy day.
 *   TIME O(n) · SPACE O(1)
 *
 * APPROACH 2: Kadane's algorithm on daily price changes
 *   1. diff[i] = prices[i] − prices[i−1].
 *   2. The best profit is the maximum subarray sum of diff (or 0).
 *   Intuition: buying on day a and selling on day b earns the sum of the daily
 *   changes between them — so it's Maximum Subarray in disguise.
 *   TIME O(n) · SPACE O(1)
 *
 * WHICH TO USE:
 *   #1 is the clearest. #2 is worth mentioning: it shows you see the link to
 *   Maximum Subarray (LC 53). Brute force over all pairs is O(n²).
 * ============================================================
 */
public class BestTimeToBuyAndSellStock {

    /** Approach 1 — running minimum. TIME O(n) · SPACE O(1) */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            maxProfit = Math.max(maxProfit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }
        return maxProfit;
    }

    /** Approach 2 — Kadane on daily changes. TIME O(n) · SPACE O(1) */
    public int maxProfitKadane(int[] prices) {
        int best = 0, current = 0;
        for (int i = 1; i < prices.length; i++) {
            current = Math.max(0, current + prices[i] - prices[i - 1]);
            best = Math.max(best, current);
        }
        return best;
    }
}
