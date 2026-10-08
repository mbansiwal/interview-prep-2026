package com.interview.blind75.dp;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

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
 * APPROACHES
 * ============================================================
 * Recurrence: minCoins(a) = 1 + min over coins c <= a of minCoins(a - c); minCoins(0) = 0.
 * Greedy (always take the biggest coin) is WRONG: coins [1,3,4], amount 6 → greedy 4+1+1,
 * optimal 3+3. Brute-force recursion is exponential.
 * (Below, k = number of coins, A = amount.)
 *
 * APPROACH 1: Bottom-up DP                                 → coinChange
 *   1. dp[0] = 0, every other dp[a] = "infinity" (A + 1).
 *   2. For a = 1..A, for each coin c <= a: dp[a] = min(dp[a], 1 + dp[a - c]).
 *   3. Return dp[A], or -1 if it's still infinity.
 *   Intuition: the best way to make a ends with some coin c; try them all.
 *   TIME  : O(A · k)    SPACE : O(A)
 *
 * APPROACH 2: Top-down recursion + memo                    → coinChangeMemo
 *   1. best(a) = 0 if a == 0; otherwise 1 + min best(a - c) over usable coins.
 *   2. Cache best(a); store "impossible" as -1.
 *   Intuition: same recurrence; only visits amounts actually reachable from A.
 *   TIME  : O(A · k)    SPACE : O(A) memo + O(A) recursion stack (deep for A = 10^4)
 *
 * APPROACH 3: BFS over amounts                             → coinChangeBfs
 *   1. Start from amount A at level 0; each edge subtracts one coin.
 *   2. The first level where we hit 0 is the minimum number of coins.
 *   3. A visited[] array stops us re-expanding the same amount.
 *   Intuition: "fewest coins" = shortest path in an unweighted graph of amounts.
 *   TIME  : O(A · k)    SPACE : O(A)
 *
 * WHICH TO USE:
 * Approach 1 is the standard answer. BFS is a strong talking point (shortest path)
 * and often stops early. Avoid deep recursion in Java for A near 10^4.
 * ============================================================
 */
public class CoinChange {

    /** Approach 1 — Bottom-up DP. TIME O(A·k) · SPACE O(A) */
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

    /** Approach 2 — Top-down recursion + memo. TIME O(A·k) · SPACE O(A) */
    public int coinChangeMemo(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, Integer.MIN_VALUE); // "not computed"
        return best(coins, amount, memo);
    }

    private int best(int[] coins, int a, int[] memo) {
        if (a == 0) return 0;
        if (memo[a] != Integer.MIN_VALUE) return memo[a];
        int result = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin <= a) {
                int sub = best(coins, a - coin, memo);
                if (sub != -1) result = Math.min(result, sub + 1);
            }
        }
        return memo[a] = (result == Integer.MAX_VALUE) ? -1 : result;
    }

    /** Approach 3 — BFS, shortest path over amounts. TIME O(A·k) · SPACE O(A) */
    public int coinChangeBfs(int[] coins, int amount) {
        if (amount == 0) return 0;
        boolean[] visited = new boolean[amount + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(amount);
        visited[amount] = true;
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            for (int size = queue.size(); size > 0; size--) {
                int remaining = queue.poll();
                for (int coin : coins) {
                    int next = remaining - coin;
                    if (next == 0) return level;
                    if (next > 0 && !visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }
        return -1;
    }
}
