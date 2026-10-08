package com.interview.blind75.dp;

import java.util.Arrays;

/**
 * ============================================================
 * PROBLEM : Unique Paths
 * LINK    : https://leetcode.com/problems/unique-paths/
 * DIFFICULTY: Medium
 * PATTERN : Dynamic Programming (2D)
 * ============================================================
 *
 * DESCRIPTION:
 * A robot is on the top-left corner of an m x n grid and must reach the
 * bottom-right corner. It can only move right or down. Return the number
 * of unique paths.
 *
 * EXAMPLES:
 *   Input : m=3, n=7  →  Output: 28
 *   Input : m=3, n=2  →  Output: 3
 *
 * CONSTRAINTS:
 *   - 1 <= m, n <= 100
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * Recurrence: paths(r, c) = paths(r-1, c) + paths(r, c-1); first row and column are 1.
 * Brute-force recursion is exponential.
 *
 * APPROACH 1: 1D rolling array (space-optimised)           → uniquePaths
 *   1. dp[c] = paths to column c in the current row; start with all 1s.
 *   2. For each next row: dp[c] += dp[c-1] (old dp[c] = from above, dp[c-1] = from left).
 *   Intuition: a row only needs the row above it.
 *   TIME  : O(m · n)    SPACE : O(n)
 *
 * APPROACH 2: 2D table                                     → uniquePaths2D
 *   1. dp[r][0] = dp[0][c] = 1.
 *   2. dp[r][c] = dp[r-1][c] + dp[r][c-1].
 *   Intuition: the textbook grid picture of the recurrence.
 *   TIME  : O(m · n)    SPACE : O(m · n)
 *
 * APPROACH 3: Combinatorics                                → uniquePathsMath
 *   1. Every path is (m-1) downs and (n-1) rights in some order.
 *   2. Answer = C(m+n-2, k) with k = min(m, n) - 1, built as
 *      result = result · (N - k + i) / i for i = 1..k (each step stays an integer).
 *   Intuition: choose WHICH of the m+n-2 moves are downs.
 *   TIME  : O(min(m, n))    SPACE : O(1)
 *
 * WHICH TO USE:
 * Show the DP (Approach 2 → 1), then mention Approach 3 as the optimal closed form.
 * The DP generalises to obstacles (Unique Paths II); the formula doesn't.
 * ============================================================
 */
public class UniquePaths {

    /** Approach 1 — 1D rolling array. TIME O(m·n) · SPACE O(n) */
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                dp[c] += dp[c - 1];
            }
        }
        return dp[n - 1];
    }

    /** Approach 2 — 2D table. TIME O(m·n) · SPACE O(m·n) */
    public int uniquePaths2D(int m, int n) {
        int[][] dp = new int[m][n];
        for (int r = 0; r < m; r++) dp[r][0] = 1;
        for (int c = 0; c < n; c++) dp[0][c] = 1;
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /** Approach 3 — Combinatorics C(m+n-2, min(m,n)-1). TIME O(min(m,n)) · SPACE O(1) */
    public int uniquePathsMath(int m, int n) {
        int total = m + n - 2;
        int k = Math.min(m, n) - 1;
        long result = 1;
        for (int i = 1; i <= k; i++) {
            result = result * (total - k + i) / i; // C(total-k+i, i), exact at every step
        }
        return (int) result;
    }
}
