package com.interview.blind75.dp;

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
 * APPROACH: DP with 1D Rolling Array
 * ============================================================
 * dp[j] = number of paths to reach column j in current row.
 * First row: all 1s (only one way: keep going right).
 * For each subsequent row: dp[j] += dp[j-1]  (from above + from left).
 *
 * WHY THIS WORKS:
 * Number of paths to (r, c) = paths from above (r-1, c) + paths from left (r, c-1).
 * 1D rolling array saves space by updating in-place.
 *
 * TIME  : O(m * n)
 * SPACE : O(n)
 * ============================================================
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, 1);
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                dp[c] += dp[c - 1];
            }
        }
        return dp[n - 1];
    }
}
