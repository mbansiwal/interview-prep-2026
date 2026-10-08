package com.interview.blind75.dp;

import java.util.Arrays;

/**
 * ============================================================
 * PROBLEM : Edit Distance
 * LINK    : https://leetcode.com/problems/edit-distance/
 * DIFFICULTY: Hard
 * PATTERN : Dynamic Programming (2D)
 * ============================================================
 *
 * DESCRIPTION:
 * Given two strings word1 and word2, return the minimum number of operations
 * (insert, delete, replace) to convert word1 into word2.
 *
 * EXAMPLES:
 *   Input : word1="horse", word2="ros"  →  Output: 3
 *   Input : word1="intention", word2="execution"  →  Output: 5
 *
 * CONSTRAINTS:
 *   - 0 <= word1.length, word2.length <= 500
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * State: d(i, j) = edit distance between word1[0..i-1] and word2[0..j-1].
 * d(i, 0) = i, d(0, j) = j. If the last chars match: d(i,j) = d(i-1,j-1);
 * else d(i,j) = 1 + min(d(i-1,j) delete, d(i,j-1) insert, d(i-1,j-1) replace).
 * (m = word1.length, n = word2.length.) Brute-force recursion is O(3^(m+n)).
 *
 * APPROACH 1: 2D table (Levenshtein)                       → minDistance
 *   1. Fill the base row/column with 0..n and 0..m.
 *   2. Fill row by row with the recurrence; answer dp[m][n].
 *   Intuition: each cell is the cheapest alignment of two prefixes.
 *   TIME  : O(m · n)    SPACE : O(m · n)
 *
 * APPROACH 2: One rolling row + saved diagonal (space-optimised) → minDistanceOneRow
 *   1. Make the shorter word the column dimension (edit distance is symmetric).
 *   2. Keep one array row[] for the previous row; before overwriting row[j],
 *      remember it as the next cell's diagonal (dp[i-1][j-1]).
 *   Intuition: row i only reads row i-1 and the cell to its left.
 *   TIME  : O(m · n)    SPACE : O(min(m, n))
 *
 * APPROACH 3: Top-down recursion + memo                    → minDistanceMemo
 *   1. solve(i, j) applies the recurrence on suffixes/prefixes; cache in memo[i][j].
 *   Intuition: the most direct translation of "try all three operations".
 *   TIME  : O(m · n)    SPACE : O(m · n) memo + O(m + n) recursion stack
 *
 * WHICH TO USE:
 * Draw Approach 1's table in the interview (it's the classic picture), then
 * offer Approach 2 as the space follow-up.
 * ============================================================
 */
public class EditDistance {

    /** Approach 1 — 2D table. TIME O(m·n) · SPACE O(m·n) */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                                   Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[m][n];
    }

    /** Approach 2 — One rolling row + saved diagonal. TIME O(m·n) · SPACE O(min(m,n)) */
    public int minDistanceOneRow(String word1, String word2) {
        if (word2.length() > word1.length()) { String t = word1; word1 = word2; word2 = t; }
        int m = word1.length(), n = word2.length();
        int[] row = new int[n + 1];
        for (int j = 0; j <= n; j++) row[j] = j;
        for (int i = 1; i <= m; i++) {
            int diagonal = row[0];   // dp[i-1][0]
            row[0] = i;
            for (int j = 1; j <= n; j++) {
                int above = row[j];  // dp[i-1][j], becomes the next diagonal
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    row[j] = diagonal;
                } else {
                    row[j] = 1 + Math.min(diagonal, Math.min(above, row[j - 1]));
                }
                diagonal = above;
            }
        }
        return row[n];
    }

    /** Approach 3 — Top-down recursion + memo. TIME O(m·n) · SPACE O(m·n) */
    public int minDistanceMemo(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        for (int[] r : memo) Arrays.fill(r, -1);
        return solve(word1, word2, word1.length(), word2.length(), memo);
    }

    private int solve(String a, String b, int i, int j, int[][] memo) {
        if (i == 0) return j;
        if (j == 0) return i;
        if (memo[i][j] != -1) return memo[i][j];
        int result;
        if (a.charAt(i - 1) == b.charAt(j - 1)) {
            result = solve(a, b, i - 1, j - 1, memo);
        } else {
            result = 1 + Math.min(solve(a, b, i - 1, j - 1, memo),
                         Math.min(solve(a, b, i - 1, j, memo), solve(a, b, i, j - 1, memo)));
        }
        return memo[i][j] = result;
    }
}
