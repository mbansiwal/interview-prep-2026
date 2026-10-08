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
 * APPROACHES
 * ============================================================
 * State: ways(i, j) = combinations making j using only the first i coin types.
 * ways(i, j) = ways(i-1, j)            (never use coin i)
 *            + ways(i, j - coin[i-1])  (use coin i at least once more).
 * (k = number of coins, A = amount.) Brute-force recursion is exponential.
 *
 * APPROACH 1: 1D DP (space-optimised)                      → change
 *   1. dp[0] = 1 (one way to make 0: use nothing).
 *   2. For each coin (OUTER loop), for j = coin..A: dp[j] += dp[j - coin].
 *   Intuition: the 2D table where row i only reads row i-1 and itself, so one
 *   row suffices. Coins outer = each multiset counted once (no [1,2] vs [2,1]).
 *   TIME  : O(k · A)    SPACE : O(A)
 *
 * APPROACH 2: 2D table                                     → change2D
 *   1. dp[i][0] = 1 for all i; dp[0][j>0] = 0.
 *   2. dp[i][j] = dp[i-1][j] + (j >= coin ? dp[i][j - coin] : 0).
 *   Intuition: the full "first i coins" table — makes the recurrence explicit.
 *   TIME  : O(k · A)    SPACE : O(k · A)
 *
 * WHICH TO USE:
 * Derive Approach 2 to explain WHY the loop order matters, then collapse to
 * Approach 1. Swapping the loops counts orderings instead (see Combination Sum IV).
 * ============================================================
 */
public class CoinChangeII {

    /** Approach 1 — 1D DP, coins in the outer loop. TIME O(k·A) · SPACE O(A) */
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

    /** Approach 2 — 2D table over (coins used, amount). TIME O(k·A) · SPACE O(k·A) */
    public int change2D(int amount, int[] coins) {
        int k = coins.length;
        int[][] dp = new int[k + 1][amount + 1];
        for (int i = 0; i <= k; i++) dp[i][0] = 1;
        for (int i = 1; i <= k; i++) {
            int coin = coins[i - 1];
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j] + (j >= coin ? dp[i][j - coin] : 0);
            }
        }
        return dp[k][amount];
    }
}
