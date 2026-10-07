package com.interview.blind75.dp;

/**
 * ============================================================
 * PROBLEM : Coin Change II
 * LINK    : https://leetcode.com/problems/coin-change-ii/
 * DIFFICULTY: Medium
 * PATTERN : Dynamic Programming (Unbounded Knapsack — Count Combos)
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array of coin denominations and an amount, return the number of
 * combinations that make up the amount. You may use each coin unlimited times.
 *
 * EXAMPLES:
 *   Input : coins = [1,2,5], amount = 5  →  Output: 4  (5, 2+2+1, 2+1+1+1, 1*5)
 *   Input : coins = [2], amount = 3      →  Output: 0
 *
 * CONSTRAINTS:
 *   - 1 <= coins.length <= 300
 *   - 0 <= amount <= 5000
 *
 * ============================================================
 * APPROACH: Bottom-Up DP (Unbounded Knapsack)
 * ============================================================
 * dp[j] = number of ways to make amount j.
 * dp[0] = 1 (one way to make 0: use nothing).
 * For each coin (outer loop), for each amount j from coin to amount:
 *   dp[j] += dp[j - coin]
 *
 * WHY THIS WORKS:
 * Outer loop over coins ensures each combination is counted once
 * (avoids permutation double-counting like [1,2] and [2,1]).
 * Inner loop forward allows coin reuse (unbounded).
 *
 * TIME  : O(coins * amount)
 * SPACE : O(amount)
 * ============================================================
 */
public class CoinChangeII {

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }
        return dp[amount];
    }
}
