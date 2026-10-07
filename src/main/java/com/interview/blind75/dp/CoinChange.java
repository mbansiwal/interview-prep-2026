package com.interview.blind75.dp;

import java.util.Arrays;

/**
 * ============================================================
 * PROBLEM : Coin Change
 * LINK    : https://leetcode.com/problems/coin-change/
 * DIFFICULTY: Medium
 * PATTERN : Dynamic Programming (Unbounded Knapsack)
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array coins and amount, return the fewest coins needed to
 * make up the amount. Return -1 if it's not possible.
 *
 * EXAMPLES:
 *   Input : coins = [1,2,5], amount = 11  →  Output: 3  (5+5+1)
 *   Input : coins = [2], amount = 3       →  Output: -1
 *
 * CONSTRAINTS:
 *   - 1 <= coins.length <= 12
 *   - 0 <= amount <= 10^4
 *
 * ============================================================
 * APPROACH: Bottom-Up DP
 * ============================================================
 * dp[i] = min coins to make amount i.
 * dp[0] = 0 (base case).
 * For each amount i from 1 to amount:
 *   For each coin c: dp[i] = min(dp[i], 1 + dp[i - c]) if i >= c.
 *
 * WHY THIS WORKS:
 * Each coin reduces the problem by c. We try all coins for each amount,
 * building up from smaller amounts (optimal substructure).
 *
 * TIME  : O(amount * coins.length)
 * SPACE : O(amount)
 * ============================================================
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // "infinity"
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
