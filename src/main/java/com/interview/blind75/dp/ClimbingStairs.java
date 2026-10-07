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
 * APPROACH: Fibonacci DP (Space Optimized)
 * ============================================================
 * dp[i] = dp[i-1] + dp[i-2]  (arrive from 1 step back OR 2 steps back)
 * Base cases: dp[1] = 1, dp[2] = 2.
 * We only need previous two values → use two variables.
 *
 * WHY THIS WORKS:
 * The number of ways to reach step i equals the number of ways to reach
 * step i-1 (take 1 step) plus the number of ways to reach step i-2 (take 2 steps).
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class ClimbingStairs {

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
}
