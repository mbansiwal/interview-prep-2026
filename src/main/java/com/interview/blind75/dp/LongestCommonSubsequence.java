package com.interview.blind75.dp;

import java.util.Arrays;

/**
 * ============================================================
 * PROBLEM : Longest Common Subsequence
 * LINK    : https://leetcode.com/problems/longest-common-subsequence/
 * DIFFICULTY: Medium
 * PATTERN : Dynamic Programming (2D)
 * ============================================================
 *
 * DESCRIPTION:
 * Given two strings text1 and text2, return the length of their longest
 * common subsequence. A subsequence is derived by deleting some characters
 * without changing the remaining order.
 *
 * EXAMPLES:
 *   Input : text1="abcde", text2="ace"  →  Output: 3 ("ace")
 *   Input : text1="abc", text2="abc"    →  Output: 3
 *   Input : text1="abc", text2="def"    →  Output: 0
 *
 * CONSTRAINTS:
 *   - 1 <= text1.length, text2.length <= 1000
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * State: L(i, j) = LCS length of text1[0..i-1] and text2[0..j-1].
 * Match: L(i,j) = 1 + L(i-1,j-1). No match: L(i,j) = max(L(i-1,j), L(i,j-1)).
 * (m, n = string lengths.) Brute force (all subsequences) is O(2^m · n).
 *
 * APPROACH 1: 2D table                                     → longestCommonSubsequence
 *   1. dp has an extra zero row/column for empty prefixes.
 *   2. Fill row by row with the recurrence; answer dp[m][n].
 *   Intuition: a match extends the diagonal; a mismatch drops one character.
 *   TIME  : O(m · n)    SPACE : O(m · n)
 *
 * APPROACH 2: One rolling row + saved diagonal (space-optimised) → lcsOneRow
 *   1. Use the shorter string as the column dimension (LCS is symmetric).
 *   2. Keep one array; carry dp[i-1][j-1] in a variable before overwriting.
 *   Intuition: each row only reads the row above and the cell to its left.
 *   TIME  : O(m · n)    SPACE : O(min(m, n))
 *
 * APPROACH 3: Top-down recursion + memo                    → lcsMemo
 *   1. solve(i, j) applies the recurrence; cache in memo[i][j].
 *   Intuition: "compare the last characters, then shrink one or both strings".
 *   TIME  : O(m · n)    SPACE : O(m · n) memo + O(m + n) recursion stack
 *
 * WHICH TO USE:
 * Approach 1 is expected and lets you reconstruct the actual subsequence by
 * walking back from dp[m][n]. Offer Approach 2 when asked about memory.
 * ============================================================
 */
public class LongestCommonSubsequence {

    /** Approach 1 — 2D table. TIME O(m·n) · SPACE O(m·n) */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    /** Approach 2 — One rolling row + saved diagonal. TIME O(m·n) · SPACE O(min(m,n)) */
    public int lcsOneRow(String text1, String text2) {
        if (text2.length() > text1.length()) { String t = text1; text1 = text2; text2 = t; }
        int m = text1.length(), n = text2.length();
        int[] row = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            int diagonal = 0;            // dp[i-1][0]
            for (int j = 1; j <= n; j++) {
                int above = row[j];      // dp[i-1][j]
                row[j] = text1.charAt(i - 1) == text2.charAt(j - 1)
                        ? diagonal + 1
                        : Math.max(above, row[j - 1]);
                diagonal = above;
            }
        }
        return row[n];
    }

    /** Approach 3 — Top-down recursion + memo. TIME O(m·n) · SPACE O(m·n) */
    public int lcsMemo(String text1, String text2) {
        int[][] memo = new int[text1.length() + 1][text2.length() + 1];
        for (int[] r : memo) Arrays.fill(r, -1);
        return solve(text1, text2, text1.length(), text2.length(), memo);
    }

    private int solve(String a, String b, int i, int j, int[][] memo) {
        if (i == 0 || j == 0) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        int result = a.charAt(i - 1) == b.charAt(j - 1)
                ? 1 + solve(a, b, i - 1, j - 1, memo)
                : Math.max(solve(a, b, i - 1, j, memo), solve(a, b, i, j - 1, memo));
        return memo[i][j] = result;
    }
}
