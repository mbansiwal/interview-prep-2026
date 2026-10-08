package com.interview.blind75.dp;

/**
 * ============================================================
 * PROBLEM : Climbing Stairs
 * LINK    : https://leetcode.com/problems/climbing-stairs/
 * DIFFICULTY: Easy
 * PATTERN : Dynamic Programming (1D) / Fibonacci
 * ============================================================
 *
 * DESCRIPTION:
 * You are climbing a staircase with n steps. You can climb 1 or 2 steps at a time.
 * Return the number of distinct ways to reach the top.
 *
 * EXAMPLES:
 *   Input : n = 2  →  Output: 2  (1+1, 2)
 *   Input : n = 3  →  Output: 3  (1+1+1, 1+2, 2+1)
 *
 * CONSTRAINTS:
 *   - 1 <= n <= 45
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * Recurrence for all of them: ways(i) = ways(i-1) + ways(i-2)
 * (the last move was either a 1-step or a 2-step). ways(1)=1, ways(2)=2.
 * Brute-force recursion without caching is O(2^n) — never submit it.
 *
 * APPROACH 1: Two rolling variables (space-optimised)      → climbStairs
 *   1. Keep prev2 = ways(i-2) and prev1 = ways(i-1).
 *   2. For i = 3..n: curr = prev1 + prev2, then shift the window.
 *   Intuition: each step only looks two steps back, so the table is unnecessary.
 *   TIME  : O(n)    SPACE : O(1)
 *
 * APPROACH 2: Top-down recursion + memo                    → climbStairsMemo
 *   1. ways(i) calls ways(i-1) and ways(i-2).
 *   2. Cache each result in memo[i] so every i is computed once.
 *   Intuition: the natural way to write the recurrence; memo kills the overlap.
 *   TIME  : O(n)    SPACE : O(n) memo + O(n) recursion stack
 *
 * APPROACH 3: Bottom-up table (tabulation)                 → climbStairsTabulation
 *   1. dp[1]=1, dp[2]=2.
 *   2. Fill dp[i] = dp[i-1] + dp[i-2] for i = 3..n.
 *   Intuition: same recurrence, iterative order, no stack.
 *   TIME  : O(n)    SPACE : O(n)
 *
 * WHICH TO USE:
 * Interviewers expect you to talk through 2 → 3 → 1. Submit Approach 1.
 * (An O(log n) matrix-exponentiation version exists but is rarely expected.)
 * ============================================================
 */
public class ClimbingStairs {

    /** Approach 1 — Two rolling variables. TIME O(n) · SPACE O(1) */
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int prev2 = 1, prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    /** Approach 2 — Top-down recursion + memo. TIME O(n) · SPACE O(n) */
    public int climbStairsMemo(int n) {
        return ways(n, new int[n + 1]);
    }

    private int ways(int i, int[] memo) {
        if (i <= 2) return i;
        if (memo[i] != 0) return memo[i];
        return memo[i] = ways(i - 1, memo) + ways(i - 2, memo);
    }

    /** Approach 3 — Bottom-up table. TIME O(n) · SPACE O(n) */
    public int climbStairsTabulation(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }
}
