package com.interview.blind75.dp;

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
 * APPROACH: 2D DP Table
 * ============================================================
 * dp[i][j] = LCS length of text1[0..i-1] and text2[0..j-1].
 * If text1[i-1] == text2[j-1]: dp[i][j] = 1 + dp[i-1][j-1]
 * Else: dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 *
 * WHY THIS WORKS:
 * When characters match, we extend the LCS of both prefixes.
 * When they don't, we take the best of excluding one character from either string.
 *
 * TIME  : O(m * n)
 * SPACE : O(m * n)
 *
 * FOLLOW-UP (space): row i only reads rows i and i-1, so keep two rolling
 * arrays sized to the shorter string → O(min(m, n)) space.
 * ============================================================
 */
public class LongestCommonSubsequence {

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
}
