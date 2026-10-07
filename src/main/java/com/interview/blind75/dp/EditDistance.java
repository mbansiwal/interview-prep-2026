package com.interview.blind75.dp;

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
 * APPROACH: 2D DP — Levenshtein Distance
 * ============================================================
 * dp[i][j] = edit distance between word1[0..i-1] and word2[0..j-1].
 * Base cases: dp[i][0] = i, dp[0][j] = j (delete/insert all chars).
 * Recurrence:
 *   If chars match: dp[i][j] = dp[i-1][j-1]  (no op needed)
 *   Else: dp[i][j] = 1 + min(dp[i-1][j],    // delete from word1
 *                              dp[i][j-1],    // insert into word1
 *                              dp[i-1][j-1])  // replace
 *
 * WHY THIS WORKS:
 * Each cell represents the optimal alignment of the two prefixes.
 * Three choices correspond to delete, insert, and replace operations.
 *
 * TIME  : O(m * n)
 * SPACE : O(m * n)
 *
 * FOLLOW-UP (space): row i only reads rows i and i-1, so keep two rolling
 * arrays (or one array + a saved diagonal) → O(min(m, n)) space.
 * ============================================================
 */
public class EditDistance {

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
}
